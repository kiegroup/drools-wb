/*
 * Copyright 2019 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.drools.workbench.screens.scenariosimulation.webapp.client.workarounds;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;

import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import jsinterop.base.Js;
import org.drools.scenariosimulation.api.model.AbstractScesimData;
import org.drools.scenariosimulation.api.model.AbstractScesimModel;
import org.drools.scenariosimulation.api.model.Background;
import org.drools.scenariosimulation.api.model.BackgroundData;
import org.drools.scenariosimulation.api.model.BackgroundDataWithIndex;
import org.drools.scenariosimulation.api.model.ExpressionIdentifier;
import org.drools.scenariosimulation.api.model.FactIdentifier;
import org.drools.scenariosimulation.api.model.FactMapping;
import org.drools.scenariosimulation.api.model.FactMappingType;
import org.drools.scenariosimulation.api.model.ScenarioSimulationModel;
import org.drools.scenariosimulation.api.model.ScenarioWithIndex;
import org.drools.scenariosimulation.api.model.ScesimDataWithIndex;
import org.drools.scenariosimulation.api.model.ScesimModelDescriptor;
import org.drools.scenariosimulation.api.model.Settings;
import org.drools.scenariosimulation.api.model.Simulation;
import org.drools.scenariosimulation.api.utils.ScenarioSimulationSharedUtils;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.SCESIMMainJs;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.callbacks.SCESIMMarshallCallback;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.model.JSIScenarioSimulationModelType;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.model.SCESIM;
import org.drools.workbench.scenariosimulation.kogito.marshaller.mapper.JsUtils;
import org.drools.workbench.screens.scenariosimulation.kogito.client.converters.scesim.ApiJSInteropConverter;
import org.drools.workbench.screens.scenariosimulation.model.typedescriptor.FactModelTree;
import org.drools.workbench.screens.scenariosimulation.model.typedescriptor.FactModelTuple;
import org.guvnor.common.services.backend.exceptions.ExceptionUtilities;
import org.jboss.errai.common.client.api.ErrorCallback;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.kie.dmn.feel.lang.Type;
import org.kie.dmn.feel.lang.types.BuiltInType;
import org.kie.workbench.common.dmn.webapp.kogito.marshaller.js.model.MainJs;
import org.kie.workbench.common.dmn.webapp.kogito.marshaller.js.model.callbacks.DMN12UnmarshallCallback;
import org.kie.workbench.common.dmn.webapp.kogito.marshaller.js.model.dmn12.DMN12;
import org.kie.workbench.common.dmn.webapp.kogito.marshaller.js.model.dmn12.JSITDRGElement;
import org.kie.workbench.common.dmn.webapp.kogito.marshaller.js.model.dmn12.JSITDecision;
import org.kie.workbench.common.dmn.webapp.kogito.marshaller.js.model.dmn12.JSITDefinitions;
import org.kie.workbench.common.dmn.webapp.kogito.marshaller.js.model.dmn12.JSITInputData;
import org.uberfire.backend.vfs.Path;
import org.uberfire.backend.vfs.PathFactory;

import static org.drools.scenariosimulation.api.model.FactMappingType.EXPECT;
import static org.drools.scenariosimulation.api.model.FactMappingType.GIVEN;
import static org.drools.scenariosimulation.api.utils.ConstantsHolder.VALUE;

/**
 * Class used to populate <code>ScenarioSimulationModel</code> inside <code>Callback</code>s
 * <p>
 * Mostly copied from <code>org.drools.workbench.screens.scenariosimulation.backend.server.util.SimulationSettingsCreationStrategy</code> and its children.
 * <p>
 * Implemented to manage the nested callbacks for <b>DMN</b>
 */
public class KogitoTestingScenarioSimulationBuilder {

    @Inject
    private KogitoDMNTypeServiceTestingImpl dmnTypeService;

    private static FactMappingType convert(final FactModelTree.Type modelTreeType) {
        switch (modelTreeType) {
            case INPUT:
                return GIVEN;
            case DECISION:
                return EXPECT;
            default:
                throw new IllegalArgumentException("Impossible to map");
        }
    }

    /**
     * Populate the given <code>ScenarioSimulationModel</code> and returns it inn the given <code>callback</code>
     * @param context
     * @param content
     * @param type
     * @param value
     * @param callback
     */
    public void populateScenarioSimulationModel(final Path context, final ScenarioSimulationModel content, final ScenarioSimulationModel.Type type, final String value, RemoteCallback<String> callback) {
        switch (type) {
            case RULE:
                populateRULE(content, value, callback);
                break;
            case DMN:
                populateDMN(content, context, value, callback);
                break;
            default:
                // noop
        }
    }

    private void populateRULE(final ScenarioSimulationModel toPopulate, final String dmoSession, final RemoteCallback<String> callback) {
        toPopulate.setSimulation(createRULESimulation());
        toPopulate.setBackground(createBackground());
        toPopulate.setSettings(createRULESettings(dmoSession));
        convertScenarioSimulationModel(toPopulate, callback);
    }

    private void populateDMN(final ScenarioSimulationModel toPopulate, final Path context, final String dmnFilePath, final RemoteCallback<String> callback) {
        toPopulate.setBackground(createBackground());
        toPopulate.setSettings(createDMNSettings(context, dmnFilePath));
        populateDMNSimulation(toPopulate, dmnFilePath, callback);
    }

    private void convertScenarioSimulationModel(final ScenarioSimulationModel toConvert, final RemoteCallback<String> callback) {
        JSIScenarioSimulationModelType converted = ApiJSInteropConverter.getJSIScenarioSimulationModelType(toConvert);
        SCESIM scesim = Js.uncheckedCast(JsUtils.getWrappedElement(converted));
        SCESIMMarshallCallback scesimMarshallCallback = getSCESIMMarshallCallback(callback);
        SCESIMMainJs.marshall(scesim, "", scesimMarshallCallback);
    }

    private Simulation createRULESimulation() {
        Simulation toReturn = new Simulation();
        ScesimModelDescriptor simulationDescriptor = toReturn.getScesimModelDescriptor();
        simulationDescriptor.addFactMapping(FactIdentifier.INDEX.getName(), FactIdentifier.INDEX, ExpressionIdentifier.INDEX);
        simulationDescriptor.addFactMapping(FactIdentifier.DESCRIPTION.getName(), FactIdentifier.DESCRIPTION, ExpressionIdentifier.DESCRIPTION);
        ScenarioWithIndex scenarioWithIndex = createScesimDataWithIndex(toReturn, (integer, scenario) -> new ScenarioWithIndex());

        // Add GIVEN Fact
        createEmptyColumn(simulationDescriptor,
                          scenarioWithIndex,
                          1,
                          GIVEN,
                          simulationDescriptor.getFactMappings().size());

        // Add EXPECT Fact
        createEmptyColumn(simulationDescriptor,
                          scenarioWithIndex,
                          2,
                          EXPECT,
                          simulationDescriptor.getFactMappings().size());
        return toReturn;
    }

    private void populateDMNSimulation(final ScenarioSimulationModel toPopulate, final String dmnFilePath, final RemoteCallback<String> callback) {
        String dmnFileName = dmnFilePath.substring(dmnFilePath.lastIndexOf('/') + 1);
        final Path dmnPath = PathFactory.newPath(dmnFileName, dmnFilePath);
        dmnTypeService.getDMNContent(dmnPath, new RemoteCallback<String>() {
                                         @Override
                                         public void callback(String dmnContent) {
                                             DMN12UnmarshallCallback dmn12UnmarshallCallback = getDMN12UnmarshallCallback(toPopulate, callback);
                                             MainJs.unmarshall(dmnContent, "", dmn12UnmarshallCallback);
                                         }
                                     },
                                     new ErrorCallback<Object>() {
                                         @Override
                                         public boolean error(Object message, Throwable throwable) {
                                             GWT.log("Error " + message.toString(), throwable);
                                             return false;
                                         }
                                     });
    }

    private Background createBackground() {
        Background toReturn = new Background();
        ScesimModelDescriptor simulationDescriptor = toReturn.getScesimModelDescriptor();
        int index = toReturn.getUnmodifiableData().size() + 1;
        BackgroundData backgroundData = toReturn.addData();
        BackgroundDataWithIndex backgroundDataWithIndex = new BackgroundDataWithIndex(index, backgroundData);

        // Add GIVEN Fact
        createEmptyColumn(simulationDescriptor,
                          backgroundDataWithIndex,
                          1,
                          GIVEN,
                          simulationDescriptor.getFactMappings().size());
        return toReturn;
    }

    private Settings createRULESettings(final String dmoSession) {
        Settings toReturn = new Settings();
        toReturn.setType(ScenarioSimulationModel.Type.RULE);
        toReturn.setDmoSession(dmoSession);
        return toReturn;
    }

    private Settings createDMNSettings(final Path context, final String dmnFilePath) {
        Settings toReturn = new Settings();
        toReturn.setType(ScenarioSimulationModel.Type.DMN);
        toReturn.setDmnFilePath(dmnFilePath);
        dmnTypeService.initializeNameAndNamespace(toReturn, context, dmnFilePath);
        return toReturn;
    }

    private <T extends AbstractScesimData, E extends ScesimDataWithIndex<T>> E createScesimDataWithIndex(final AbstractScesimModel<T> abstractScesimModel,
                                                                                                         final BiFunction<Integer, T, E> producer) {
        T scenario = abstractScesimModel.addData();
        scenario.setDescription(null);
        int index = abstractScesimModel.getUnmodifiableData().indexOf(scenario) + 1;
        return producer.apply(index, scenario);
    }

    /**
     * Create an empty column using factMappingType defined. The new column will be added as last column of
     * the group (GIVEN/EXPECT) (see findLastIndexOfGroup)
     * @param simulationDescriptor
     * @param scesimDataWithIndex
     * @param placeholderId
     * @param factMappingType
     * @param columnIndex
     */
    private void createEmptyColumn(final ScesimModelDescriptor simulationDescriptor,
                                   final ScesimDataWithIndex scesimDataWithIndex,
                                   final int placeholderId,
                                   final FactMappingType factMappingType,
                                   final int columnIndex) {
        int row = scesimDataWithIndex.getIndex();
        final ExpressionIdentifier expressionIdentifier = ExpressionIdentifier.create(row + "|" + placeholderId, factMappingType);

        final FactMapping factMapping = simulationDescriptor
                .addFactMapping(
                        columnIndex,
                        FactMapping.getInstancePlaceHolder(placeholderId),
                        FactIdentifier.EMPTY,
                        expressionIdentifier);
        factMapping.setExpressionAlias(FactMapping.getPropertyPlaceHolder(placeholderId));
        scesimDataWithIndex.getScesimData().addMappingValue(FactIdentifier.EMPTY, expressionIdentifier, null);
    }

    /**
     * If DMN model is empty, contains only inputs or only outputs this method add one GIVEN and/or EXPECT empty column
     * @param simulation
     * @param scenarioWithIndex
     */
    private void addEmptyColumnsIfNeeded(final Simulation simulation, final ScenarioWithIndex scenarioWithIndex) {
        boolean hasGiven = false;
        boolean hasExpect = false;
        ScesimModelDescriptor simulationDescriptor = simulation.getScesimModelDescriptor();
        for (FactMapping factMapping : simulationDescriptor.getFactMappings()) {
            FactMappingType factMappingType = factMapping.getExpressionIdentifier().getType();
            if (!hasGiven && GIVEN.equals(factMappingType)) {
                hasGiven = true;
            } else if (!hasExpect && EXPECT.equals(factMappingType)) {
                hasExpect = true;
            }
        }
        if (!hasGiven) {
            createEmptyColumn(simulationDescriptor,
                              scenarioWithIndex,
                              1,
                              GIVEN,
                              findNewIndexOfGroup(simulationDescriptor, GIVEN));
        }
        if (!hasExpect) {
            createEmptyColumn(simulationDescriptor,
                              scenarioWithIndex,
                              2,
                              EXPECT,
                              findNewIndexOfGroup(simulationDescriptor, EXPECT));
        }
    }

    private int findNewIndexOfGroup(final ScesimModelDescriptor simulationDescriptor, final FactMappingType factMappingType) {
        List<FactMapping> factMappings = simulationDescriptor.getFactMappings();
        if (GIVEN.equals(factMappingType)) {
            for (int i = 0; i < factMappings.size(); i += 1) {
                if (EXPECT.equals(factMappings.get(i).getExpressionIdentifier().getType())) {
                    return i;
                }
            }
            return factMappings.size();
        } else if (EXPECT.equals(factMappingType)) {
            return factMappings.size();
        } else {
            throw new IllegalArgumentException("This method can be invoked only with GIVEN or EXPECT as FactMappingType");
        }
    }

    private SCESIMMarshallCallback getSCESIMMarshallCallback(final RemoteCallback<String> callback) {
        return xmlString -> callback.callback(xmlString);
    }

    private DMN12UnmarshallCallback getDMN12UnmarshallCallback(final ScenarioSimulationModel toPopulate, final RemoteCallback<String> callback) {
        return dmn12 -> {
            final FactModelTuple factModelTuple = getFactModelTuple(dmn12);
            toPopulate.setSimulation(getDMNSimulation(factModelTuple));
            convertScenarioSimulationModel(toPopulate, callback);
        };
    }

    private FactModelTuple getFactModelTuple(final DMN12 dmn12) {
        SortedMap<String, FactModelTree> visibleFacts = new TreeMap<>();
        SortedMap<String, FactModelTree> hiddenFacts = new TreeMap<>();
        ErrorHolder errorHolder = new ErrorHolder();
        final JSITDefinitions definitions = dmn12.getDefinitions();
        for (JSITDRGElement jsitdrgElement : definitions.getDrgElement()) {
            if (jsitdrgElement instanceof JSITInputData) {
                try {
                    DMNType type = new DMNType(jsitdrgElement);
                    checkTypeSupport(type, errorHolder, jsitdrgElement.getName());
                    visibleFacts.put(jsitdrgElement.getName(), createTopLevelFactModelTree(jsitdrgElement.getName(), type, hiddenFacts, FactModelTree.Type.INPUT));
                } catch (Exception e) {
                    throw ExceptionUtilities.handleException(e);
                }
            } else if (jsitdrgElement instanceof JSITDecision) {
                DMNType type = new DMNType(jsitdrgElement);
                checkTypeSupport(type, errorHolder, jsitdrgElement.getName());
                try {
                    visibleFacts.put(jsitdrgElement.getName(), createTopLevelFactModelTree(jsitdrgElement.getName(), type, hiddenFacts, FactModelTree.Type.DECISION));
                } catch (Exception e) {
                    throw ExceptionUtilities.handleException(e);
                }
            }
        }
        FactModelTuple toReturn = new FactModelTuple(visibleFacts, hiddenFacts);
        errorHolder.getMultipleNestedCollection().forEach(toReturn::addMultipleNestedCollectionError);
        errorHolder.getMultipleNestedObject().forEach(toReturn::addMultipleNestedObjectError);
        return toReturn;
    }

    private Simulation getDMNSimulation(final FactModelTuple factModelTuple) {
        Simulation toReturn = new Simulation();
        ScesimModelDescriptor simulationDescriptor = toReturn.getScesimModelDescriptor();
        simulationDescriptor.addFactMapping(FactIdentifier.INDEX.getName(), FactIdentifier.INDEX, ExpressionIdentifier.INDEX);
        simulationDescriptor.addFactMapping(FactIdentifier.DESCRIPTION.getName(), FactIdentifier.DESCRIPTION, ExpressionIdentifier.DESCRIPTION);
        ScenarioWithIndex scenarioWithIndex = createScesimDataWithIndex(toReturn, ScenarioWithIndex::new);

        AtomicInteger id = new AtomicInteger(1);
        final Collection<FactModelTree> visibleFactTrees = factModelTuple.getVisibleFacts().values();
        final Map<String, FactModelTree> hiddenValues = factModelTuple.getHiddenFacts();

        visibleFactTrees.stream().sorted((a, b) -> {
            FactModelTree.Type aType = a.getType();
            FactModelTree.Type bType = b.getType();
            int inputFirstOrder = FactModelTree.Type.INPUT.equals(aType) ? -1 : 1;
            return aType.equals(bType) ? 0 : inputFirstOrder;
        }).forEach(factModelTree -> {
            FactIdentifier factIdentifier = new FactIdentifier(factModelTree.getFactName(), factModelTree.getFactName());
            FactMappingExtractor factMappingExtractor = new FactMappingExtractor(factIdentifier, scenarioWithIndex.getIndex(), id, convert(factModelTree.getType()), simulationDescriptor, scenarioWithIndex.getScesimData());
            addFactMapping(factMappingExtractor, factModelTree, new ArrayList<>(), hiddenValues);
        });

        addEmptyColumnsIfNeeded(toReturn, scenarioWithIndex);
        return toReturn;
    }

    private void addFactMapping(final FactMappingExtractor factMappingExtractor,
                                final FactModelTree factModelTree,
                                final List<String> previousSteps,
                                final Map<String, FactModelTree> hiddenValues) {
        internalAddToScenario(factMappingExtractor,
                              factModelTree,
                              previousSteps,
                              hiddenValues,
                              new HashSet<>());
    }

    private void internalAddToScenario(final FactMappingExtractor factMappingExtractor,
                                       final FactModelTree factModelTree,
                                       final List<String> readOnlyPreviousSteps,
                                       final Map<String, FactModelTree> hiddenValues,
                                       final Set<String> alreadyVisited) {

        List<String> previousSteps = new ArrayList<>(readOnlyPreviousSteps);
        // if is a simple type it generates a single column
        if (factModelTree.isSimple()) {

            String factType = factModelTree.getSimpleProperties().get(VALUE);
            factMappingExtractor.getFactMapping(factModelTree, VALUE, previousSteps, factType);
        }
        // otherwise it adds a column for each simple properties direct or nested
        else {
            for (Map.Entry<String, String> entry : factModelTree.getSimpleProperties().entrySet()) {
                String factName = entry.getKey();
                String factType = entry.getValue();

                FactMapping factMapping = factMappingExtractor.getFactMapping(factModelTree, factName, previousSteps, factType);

                if (ScenarioSimulationSharedUtils.isList(factType)) {
                    factMapping.setGenericTypes(factModelTree.getGenericTypeInfo(factName));
                }
                factMapping.addExpressionElement(factName, factType);
            }

            for (Map.Entry<String, String> entry : factModelTree.getExpandableProperties().entrySet()) {
                String factType = entry.getValue();
                FactModelTree nestedModelTree = hiddenValues.get(factType);

                if (previousSteps.isEmpty()) {
                    previousSteps.add(factModelTree.getFactName());
                }
                previousSteps.add(entry.getKey());

                if (!alreadyVisited.contains(nestedModelTree.getFactName())) {
                    alreadyVisited.add(factModelTree.getFactName());
                    internalAddToScenario(factMappingExtractor, nestedModelTree, previousSteps, hiddenValues, alreadyVisited);
                }
            }
        }
    }

    /**
     * This method is the <b>entry point</b> for <code>FactModelTree</code>. It is the one to be called from the very top level <code>DMNType</code>
     * @param factName
     * @param type
     * @param hiddenFacts
     * @param fmType
     * @return
     * @throws Exception
     */
    private FactModelTree createTopLevelFactModelTree(String factName, DMNType type, SortedMap<String, FactModelTree> hiddenFacts, FactModelTree.Type fmType) throws Exception {
        return isToBeManagedAsCollection(type) ? createFactModelTreeForCollection(new HashMap<>(), factName, type, hiddenFacts, fmType, new ArrayList<>()) : createFactModelTreeForNoCollection(new HashMap<>(), factName, factName, type.getName(), type, hiddenFacts, fmType, new ArrayList<>());
    }

    /**
     * Creates a <code>FactModelTree</code> for <code>DMNType</code> where <code>DMNType.isCollection()</code> == <code>true</code>
     * @param factName
     * @param type
     * @param hiddenFacts
     * @param fmType
     * @return
     * @throws Exception if <code>DMNType.isCollection()</code> != <code>true</code>
     */
    protected FactModelTree createFactModelTreeForCollection(Map<String, List<String>> genericTypeInfoMap, String factName, DMNType type, SortedMap<String, FactModelTree> hiddenFacts, FactModelTree.Type fmType, List<String> alreadyVisited) throws Exception {
        if (!type.isCollection() && !isToBeManagedAsCollection(type)) {
            throw new Exception();
        }
        String typeName = type.getName();
        populateGeneric(genericTypeInfoMap, VALUE, typeName);
        FactModelTree toReturn = createFactModelTreeForSimple(genericTypeInfoMap, factName, List.class.getCanonicalName(), fmType);
        if (!hiddenFacts.containsKey(typeName) && !alreadyVisited.contains(typeName)) {
            alreadyVisited.add(typeName);
            FactModelTree genericFactModelTree = createFactModelTreeForGenericType(new HashMap<>(), typeName, typeName, typeName, type, hiddenFacts, fmType, alreadyVisited);
            hiddenFacts.put(typeName, genericFactModelTree);
        }
        return toReturn;
    }

    /**
     * Creates a <code>FactModelTree</code> for <code>DMNType</code> where <code>DMNType.isCollection()</code> != <code>true</code>
     * @param propertyName
     * @param fullPropertyPath
     * @param type
     * @param hiddenFacts
     * @param fmType
     * @return
     * @throws Exception if <code>DMNType.isCollection()</code> == <code>true</code>
     */
    protected FactModelTree createFactModelTreeForNoCollection(Map<String, List<String>> genericTypeInfoMap, String factName, String propertyName, String fullPropertyPath, DMNType type, SortedMap<String, FactModelTree> hiddenFacts, FactModelTree.Type fmType, List<String> alreadyVisited) throws Exception {
        if (type.isCollection() && isToBeManagedAsCollection(type)) {
            throw new Exception();
        }
        return isToBeManagedAsComposite(type) ? createFactModelTreeForComposite(genericTypeInfoMap, propertyName, fullPropertyPath, type, hiddenFacts, fmType, alreadyVisited) : createFactModelTreeForSimple(genericTypeInfoMap, factName, type.getName(), fmType);
    }

    /**
     * Creates a <code>FactModelTree</code> for <code>DMNType</code>
     * @param propertyName
     * @param fullPropertyPath
     * @param type
     * @param hiddenFacts
     * @param fmType
     * @return
     */
    protected FactModelTree createFactModelTreeForGenericType(Map<String, List<String>> genericTypeInfoMap, String factName, String propertyName, String fullPropertyPath, DMNType type, SortedMap<String, FactModelTree> hiddenFacts, FactModelTree.Type fmType, List<String> alreadyVisited) throws Exception {
        return type.isComposite() ? createFactModelTreeForComposite(genericTypeInfoMap, propertyName, fullPropertyPath, type, hiddenFacts, fmType, alreadyVisited) : createFactModelTreeForSimple(genericTypeInfoMap, factName, type.getName(), fmType);
    }

    /**
     * Creates a <code>FactModelTree</code> for <code>DMNType</code> where <code>DMNType.isComposite()</code> != <code>true</code>.
     * Returned <code>FactModelTree</code> will have only one single property, whose name is <b>VALUE</b> and whose value is the given <b>propertyClass</b>
     * @param genericTypeInfoMap
     * @param factName
     * @param propertyClass
     * @param fmType
     * @return
     */
    private FactModelTree createFactModelTreeForSimple(Map<String, List<String>> genericTypeInfoMap, String factName, String propertyClass, FactModelTree.Type fmType) {
        Map<String, String> simpleProperties = new HashMap<>();
        FactModelTree simpleFactModelTree = new FactModelTree(factName, "", simpleProperties, genericTypeInfoMap, fmType);
        simpleFactModelTree.addSimpleProperty(VALUE, propertyClass);
        simpleFactModelTree.setSimple(true);
        return simpleFactModelTree;
    }

    /**
     * Creates a <code>FactModelTree</code> for <code>DMNType</code> where <code>DMNType.isComposite()</code> == <code>true</code>
     * @param name
     * @param fullPropertyPath
     * @param type
     * @param hiddenFacts
     * @param fmType
     * @throws Exception if <code>DMNType.isComposite()</code> != <code>true</code>
     */
    private FactModelTree createFactModelTreeForComposite(Map<String, List<String>> genericTypeInfoMap, String name, String fullPropertyPath, DMNType type, SortedMap<String, FactModelTree> hiddenFacts, FactModelTree.Type fmType, List<String> alreadyVisited) throws Exception {
        if (!type.isComposite() && !isToBeManagedAsComposite(type)) {
            throw new Exception();
        }
        Map<String, String> simpleFields = new HashMap<>();
        FactModelTree toReturn = new FactModelTree(name, "", simpleFields, genericTypeInfoMap, fmType);
        for (Map.Entry<String, DMNType> entry : type.getFields().entrySet()) {
            String expandablePropertyName = fullPropertyPath + "." + entry.getKey();
            if (isToBeManagedAsCollection(entry.getValue())) {  // if it is a collection, generate the generic and add as hidden fact a simple or composite fact model tree
                FactModelTree fact = createFactModelTreeForCollection(new HashMap<>(), entry.getKey(), entry.getValue(), hiddenFacts, FactModelTree.Type.UNDEFINED, alreadyVisited);
                simpleFields.put(entry.getKey(), List.class.getCanonicalName());
                genericTypeInfoMap.put(entry.getKey(), fact.getGenericTypeInfo(VALUE));
            } else {
                String typeName = entry.getValue().getName();
                if (entry.getValue().isComposite()) { // a complex type needs the expandable property and then in the hidden map, its fact model tree
                    if (!hiddenFacts.containsKey(typeName) && !alreadyVisited.contains(typeName)) {
                        alreadyVisited.add(typeName);
                        FactModelTree fact = createFactModelTreeForNoCollection(genericTypeInfoMap, entry.getKey(), VALUE, expandablePropertyName, entry.getValue(), hiddenFacts, FactModelTree.Type.UNDEFINED, alreadyVisited);
                        hiddenFacts.put(typeName, fact);
                    }
                    toReturn.addExpandableProperty(entry.getKey(), typeName);
                } else {  // a simple type is just name -> type
                    simpleFields.put(entry.getKey(), typeName);
                }
            }
        }
        return toReturn;
    }

    /**
     * Return <code>true</code> if the given <code>DMNType</code> has to be managed as <b>collection</b>
     * @param type
     * @return <code>true</code> if <code>DMNType.isCollection() == true</code> <b>and</b> <code>BaseDMNTypeImpl.getFeelType() != BuiltInType.UNKNOWN</code>
     */
    private boolean isToBeManagedAsCollection(DMNType type) {
        boolean toReturn = type.isCollection();
        if (toReturn) {
            Type feelType = getRootType(type);
            // BuiltInType.UNKNOWN is a special case: it is instantiated as collection but it should be considered as single for editing
            if (feelType instanceof BuiltInType && feelType.equals(BuiltInType.UNKNOWN)) {
                toReturn = false;
            }
        }
        return toReturn;
    }

    /**
     * Return <code>true</code> if the given <code>DMNType</code> has to be managed as <b>composite</b>
     * @param type
     * @return <code>true</code> if <code>DMNType.isCollection() == true</code> <b>and</b> <code>BaseDMNTypeImpl.getFeelType() != BuiltInType.UNKNOWN</code>
     */
    private boolean isToBeManagedAsComposite(DMNType type) {
        boolean toReturn = type.isComposite();
        if (toReturn) {
            Type feelType = getRootType(type);
            // BuiltInType.CONTEXT is a special case: it is instantiated as composite but has no nested fields so it should be considered as simple for editing
            if (feelType instanceof BuiltInType && feelType.equals(BuiltInType.CONTEXT)) {
                toReturn = false;
            }
        }
        return toReturn;
    }

    private Type getRootType(DMNType dmnType) {
        if (dmnType.getFeelType() instanceof BuiltInType) {
            return dmnType.getFeelType();
        } else if (dmnType.getBaseType() != null) {
            return getRootType(dmnType.getBaseType());
        }
        return dmnType.getFeelType();
    }

    /**
     * This method map the given <b>name</b> to <b>List.class.getCanonicalName()</b> inside <b>simpleFields</b>,
     * and map <b>name</b>  to a singleton list containing a newly generated <b>key</b> (path + "." + type) inside <b>genericTypeInfoMap</b>
     * @param genericTypeInfoMap
     * @param fullPropertyPath
     * @param type
     * @return
     */
    private String populateGeneric(Map<String, List<String>> genericTypeInfoMap,
                                   String fullPropertyPath,
                                   String type) {
        String genericKey = fullPropertyPath;
        genericTypeInfoMap.put(fullPropertyPath, Collections.singletonList(type));
        return genericKey;
    }

    /**
     * Recursively visit a <i>composite</i> <code>DMNType</code> to eventually detect and add errors to given <code>ErrorHolder</code>
     * @param type
     * @param errorHolder
     * @param fullPropertyPath
     */
    private void checkTypeSupport(DMNType type,
                                  ErrorHolder errorHolder,
                                  String fullPropertyPath) {
        internalCheckTypeSupport(type, false, errorHolder, fullPropertyPath, new HashSet<>());
    }

    private void internalCheckTypeSupport(DMNType type,
                                          boolean alreadyInCollection,
                                          ErrorHolder errorHolder,
                                          String fullPropertyPath,
                                          Set<String> alreadyVisited) {
        alreadyVisited.add(type.getName());
        if (type.isComposite()) {
            for (Map.Entry<String, DMNType> entry : type.getFields().entrySet()) {
                String name = entry.getKey();
                DMNType nestedType = entry.getValue();
                String nestedPath = fullPropertyPath + "." + name;
                if (alreadyInCollection && nestedType.isCollection()) {
                    errorHolder.getMultipleNestedCollection().add(nestedPath);
                }
                if (alreadyInCollection && nestedType.isComposite()) {
                    errorHolder.getMultipleNestedObject().add(nestedPath);
                }
                if (!alreadyVisited.contains(nestedType.getName())) {
                    internalCheckTypeSupport(nestedType,
                                             alreadyInCollection || nestedType.isCollection(),
                                             errorHolder,
                                             nestedPath,
                                             alreadyVisited);
                }
            }
        }
    }

    private static class FactMappingExtractor {

        private final FactIdentifier factIdentifier;
        private final int row;
        private final AtomicInteger id;
        private final FactMappingType type;
        private final ScesimModelDescriptor simulationDescriptor;
        private final AbstractScesimData abstractScesimData;

        public FactMappingExtractor(final FactIdentifier factIdentifier,
                                    final int row,
                                    final AtomicInteger id,
                                    final FactMappingType type,
                                    final ScesimModelDescriptor simulationDescriptor,
                                    final AbstractScesimData abstractScesimData) {
            this.factIdentifier = factIdentifier;
            this.row = row;
            this.id = id;
            this.type = type;
            this.simulationDescriptor = simulationDescriptor;
            this.abstractScesimData = abstractScesimData;
        }

        public FactMapping getFactMapping(final FactModelTree factModelTree,
                                          final String propertyName,
                                          final List<String> previousSteps,
                                          final String factType) {

            String factAlias = !previousSteps.isEmpty() ? previousSteps.get(0) : factModelTree.getFactName();

            ExpressionIdentifier expressionIdentifier = ExpressionIdentifier.create(row + "|" + id.getAndIncrement(), type);
            final FactMapping factMapping = simulationDescriptor.addFactMapping(factAlias, factIdentifier, expressionIdentifier);

            List<String> localPreviousStep = new ArrayList<>(previousSteps);
            localPreviousStep.add(propertyName);
            String expressionAlias = String.join(".",
                                                 localPreviousStep.size() > 1 ?
                                                         localPreviousStep.subList(1, localPreviousStep.size()) :
                                                         localPreviousStep);
            factMapping.setExpressionAlias(expressionAlias);
            factMapping.setGenericTypes(factModelTree.getGenericTypeInfo(VALUE));

            previousSteps.forEach(step -> factMapping.addExpressionElement(step, factType));

            if (previousSteps.isEmpty()) {
                factMapping.addExpressionElement(factModelTree.getFactName(), factType);
            }
            abstractScesimData.addMappingValue(factIdentifier, expressionIdentifier, null);

            return factMapping;
        }
    }

    private static class ErrorHolder {

        Set<String> multipleNestedObject = new TreeSet<>();
        Set<String> multipleNestedCollection = new TreeSet<>();

        public Set<String> getMultipleNestedObject() {
            return multipleNestedObject;
        }

        public Set<String> getMultipleNestedCollection() {
            return multipleNestedCollection;
        }
    }

    private static class DMNType {

        private String namespace;
        private String name;
        private String id;
        private boolean collection;
        private boolean composite;
        private Map<String, DMNType> fields;
        private Type feelType;
        private DMNType baseType;

        public DMNType(JSITDRGElement jsitdrgElement) {
        }

        public String getNamespace() {
            return namespace;
        }

        public String getName() {
            return name;
        }

        public String getId() {
            return id;
        }

        public boolean isCollection() {
            return collection;
        }

        public boolean isComposite() {
            return composite;
        }

        public Map<String, DMNType> getFields() {
            return fields;
        }

        public Type getFeelType() {
            return feelType;
        }

        public DMNType getBaseType() {
            return baseType;
        }
    }
}

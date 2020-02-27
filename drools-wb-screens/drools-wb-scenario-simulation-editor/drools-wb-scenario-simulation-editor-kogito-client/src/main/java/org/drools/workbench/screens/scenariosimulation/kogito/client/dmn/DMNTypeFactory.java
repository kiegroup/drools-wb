/*
 * Copyright 2020 Red Hat, Inc. and/or its affiliates.
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
package org.drools.workbench.screens.scenariosimulation.kogito.client.dmn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jsinterop.base.Js;
import org.drools.workbench.screens.scenariosimulation.kogito.client.dmn.feel.BuiltInType;
import org.kie.workbench.common.dmn.webapp.kogito.marshaller.js.model.dmn12.JSITItemDefinition;

/**
 * Utility factory for <code>DMNType</code>
 */
public class DMNTypeFactory {

    private DMNTypeFactory() {
        // Utility class, not instantiable
    }

    /**
     * This method creates a <code>ClientDMNType</code> object of a given <code>JSITItemDefinition</code> object.
     * To correctly work, it requires a sorted <code>dmnTypesMap</code>, in order to process items which don't refer to
     * a "super items" BEFORE items with a refer to another item.
     * @param itemDefinition
     * @param namespace
     * @param dmnTypesMap
     * @return
     */
    public static ClientDMNType getDMNType(final JSITItemDefinition itemDefinition,
                                           final String namespace,
                                           final Map<String, ClientDMNType> dmnTypesMap) {
        Map<String, ClientDMNType> fields = new HashMap<>();
        /* First Step: inheriting fields defined from item's typeRef, which represent its "super item".
        *  This is required when a typeRef is defined for current itemDefinition AND it's not a collection */
        if (itemDefinition.getTypeRef() != null && !itemDefinition.getIsCollection()) {
            final ClientDMNType clientDmnType = dmnTypesMap.get(itemDefinition.getTypeRef());
            if (clientDmnType != null) {
                /* Fields are added if the referred "super item" it's a composite (eg. it holds user defined fields
                 * and it's not a collection */
                if (clientDmnType.isComposite() && !clientDmnType.isCollection()) {
                    fields.putAll(clientDmnType.getFields());
                }
            } else {
                throw new IllegalStateException(
                        "Item: " + itemDefinition.getName() + " refers to typeRef: " + itemDefinition.getTypeRef() + "which can't be found.");
            }
        }
        /* Second Step: retrieving fields defined into current itemDefinition */
        List<JSITItemDefinition> jsitItemDefinitions = itemDefinition.getItemComponent();
        if (jsitItemDefinitions != null && !jsitItemDefinitions.isEmpty()) {
            for (int i = 0; i < jsitItemDefinitions.size(); i++) {
                final JSITItemDefinition jsitItemDefinition = Js.uncheckedCast(jsitItemDefinitions.get(i));
                final String typeRef = jsitItemDefinition.getTypeRef();
                if (!dmnTypesMap.containsKey(typeRef)) {
                    ClientDMNType nestedClientDMNType = getDMNType(jsitItemDefinition, namespace, dmnTypesMap);
                    dmnTypesMap.put(typeRef, nestedClientDMNType);
                }
                fields.put(jsitItemDefinition.getName(), dmnTypesMap.get(typeRef));
            }
        }
        return new ClientDMNType(namespace,
                                 itemDefinition.getName(),
                                 itemDefinition.getId(),
                                 itemDefinition.getIsCollection(),
                                 !fields.isEmpty(),
                                 fields,
                                 null);
    }


    public static class ClientDMNType {

        private String namespace;
        private String name;
        private String id;
        private boolean collection;
        private boolean composite;
        private Map<String, ClientDMNType> fields;
        private BuiltInType feelType;

        public ClientDMNType(String namespace, String name, String id, boolean isCollection, BuiltInType feelType) {
            this.namespace = namespace;
            this.name = name;
            this.id = id;
            this.collection = isCollection;
            this.feelType = feelType;
        }

        public ClientDMNType(String namespace, String name, String id, boolean isCollection, boolean isComposite, Map<String, ClientDMNType> fields, BuiltInType feelType) {
            this(namespace, name, id, isCollection, feelType);
            this.fields = fields;
            this.composite = isComposite;
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

        public Map<String, ClientDMNType> getFields() {
            return fields;
        }

        public BuiltInType getFeelType() {
            return feelType;
        }
    }
}

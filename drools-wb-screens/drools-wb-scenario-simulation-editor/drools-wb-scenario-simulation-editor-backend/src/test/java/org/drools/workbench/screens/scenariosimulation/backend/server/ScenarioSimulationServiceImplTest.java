/*
 * Copyright 2018 Red Hat, Inc. and/or its affiliates.
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
package org.drools.workbench.screens.scenariosimulation.backend.server;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Named;

import org.drools.scenariosimulation.api.model.Background;
import org.drools.scenariosimulation.api.model.ScenarioSimulationModel;
import org.drools.scenariosimulation.api.model.Settings;
import org.drools.scenariosimulation.api.model.Simulation;
import org.drools.scenariosimulation.backend.exceptions.ImpossibleToFindDMNException;
import org.drools.scenariosimulation.backend.runner.ScenarioJunitActivator;
import org.drools.workbench.screens.scenariosimulation.backend.server.util.ScenarioSimulationBuilder;
import org.drools.workbench.screens.scenariosimulation.service.DMNTypeService;
import org.guvnor.common.services.backend.config.SafeSessionInfo;
import org.guvnor.common.services.backend.metadata.MetadataServerSideService;
import org.guvnor.common.services.backend.util.CommentedOptionFactory;
import org.guvnor.common.services.project.model.Dependencies;
import org.guvnor.common.services.project.model.Dependency;
import org.guvnor.common.services.project.model.GAV;
import org.guvnor.common.services.project.model.POM;
import org.guvnor.common.services.project.model.Package;
import org.guvnor.common.services.project.service.POMService;
import org.guvnor.common.services.shared.metadata.model.Metadata;
import org.jboss.errai.security.shared.api.identity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.workbench.common.services.backend.service.KieServiceOverviewLoader;
import org.kie.workbench.common.services.shared.project.KieModule;
import org.kie.workbench.common.services.shared.project.KieModuleService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.uberfire.backend.vfs.Path;
import org.uberfire.backend.vfs.PathFactory;
import org.uberfire.ext.editor.commons.backend.service.SaveAndRenameServiceImpl;
import org.uberfire.ext.editor.commons.backend.version.PathResolver;
import org.uberfire.ext.editor.commons.service.CopyService;
import org.uberfire.ext.editor.commons.service.DeleteService;
import org.uberfire.ext.editor.commons.service.RenameService;
import org.uberfire.io.IOService;
import org.uberfire.java.nio.file.FileAlreadyExistsException;

import static org.drools.scenariosimulation.api.model.ScenarioSimulationModel.Type;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ScenarioSimulationServiceImplTest {

    private static final String ORG_DROOLS = "org.drools";
    private static final String ORG_KIE = "org.kie";
    private static final String ORG_JBPM = "org.jbpm";

    @Mock
    protected KieServiceOverviewLoader overviewLoaderMock;
    @Mock
    protected MetadataServerSideService metadataServiceMock;
    @Mock
    @Named("ioStrategy")
    private IOService ioServiceMock;
    @Mock
    private CommentedOptionFactory commentedOptionFactoryMock;
    @Mock
    private SaveAndRenameServiceImpl<ScenarioSimulationModel, Metadata> saveAndRenameServiceMock;
    @Mock
    private PathResolver pathResolverMock;
    @Mock
    private DeleteService deleteServiceMock;
    @Mock
    private RenameService renameServiceMock;
    @Mock
    private CopyService copyServiceMock;
    @Mock
    private User userMock;
    @Mock
    private ScenarioRunnerServiceImpl scenarioRunnerServiceMock;
    @Mock
    private POMService pomServiceMock;
    @Mock
    private org.uberfire.java.nio.file.Path activatorPathMock;
    @Mock
    private KieModuleService kieModuleServiceMock;
    @Mock
    private KieModule kieModuleMock;
    @Mock
    private POM projectPomMock;
    @Mock
    private GAV gavMock;
    @Mock
    private Dependencies dependenciesMock;
    @Mock
    private Package packageMock;
    @Mock
    private ScenarioSimulationBuilder scenarioSimulationBuilderMock;
    @Mock
    private DMNTypeService dmnTypeServiceMock;

    @InjectMocks
    private ScenarioSimulationServiceImpl service = new ScenarioSimulationServiceImpl(mock(SafeSessionInfo.class)) {
        @Override
        protected ScenarioSimulationModel unmarshalInternal(String content) {
            Simulation simulation = new Simulation();
            Settings settings = new Settings();
            settings.setType(Type.DMN);
            ScenarioSimulationModel toReturn = new ScenarioSimulationModel();
            toReturn.setSimulation(simulation);
            toReturn.setSettings(settings);
            return toReturn;
        }
    };

    private Path path = PathFactory.newPath("contextPath", "file:///contextPath");

    @Before
    public void setup() {
        Set<Package> testPackages = new HashSet<>();
        Package testPackage = new Package(path, path, path, path, path, "Test", "", "");
        testPackages.add(testPackage);
        when(kieModuleServiceMock.resolveModule(any())).thenReturn(kieModuleMock);
        when(kieModuleServiceMock.resolvePackages(Mockito.<KieModule>any())).thenReturn(testPackages);
        when(kieModuleServiceMock.newPackage(any(), any())).thenReturn(testPackage);
        when(kieModuleServiceMock.resolveDefaultPackage(any())).thenReturn(testPackage);

        when(kieModuleServiceMock.resolveModule(any())).thenReturn(kieModuleMock);
        when(kieModuleMock.getPom()).thenReturn(projectPomMock);
        when(projectPomMock.getGav()).thenReturn(gavMock);
        when(gavMock.getGroupId()).thenReturn("Test");
        when(projectPomMock.getDependencies()).thenReturn(dependenciesMock);
        when(dependenciesMock.iterator()).thenReturn(new Dependencies().iterator());
        when(ioServiceMock.exists(any())).thenReturn(false);
        when(packageMock.getPackageTestSrcPath()).thenReturn(path);
        when(scenarioSimulationBuilderMock.createSimulation(any(), any(), any())).thenReturn(new Simulation());
        when(scenarioSimulationBuilderMock.createBackground(any(), any(), any())).thenReturn(new Background());
        when(scenarioSimulationBuilderMock.createSettings(any(), any(), any())).thenReturn(new Settings());
        service.scenarioSimulationBuilder = scenarioSimulationBuilderMock;
    }

    @Test
    public void init() {
        service.init();

        verify(saveAndRenameServiceMock).init(service);
    }

    @Test
    public void delete() {
        service.delete(path,
                       "Removing this");
        verify(deleteServiceMock).delete(path,
                                         "Removing this");
    }

    @Test
    public void rename() {
        service.rename(path,
                       "newName.scesim",
                       "comment");
        verify(renameServiceMock).rename(path,
                                         "newName.scesim",
                                         "comment");
    }

    @Test
    public void copy() throws Exception {
        service.copy(path,
                     "newName.scesim",
                     "comment");
        verify(copyServiceMock).copy(path,
                                     "newName.scesim",
                                     "comment");
    }

    @Test
    public void copyToDirectory() {
        final Path folder = mock(Path.class);
        service.copy(path,
                     "newName.scesim",
                     folder,
                     "comment");
        verify(copyServiceMock).copy(path,
                                     "newName.scesim",
                                     folder,
                                     "comment");
    }

    @Test
    public void saveAndRename() {
        final Metadata metadata = mock(Metadata.class);
        final ScenarioSimulationModel model = new ScenarioSimulationModel();
        service.saveAndRename(path,
                              "newName.scesim",
                              metadata,
                              model,
                              "comment");
        verify(saveAndRenameServiceMock).saveAndRename(path,
                                                       "newName.scesim",
                                                       metadata,
                                                       model,
                                                       "comment");
    }

    @Test
    public void save() {
        final Path returnPath = service.save(this.path,
                                             new ScenarioSimulationModel(),
                                             new Metadata(),
                                             "Commit comment");

        assertNotNull(returnPath);
        verify(ioServiceMock).write(any(org.uberfire.java.nio.file.Path.class),
                                    anyString(),
                                    anyMap(),
                                    any());
    }

    @Test
    public void createRULEScenario() {
        doReturn(false).when(ioServiceMock).exists(any());
        ScenarioSimulationModel model = new ScenarioSimulationModel();
        assertNull(model.getSimulation());
        assertNull(model.getBackground());
        assertNull(model.getSettings());
        final Path returnPath = service.create(this.path,
                                               "test.scesim",
                                               model,
                                               "Commit comment",
                                               Type.RULE,
                                               null);

        assertNotNull(returnPath);
        assertNotNull(model.getSimulation());
        assertNotNull(model.getBackground());
        assertNotNull(model.getSettings());
        verify(ioServiceMock, times(2)).write(any(org.uberfire.java.nio.file.Path.class),
                                              anyString(),
                                              any());
    }

    @Test
    public void createDMNScenario() {
        doReturn(false).when(ioServiceMock).exists(any());
        ScenarioSimulationModel model = new ScenarioSimulationModel();
        assertNull(model.getSimulation());
        assertNull(model.getBackground());
        assertNull(model.getSettings());
        final Path returnPath = service.create(this.path,
                                               "test.scesim",
                                               model,
                                               "Commit comment",
                                               Type.DMN,
                                               "test");

        assertNotNull(returnPath);
        assertNotNull(model.getSimulation());
        assertNotNull(model.getBackground());
        assertNotNull(model.getSettings());
        verify(ioServiceMock, times(2)).write(any(org.uberfire.java.nio.file.Path.class),
                                              anyString(),
                                              any());
    }

    @Test(expected = FileAlreadyExistsException.class)
    public void createFileExists() throws Exception {
        doReturn(true).when(ioServiceMock).exists(any());
        ScenarioSimulationModel model = new ScenarioSimulationModel();
        service.create(this.path,
                       "test.scesim",
                       model,
                       "Commit comment");
    }

    @Test
    public void runScenario() throws Exception {
        doReturn("test userMock").when(userMock).getIdentifier();

        final Path path = mock(Path.class);
        Simulation simulation = new Simulation();
        Settings settings = new Settings();
        Background background = new Background();

        service.runScenario(path, simulation.getScesimModelDescriptor(), simulation.getScenarioWithIndex(), settings, background);

        verify(scenarioRunnerServiceMock).runTest("test userMock",
                                                  path,
                                                  simulation.getScesimModelDescriptor(),
                                                  simulation.getScenarioWithIndex(),
                                                  settings,
                                                  background);
    }

    @Test
    public void createActivatorIfNotExistTest() {
        service.createActivatorIfNotExist(path);

        verify(ioServiceMock, times(1))
                .write(any(org.uberfire.java.nio.file.Path.class),
                       anyString(),
                       any());

        reset(ioServiceMock);
        when(ioServiceMock.exists(any())).thenReturn(true);
        service.createActivatorIfNotExist(path);

        verify(ioServiceMock, never())
                .write(any(org.uberfire.java.nio.file.Path.class),
                       anyString(),
                       any());
    }

    @Test
    public void getOrCreateJunitActivatorPackageTest() {
        service.getOrCreateJunitActivatorPackage(kieModuleMock);
        verify(kieModuleServiceMock, times(1)).newPackage(any(), anyString());

        reset(kieModuleServiceMock);
        when(kieModuleServiceMock.resolveDefaultPackage(any())).thenReturn(packageMock);
        when(kieModuleServiceMock.resolvePackage(any())).thenReturn(packageMock);
        service.getOrCreateJunitActivatorPackage(kieModuleMock);
        verify(kieModuleServiceMock, never()).newPackage(any(), anyString());
    }

    @Test
    public void removeOldActivatorIfExistsTest() {
        org.uberfire.java.nio.file.Path existingActivatorPathMock = mock(org.uberfire.java.nio.file.Path.class);
        service.removeOldActivatorIfExists(existingActivatorPathMock, kieModuleMock);
        verify(ioServiceMock, times(2)).deleteIfExists(any());

        reset(ioServiceMock);
        when(kieModuleServiceMock.resolvePackages(any(KieModule.class))).thenReturn(new HashSet<>());
        service.removeOldActivatorIfExists(existingActivatorPathMock, kieModuleMock);
        verify(ioServiceMock, times(1)).deleteIfExists(existingActivatorPathMock);
    }

    @Test
    public void ensureDependenciesTest() {
        service.ensureDependencies(kieModuleMock);

        verify(pomServiceMock, times(1)).save(any(),
                                              any(POM.class),
                                              any(),
                                              anyString());

        reset(pomServiceMock);
        when(dependenciesMock.containsDependency(any())).thenReturn(true);

        service.ensureDependencies(kieModuleMock);

        verify(pomServiceMock, never()).save(any(),
                                             any(POM.class),
                                             any(),
                                             anyString());
    }

    @Test
    public void removeFromPomIfNecessaryTest() {
        String groupId = "groupId";
        String artifactId = "artifactId";
        String version = "version";
        GAV toRemove = new GAV(groupId, artifactId, version);
        Dependencies dependencies = new Dependencies();
        dependencies.add(new Dependency(toRemove));

        assertTrue(service.removeFromPomIfNecessary(dependencies, toRemove));

        assertFalse(service.removeFromPomIfNecessary(dependencies, toRemove));

        dependencies.add(new Dependency(new GAV(groupId, artifactId, null)));

        assertTrue(service.removeFromPomIfNecessary(dependencies, toRemove));

        assertFalse(service.removeFromPomIfNecessary(dependencies, toRemove));
    }

    @Test
    public void editPomIfNecessaryTest() {
        String groupId = "groupId";
        String artifactId = "artifactId";
        String version = "version";
        GAV gav = new GAV(groupId, artifactId, version);
        Dependencies dependencies = new Dependencies();

        assertTrue(service.editPomIfNecessary(dependencies, gav));

        assertFalse(service.editPomIfNecessary(dependencies, gav));
    }

    @Test
    public void getActivatorPathTest() {
        assertTrue(service.getActivatorPath(packageMock).endsWith(ScenarioJunitActivator.ACTIVATOR_CLASS_NAME + ".java"));
    }

    @Test
    public void load() {
        ScenarioSimulationModel model = service.load(path);

        assertEquals(Type.DMN, model.getSettings().getType());
        verify(dmnTypeServiceMock, times(1)).initializeNameAndNamespace(any(), any(), any());

        doThrow(new ImpossibleToFindDMNException("")).when(dmnTypeServiceMock).initializeNameAndNamespace(any(), any(), any());

        try {
            service.load(path);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void checkDependency() {
        GAV scesimApiDependency = new GAV(ORG_DROOLS, "drools-scenario-simulation-api", null);
        GAV scesimBackendDependency = new GAV(ORG_DROOLS, "drools-scenario-simulation-backend", null);
        GAV droolsCompilerDependency = new GAV(ORG_DROOLS, "drools-compiler", null);
        GAV dtableDependency = new GAV(ORG_DROOLS, "drools-decisiontables", null);
        GAV gtableDependency = new GAV(ORG_DROOLS, "drools-workbench-models-guided-dtable", null);
        GAV jbpmBpmn2Dependency = new GAV(ORG_JBPM, "jbpm-bpmn2", null);
        GAV dmnFeelDependency = new GAV(ORG_KIE, "kie-dmn-feel", null);
        GAV dmnApi2Dependency = new GAV(ORG_KIE, "kie-dmn-api", null);
        GAV dmnCoreDependency = new GAV(ORG_KIE, "kie-dmn-core", null);

        List<GAV> dependencies = service.getDependencies(null);

        assertEquals(9, dependencies.size());
        assertTrue(dependencies.contains(scesimApiDependency));
        assertTrue(dependencies.contains(scesimBackendDependency));
        assertTrue(dependencies.contains(droolsCompilerDependency));
        assertTrue(dependencies.contains(gtableDependency));
        assertTrue(dependencies.contains(dtableDependency));
        assertTrue(dependencies.contains(jbpmBpmn2Dependency));
        assertTrue(dependencies.contains(dmnFeelDependency));
        assertTrue(dependencies.contains(dmnApi2Dependency));
        assertTrue(dependencies.contains(dmnCoreDependency));
    }
}
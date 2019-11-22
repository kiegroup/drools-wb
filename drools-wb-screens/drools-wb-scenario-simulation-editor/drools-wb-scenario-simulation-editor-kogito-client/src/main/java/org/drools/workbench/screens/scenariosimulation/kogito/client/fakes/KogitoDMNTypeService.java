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
package org.drools.workbench.screens.scenariosimulation.kogito.client.fakes;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.drools.scenariosimulation.api.model.Settings;
import org.drools.workbench.screens.scenariosimulation.model.typedescriptor.FactModelTree;
import org.drools.workbench.screens.scenariosimulation.model.typedescriptor.FactModelTuple;
import org.drools.workbench.screens.scenariosimulation.service.DMNTypeService;
import org.uberfire.backend.vfs.Path;

public class KogitoDMNTypeService implements DMNTypeService {

    private static final SortedMap<String, FactModelTree> VISIBLE_FACTS = new TreeMap<>();
    private static final SortedMap<String, FactModelTree> HIDDEN_FACTS = new TreeMap<>();

    private static final Map<String, String> addressMap = new HashMap<String, String>() {
        {
            put("city", "java.lang.String");
            put("country", "java.lang.String");
        }
    };

    private static final Map<String, String> personMap = new HashMap<String, String>() {
        {
            put("age", "java.lang.Integer");
            put("address", "java.util.List");
        }
    };
    private static final Map<String, List<String>> personGenericMap = new HashMap<String, List<String>>() {
        {
            put("address", Collections.singletonList("tPlace"));
        }
    };

    static {
        VISIBLE_FACTS.put("person", getFactModelTree("tPerson", personMap, personGenericMap));
        HIDDEN_FACTS.put("address", getFactModelTree("tPlace", addressMap, new HashMap<>()));
    }

    @Override
    public FactModelTuple retrieveFactModelTuple(Path path, String dmnPath) {
        return new FactModelTuple(VISIBLE_FACTS, HIDDEN_FACTS);
    }

    @Override
    public void initializeNameAndNamespace(Settings settings, Path path, String dmnPath) {

    }

    private static FactModelTree getFactModelTree(String factName,  Map<String, String> simpleProperties, Map<String, List<String>> genericTypesMap) {
        return new FactModelTree(factName, "", simpleProperties, genericTypesMap);
    }

}

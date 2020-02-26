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

    public static DMNType getDMNType(final JSITItemDefinition itemDefinition,
                                     final String namespace,
                                     final Map<String, DMNType> dmnTypesMap) {
        List<JSITItemDefinition> jsitItemDefinitions = itemDefinition.getItemComponent();
        if (jsitItemDefinitions != null && !jsitItemDefinitions.isEmpty()) {
            Map<String, DMNType> fields = new HashMap<>();
            for (int i = 0; i < jsitItemDefinitions.size(); i++) {
                final JSITItemDefinition jsitItemDefinition = Js.uncheckedCast(jsitItemDefinitions.get(i));
                final String typeRef = jsitItemDefinition.getTypeRef();
                if (!dmnTypesMap.containsKey(typeRef)) {
                    DMNType nestedDMNType = getDMNType(jsitItemDefinition, namespace, dmnTypesMap);
                    dmnTypesMap.put(typeRef, nestedDMNType);
                }
                fields.put(jsitItemDefinition.getName(), dmnTypesMap.get(typeRef));
            }
            return new DMNType(namespace, itemDefinition.getName(), itemDefinition.getId(), itemDefinition.getIsCollection(), true, fields, null);
        } else {
            return new DMNType(namespace, itemDefinition.getName(), itemDefinition.getId(), itemDefinition.getIsCollection(), null);
        }
    }


    public static class DMNType {

        private String namespace;
        private String name;
        private String id;
        private boolean collection;
        private boolean composite;
        private Map<String, DMNType> fields;
        private BuiltInType feelType;

        public DMNType(String namespace, String name, String id, boolean isCollection, BuiltInType feelType) {
            this.namespace = namespace;
            this.name = name;
            this.id = id;
            this.collection = isCollection;
            this.feelType = feelType;
        }

        public DMNType(String namespace, String name, String id, boolean isCollection, boolean isComposite, Map<String, DMNType> fields, BuiltInType feelType) {
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

        public Map<String, DMNType> getFields() {
            return fields;
        }

        public BuiltInType getFeelType() {
            return feelType;
        }
    }
}

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
package org.drools.workbench.screens.scenariosimulation.webapp.backend;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;

import org.jboss.errai.security.shared.api.Group;
import org.jboss.errai.security.shared.api.Role;
import org.jboss.errai.security.shared.api.identity.User;


@ApplicationScoped
public class UserMock implements User {

    private Map<String, String> properties = new HashMap<>();
    private Set<Role> roles = new HashSet<>();
    private Set<Group> groups = new HashSet<>();

    @Override
    public String getIdentifier() {
        return "admin";
    }

    @Override
    public Set<Role> getRoles() {
        return roles;
    }

    @Override
    public Set<Group> getGroups() {
        return groups;
    }

    @Override
    public Map<String, String> getProperties() {
        return properties;
    }

    @Override
    public void setProperty(String name, String value) {
        properties.put(name, value);
    }

    @Override
    public void removeProperty(String name) {
        properties.remove(name);
    }

    @Override
    public String getProperty(String name) {
        return properties.get(name);
    }
}

/*
 * Copyright 2012 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.drools.workbench.backend.server;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.guvnor.common.services.shared.identity.RequestIdentityProvider;
import org.uberfire.security.Identity;
import org.uberfire.security.Role;

@ApplicationScoped
public class UberFireIdentityProvider implements RequestIdentityProvider {

    @Inject
    private Identity identity;

    @Override
    public String getName() {
        try {
            return identity.getName();
        } catch ( Exception e ) {
            return "admin";
        }
    }

    @Override
    public List<String> getRoles() {
        List<String> roles = new ArrayList<String>();

        List<Role> ufRoles = identity.getRoles();
        for ( Role role : ufRoles ) {
            roles.add( role.getName() );
        }

        return roles;
    }

}
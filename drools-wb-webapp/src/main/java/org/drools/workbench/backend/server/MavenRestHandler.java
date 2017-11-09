/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
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
package org.drools.workbench.backend.server;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.kie.workbench.common.services.backend.builder.af.KieAFBuilder;

import org.kie.workbench.common.services.backend.builder.af.impl.DefaultKieAFBuilder;
import org.kie.workbench.common.services.backend.compiler.impl.kie.KieCompilationResponse;

@Path("/maven/3.3.9/")
@RequestScoped
public class MavenRestHandler {

    public MavenRestHandler(){}

    private static int count = 0;

    @POST
    @Produces("multipart/mixed")
    @Consumes("text/plain")
    public String post(@HeaderParam("project") String projectRepo, @HeaderParam("mavenrepo") String mavenRepo) throws Exception {
        KieAFBuilder builder = new DefaultKieAFBuilder(projectRepo, mavenRepo);
        KieCompilationResponse response = builder.build();
        /*MultipartFormDataOutput output = new MultipartFormDataOutput();
        output.addPart(response, MediaType.MULTIPART_FORM_DATA_TYPE);
        return output;*/
        return "Hello";
    }

    @GET
    @Produces("text/plain")
    public String get() {
        return Integer.toString(count);
    }

    @PUT
    @Consumes("text/plain")
    public void put(String content) throws Exception {
        System.out.println("IN PUT!!!!");
        System.out.println("******* countdown complete ****");
        count++;
    }
}

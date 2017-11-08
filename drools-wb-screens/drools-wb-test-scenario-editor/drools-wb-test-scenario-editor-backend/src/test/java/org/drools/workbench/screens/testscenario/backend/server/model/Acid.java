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

package org.drools.workbench.screens.testscenario.backend.server.model;


public class Acid implements java.io.Serializable {

	static final long serialVersionUID = 1L;

	private java.lang.String name;
	private java.util.List<org.drools.workbench.screens.testscenario.backend.server.model.Molecule> molecules;

	public Acid() {
	}

	public java.lang.String getName() {
		return this.name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.util.List<org.drools.workbench.screens.testscenario.backend.server.model.Molecule> getMolecules() {
		return this.molecules;
	}

	public void setMolecules(
			java.util.List<org.drools.workbench.screens.testscenario.backend.server.model.Molecule> molecules) {
		this.molecules = molecules;
	}

	public Acid(
			java.lang.String name,
			java.util.List<org.drools.workbench.screens.testscenario.backend.server.model.Molecule> molecules) {
		this.name = name;
		this.molecules = molecules;
	}

}
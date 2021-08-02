/*
 * Copyright 2021 Red Hat, Inc. and/or its affiliates.
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

/**
 * This class was automatically generated by the data modeler tool.
 */

public class Borrower implements java.io.Serializable {

    static final long serialVersionUID = 1L;

    private Integer ssn;
    private String lastName;
    private Integer oldestOpenLoan;

    public Borrower() {
    }

    public Integer getSsn() {
        return this.ssn;
    }

    public void setSsn(Integer ssn) {
        this.ssn = ssn;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getOldestOpenLoan() {
        return this.oldestOpenLoan;
    }

    public void setOldestOpenLoan(Integer oldestOpenLoan) {
        this.oldestOpenLoan = oldestOpenLoan;
    }

    public Borrower(Integer ssn, String lastName,
                    Integer oldestOpenLoan) {
        this.ssn = ssn;
        this.lastName = lastName;
        this.oldestOpenLoan = oldestOpenLoan;
    }
}
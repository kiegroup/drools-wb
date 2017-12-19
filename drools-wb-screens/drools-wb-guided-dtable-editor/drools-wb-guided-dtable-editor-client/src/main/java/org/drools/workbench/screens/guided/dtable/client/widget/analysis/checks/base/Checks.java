/*
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
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

package org.drools.workbench.screens.guided.dtable.client.widget.analysis.checks.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gwt.core.client.Scheduler;
import org.drools.workbench.screens.guided.dtable.client.widget.analysis.CancellableRepeatingCommand;
import org.drools.workbench.screens.guided.dtable.client.widget.analysis.RowInspector;
import org.drools.workbench.screens.guided.dtable.client.widget.analysis.Status;
import org.drools.workbench.screens.guided.dtable.client.widget.analysis.checks.DetectConflictingRowsCheck;
import org.drools.workbench.screens.guided.dtable.client.widget.analysis.checks.DetectDeficientRowsCheck;
import org.drools.workbench.screens.guided.dtable.client.widget.analysis.checks.DetectImpossibleMatchCheck;
import org.drools.workbench.screens.guided.dtable.client.widget.analysis.checks.DetectMissingActionCheck;
import org.drools.workbench.screens.guided.dtable.client.widget.analysis.checks.DetectMissingConditionCheck;
import org.drools.workbench.screens.guided.dtable.client.widget.analysis.checks.DetectMultipleValuesForOneActionCheck;
import org.drools.workbench.screens.guided.dtable.client.widget.analysis.checks.DetectRedundantActionCheck;
import org.drools.workbench.screens.guided.dtable.client.widget.analysis.checks.DetectRedundantConditionsCheck;
import org.drools.workbench.screens.guided.dtable.client.widget.analysis.checks.DetectRedundantRowsCheck;
import org.uberfire.mvp.Command;
import org.uberfire.mvp.ParameterizedCommand;

public class Checks {

    //RowInspector = a row's inspector; Set<Check> = Checks for the row
    private final Map<RowInspector, Set<Check>> set = new HashMap<RowInspector, Set<Check>>();

    //RowInspector = a row's inspector; Set<Check> = Checks for the row
    private final Map<RowInspector, Set<Check>> rechecks = new HashMap<RowInspector, Set<Check>>();

    //RowInspector = a row's inspector; Map<RowInspector, List<Set<Check>>> = RowInspectors referencing the key together with their Checks
    private final Map<RowInspector, Map<RowInspector, Set<Check>>> reciprocalRowInspectors = new HashMap<RowInspector, Map<RowInspector, Set<Check>>>();

    private CancellableRepeatingCommand activeAnalysis;

    /**
     * Run analysis without feedback
     */
    public void run() {
        run(null,
            null);
    }

    /**
     * Run analysis with feedback
     * @param onStatus Command executed repeatedly receiving status update
     * @param onCompletion Command executed on completion
     */
    public void run(final ParameterizedCommand<Status> onStatus,
                    final Command onCompletion) {

        //Ensure active analysis is cancelled
        cancelExistingAnalysis();

        //If there are no rows to check simply return
        if (set.isEmpty()) {
            if (onCompletion != null) {
                onCompletion.execute();
                return;
            }
        }

        final CancellableRepeatingCommand command = new ChecksRepeatingCommand(set,
                                                                               rechecks,
                                                                               onStatus,
                                                                               onCompletion);
        doRun(command);
    }

    //Override for tests where we do not want to perform checks using a Scheduled RepeatingCommand
    protected void doRun(final CancellableRepeatingCommand command) {
        activeAnalysis = command;
        Scheduler.get().scheduleIncremental(command);
    }

    public void update(final RowInspector oldRowInspector,
                       final RowInspector newRowInspector) {
        //Ensure active analysis is cancelled
        cancelExistingAnalysis();

        //Ensure newRowInspector has the oldRowInspector's rowIndex
        newRowInspector.setRowIndex(oldRowInspector.getRowIndex());

        //Remove the oldRowInspector and add the newRowInspector
        remove(oldRowInspector);
        add(newRowInspector);
    }

    public Collection<Check> get(final RowInspector rowInspector) {
        return set.get(rowInspector);
    }

    public boolean isEmpty() {
        return set.isEmpty();
    }

    public void add(final RowInspector rowInspector) {
        //Ensure active analysis is cancelled
        cancelExistingAnalysis();

        //Add new checks
        addSingleRowChecks(rowInspector);
        addPairRowChecks(rowInspector);

        if (!this.reciprocalRowInspectors.containsKey(rowInspector)) {
            this.reciprocalRowInspectors.put(rowInspector,
                                             new HashMap<RowInspector, Set<Check>>());
        }

        //Ensure referenced RowInspectors have checks created referencing the new RowInspector, if applicable
        for (Check check : get(rowInspector)) {
            if (check instanceof PairCheck) {

                final RowInspector otherRowInspector = ((PairCheck) check).getOther();

                addIfMissing(rowInspector, otherRowInspector);
                addIfMissing(otherRowInspector, rowInspector);

                this.reciprocalRowInspectors.get(otherRowInspector).get(rowInspector).add(check);


                List<Check> collection = makePairRowChecks(otherRowInspector,
                                                           rowInspector);
                if (set.containsKey(otherRowInspector)) {
                    set.get(otherRowInspector).addAll(collection);
                }


                reciprocalRowInspectors.get(rowInspector).get(otherRowInspector).addAll(collection);
            }
        }

    }

    private void addIfMissing(final RowInspector rowInspector,
                              final RowInspector otherRowInspector) {
        if (!this.reciprocalRowInspectors.containsKey(otherRowInspector)) {
            this.reciprocalRowInspectors.put(otherRowInspector,
                                             new HashMap<RowInspector, Set<Check>>());
        }

        if (!this.reciprocalRowInspectors.get(otherRowInspector).containsKey(rowInspector)) {
            this.reciprocalRowInspectors.get(otherRowInspector).put(rowInspector,
                                                                    new HashSet<Check>());
        }
    }

    private void addSingleRowChecks(final RowInspector rowInspector) {
        final List<Check> checks = makeSingleRowChecks(rowInspector);
        assertChecks(rowInspector,
                     checks);
    }

    protected List<Check> makeSingleRowChecks(final RowInspector rowInspector) {
        final ArrayList<Check> checkList = new ArrayList<Check>();
        checkList.add(new DetectImpossibleMatchCheck(rowInspector));
        checkList.add(new DetectMultipleValuesForOneActionCheck(rowInspector));
        checkList.add(new DetectMissingActionCheck(rowInspector));
        checkList.add(new DetectMissingConditionCheck(rowInspector));
        checkList.add(new DetectDeficientRowsCheck(rowInspector));
        checkList.add(new DetectRedundantActionCheck(rowInspector));
        checkList.add(new DetectRedundantConditionsCheck(rowInspector));
        return checkList;
    }

    private void addPairRowChecks(final RowInspector rowInspector) {
        for (RowInspector other : rowInspector.getCache().all()) {
            if (!rowInspector.equals(other)) {
                final List<Check> checks = makePairRowChecks(rowInspector,
                                                             other);
                assertChecks(rowInspector,
                             checks);
            }
        }
    }

    protected List<Check> makePairRowChecks(final RowInspector rowInspector,
                                            final RowInspector other) {
        final ArrayList<Check> checkList = new ArrayList<Check>();
        if (other.getRowIndex() != rowInspector.getRowIndex()) {
            checkList.add(new DetectConflictingRowsCheck(rowInspector,
                                                         other));
            checkList.add(new DetectRedundantRowsCheck(rowInspector,
                                                       other));
        }
        return checkList;
    }

    private void assertChecks(final RowInspector rowInspector,
                              final List<Check> checks) {
        final Set<Check> existingSetChecks = set.get(rowInspector);
        if (existingSetChecks == null) {
            set.put(rowInspector,
                    new HashSet<Check>(checks));
        } else {
            existingSetChecks.addAll(checks);
        }

        final Set<Check> existingRechecks = rechecks.get(rowInspector);
        if (existingRechecks == null) {
            rechecks.put(rowInspector,
                         new HashSet<Check>(checks));
        } else {
            existingRechecks.addAll(checks);
        }
    }

    public Collection<Check> remove(final RowInspector removedRowInspector) {
        //Ensure active analysis is cancelled
        cancelExistingAnalysis();

        //Remove all Checks referencing the removed RowInspector
        final Set<Check> removedChecks = new HashSet<Check>();
        Map<RowInspector, Set<Check>> remove = this.reciprocalRowInspectors.remove(removedRowInspector);
        for (Map.Entry<RowInspector, Set<Check>> reciprocalRowInspectors : remove.entrySet()) {
            final RowInspector reciprocalRowInspector = reciprocalRowInspectors.getKey();
            final Set<Check> reciprocalChecks = reciprocalRowInspectors.getValue();
            removedChecks.addAll(reciprocalChecks);
            if (set.containsKey(reciprocalRowInspector)) {
                set.get(reciprocalRowInspector).removeAll(reciprocalChecks);
            }
        }

        //Remove the RowInspector itself
        removedChecks.addAll(set.get(removedRowInspector));
        set.remove(removedRowInspector);

        return removedChecks;
    }

    public void cancelExistingAnalysis() {
        if (activeAnalysis != null) {
            activeAnalysis.cancel();
            activeAnalysis = null;
        }
    }
}

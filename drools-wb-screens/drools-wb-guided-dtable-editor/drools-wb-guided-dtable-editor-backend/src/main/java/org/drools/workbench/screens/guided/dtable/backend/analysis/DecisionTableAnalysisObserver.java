package org.drools.workbench.screens.guided.dtable.backend.analysis;

/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
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

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.drools.workbench.screens.guided.dtable.analysis.AnalysisMessage;
import org.guvnor.messageconsole.events.PublishBaseEvent.Place;
import org.guvnor.messageconsole.events.PublishBatchMessagesEvent;
import org.guvnor.messageconsole.events.SystemMessage;

@ApplicationScoped
public class DecisionTableAnalysisObserver {

    private static final String DT_ANALYSIS_MESSAGE = "DTAnalysis";

    @Inject
    private Event<PublishBatchMessagesEvent> publishBatchMessagesEvent;

    public void addAnalysisMessages(final @Observes AnalysisMessage analysisMessage) {
        PublishBatchMessagesEvent batchMessages = new PublishBatchMessagesEvent();
        batchMessages.setUserId(analysisMessage.getUserId());
        batchMessages.setSessionId(analysisMessage.getSessionId());
        batchMessages.setCleanExisting(true);
        batchMessages.setMessageType(DT_ANALYSIS_MESSAGE);
        batchMessages.setShowSystemConsole(true);
        batchMessages.setPlace(Place.TOP);

        if (!analysisMessage.isAnalysisFinished()) {
            SystemMessage publishableMessage = convertMessage(analysisMessage);
            batchMessages.getMessagesToPublish().add(publishableMessage);
        }

        publishBatchMessagesEvent.fire(batchMessages);
    }

    private SystemMessage convertMessage(AnalysisMessage originalMessage) {
        SystemMessage newMessage = new SystemMessage();
        newMessage.setLevel(originalMessage.getLevel());
        newMessage.setMessageType(DT_ANALYSIS_MESSAGE);
        newMessage.setId(originalMessage.getId());
        newMessage.setText(originalMessage.getText());
        newMessage.setPath(originalMessage.getPath());
        newMessage.setUserId(originalMessage.getUserId());
        return newMessage;
    }
}

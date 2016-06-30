package org.drools.workbench.screens.guided.dtable.analysis;

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

import java.io.Serializable;

import org.guvnor.common.services.shared.message.Level;
import org.jboss.errai.common.client.api.annotations.Portable;
import org.uberfire.backend.vfs.Path;

@Portable
public class AnalysisMessage implements Serializable {

    private long id;
    private Level level;
    private String text;
    private Path path;
    private String userId;
    private String sessionId;
    private boolean analysisFinished;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public boolean isAnalysisFinished() {
        return analysisFinished;
    }

    public void setAnalysisFinished(boolean analysisFinished) {
        this.analysisFinished = analysisFinished;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [id:" + id
                                               + ", level:" + level
                                               + ", text:" + text
                                               + ", path:" + path
                                               + ", userId:" + userId
                                               + ", sessionId:" + sessionId
                                               + ", analysisFinished:" + analysisFinished
                                               + "]";
    }
}

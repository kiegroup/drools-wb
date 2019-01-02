package org.drools.workbench.screens.scenariosimulation.backend.server.runner.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ResultWrapperTest {

    @Test
    public void orElse() {
        assertEquals((Integer) 1, ResultWrapper.createResult(1).orElse(3));
        assertEquals(3, ResultWrapper.createErrorResult().orElse(3));
        assertNull(ResultWrapper.createResult(null).orElse(3));
    }

    @Test
    public void orElseGet() {
        assertEquals((Integer) 1, ResultWrapper.createResult(1).orElseGet(() -> 3));
        assertEquals(3, ResultWrapper.createErrorResult().orElseGet(() -> 3));
        assertNull(ResultWrapper.createResult(null).orElseGet(() -> 3));
    }
}
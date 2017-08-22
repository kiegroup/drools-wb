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

package org.drools.workbench.screens.guided.dtable.client.widget.table;

import com.ait.lienzo.client.core.types.Transform;
import com.ait.lienzo.test.LienzoMockitoTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.uberfire.ext.wires.core.grids.client.model.Bounds;
import org.uberfire.ext.wires.core.grids.client.model.impl.BaseBounds;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(LienzoMockitoTestRunner.class)
public class BoundaryTransformMediatorTest {

    @Mock
    private GuidedDecisionTableModellerView view;

    private Bounds visibleBounds = new BaseBounds(0,
                                                  0,
                                                  1000,
                                                  1000);

    private BoundaryTransformMediator restriction;

    private void setBounds(final double x,
                           final double y,
                           final double width,
                           final double height) {
        when(view.getBounds()).thenReturn(new BaseBounds(x,
                                                         y,
                                                         width,
                                                         height));
        restriction = new BoundaryTransformMediator(view);
    }

    private void testTransformation(final double txActual,
                                    final double tyActual,
                                    final double txExpected,
                                    final double tyExpected) {
        final Transform test = new Transform().translate(txActual,
                                                         tyActual);
        final Transform result = restriction.adjust(test,
                                                    visibleBounds);

        assertNotNull(result);
        assertEquals(txExpected,
                     result.getTranslateX(),
                     0.0);
        assertEquals(tyExpected,
                     result.getTranslateY(),
                     0.0);
    }

    @Test
    public void testLeftEdgeWhenGridIsSmallerThanVisibleBounds() {
        setBounds(0,
                  0,
                  500,
                  500);
        testTransformation(1200,
                           0,
                           0,
                           0);
    }

    @Test
    public void testRightEdgeWhenGridIsSmallerThanVisibleBounds() {
        setBounds(0,
                  0,
                  500,
                  500);
        testTransformation(-200,
                           0,
                           0,
                           0);
    }

    @Test
    public void testTopEdgeWhenGridIsSmallerThanVisibleBounds() {
        setBounds(0,
                  0,
                  500,
                  500);
        testTransformation(0,
                           1200,
                           0,
                           0);
    }

    @Test
    public void testBottomEdgeWhenGridIsSmallerThanVisibleBounds() {
        setBounds(0,
                  0,
                  500,
                  500);
        testTransformation(0,
                           -200,
                           0,
                           0);
    }

    @Test
    public void testLeftEdgeWhenGridIsLargerThanVisibleBounds() {
        setBounds(0,
                  0,
                  5000,
                  5000);
        testTransformation(1200,
                           0,
                           0,
                           0);
    }

    @Test
    public void testRightEdgeWhenGridIsLargerThanVisibleBounds() {
        setBounds(0,
                  0,
                  5000,
                  5000);
        testTransformation(-200,
                           0,
                           -200,
                           0);
    }

    @Test
    public void testTopEdgeWhenGridIsLargerThanVisibleBounds() {
        setBounds(0,
                  0,
                  5000,
                  5000);
        testTransformation(0,
                           1200,
                           0,
                           0);
    }

    @Test
    public void testBottomEdgeWhenGridIsLargerThanVisibleBounds() {
        setBounds(0,
                  0,
                  5000,
                  5000);
        testTransformation(0,
                           -200,
                           0,
                           -200);
    }
}

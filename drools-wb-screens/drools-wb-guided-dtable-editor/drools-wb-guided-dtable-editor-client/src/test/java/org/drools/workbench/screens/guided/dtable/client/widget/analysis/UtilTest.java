package org.drools.workbench.screens.guided.dtable.client.widget.analysis;

import org.drools.workbench.models.guided.dtable.shared.model.DTCellValue52;
import org.junit.Test;

import static org.junit.Assert.*;

public class UtilTest {

    @Test
    public void checkNullNumericValues() {
        assertNull(Util.getValueAsString(new DTCellValue52((Long) null)));
    }

    @Test
    public void checkNotNullNumericValues() {
        assertEquals("100",
                     Util.getValueAsString(new DTCellValue52(100)));
    }

    @Test
    public void checkNullBooleanValues() {
        assertNull(Util.getValueAsString(new DTCellValue52((Boolean) null)));
    }

    @Test
    public void checkNotNullBooleanValues() {
        assertEquals("true",
                     Util.getValueAsString(new DTCellValue52(true)));
    }

    @Test
    public void checkNullStringValues() {
        assertNull(Util.getValueAsString(new DTCellValue52((String) null)));
    }

    @Test
    public void checkNotNullStringValues() {
        assertEquals("test",
                     Util.getValueAsString(new DTCellValue52("test")));
    }
}

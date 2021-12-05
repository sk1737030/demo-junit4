package step3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AssertHamcrestTest {

    @Test
    void floatPoint() {
        // false
        assertNotSame(2.32 * 3, 6.96);

        assertTrue(Math.abs(2.32 * 3 - 6.96) < 0.0005);

        assertEquals(2.32 * 3, 6.96, 0.001);
    }




}

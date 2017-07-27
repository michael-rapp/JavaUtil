/*
 * Copyright 2017 Michael Rapp
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package de.mrapp.util;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static de.mrapp.util.Condition.ensureNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests the functionality of the class {@link Condition}.
 *
 * @author Michael Rapp
 */
public class ConditionTest extends AbstractFileTest {

    @Test
    public final void testEnsureTrueThrowsException() {
        String message = "message";

        try {
            Condition.ensureTrue(false, message);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureTrueThrowsNoException() {
        Condition.ensureTrue(true, "message");
    }

    @Test
    public final void testEnsureTrueWithClassParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureTrue(false, message, IllegalStateException.class);
            fail();
        } catch (IllegalStateException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureTrueWithClassParameterThrowsNoException() {
        Condition.ensureTrue(true, "message", IllegalStateException.class);
    }

    @Test
    public final void testEnsureFalseThrowsException() {
        String message = "message";

        try {
            Condition.ensureFalse(true, message);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureFalseThrowsNoException() {
        Condition.ensureFalse(false, "message");
    }

    @Test
    public final void testEnsureFalseWithClassParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureFalse(true, message, IllegalStateException.class);
            fail();
        } catch (IllegalStateException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureFalseWithClassParameterThrowsNoException() {
        Condition.ensureFalse(false, "message", IllegalStateException.class);
    }

    @Test
    public final void testEnsureEqualThrowsException1() {
        String message = "message";

        try {
            Condition.ensureEqual("foo", "bar", message);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureEqualThrowsException2() {
        String message = "message";

        try {
            Condition.ensureEqual(null, "bar", message);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureEqualThrowsException3() {
        String message = "message";

        try {
            Condition.ensureEqual("foo", null, message);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureEqualThrowsNoException() {
        Condition.ensureEqual("foo", "foo", "message");
        Condition.ensureEqual(null, null, "message");
    }

    @Test
    public final void testEnsureNotEqualThrowsException1() {
        String message = "message";

        try {
            Condition.ensureNotEqual("foo", "foo", message);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureNotEqualThrowsException2() {
        String message = "message";

        try {
            Condition.ensureNotEqual(null, null, message);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureNotEqualThrowsNoException() {
        Condition.ensureNotEqual(false, "foo", "bar");
        Condition.ensureNotEqual(false, "foo", null);
        Condition.ensureNotEqual(false, null, "bar");
    }

    @Test
    public final void testEnsureNotNullThrowsException() {
        String message = "message";

        try {
            ensureNotNull(null, message);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureNotNullThrowsNoException() {
        ensureNotNull(new Object(), "message");
    }

    @Test
    public final void testEnsureNotNullWithClassParameterThrowsException() {
        String message = "message";

        try {
            ensureNotNull(null, message, IllegalStateException.class);
            fail();
        } catch (IllegalStateException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureNotNullWithClassParameterThrowsNoException() {
        ensureNotNull(new Object(), "message", IllegalArgumentException.class);
    }

    @Test
    public final void testEnsureNotEmptyThrowsException() {
        String message = "message";

        try {
            Condition.ensureNotEmpty("", message);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureNotEmptyThrowsNoException() {
        Condition.ensureNotEmpty("text", "message");
    }

    @Test
    public final void testEnsureNotEmptyWithClassParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureNotEmpty("", message, NullPointerException.class);
            fail();
        } catch (NullPointerException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureNotEmptyWithClassParameterThrowsNoException() {
        Condition.ensureNotEmpty("text", "message", NullPointerException.class);
    }

    @Test
    public final void testEnsureAtLeastWithShortParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtLeast((short) 0, (short) 1, message);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureAtLeastWithShortParameterThrowsNoException() {
        Condition.ensureAtLeast((short) 1, (short) 1, "message");
    }

    @Test
    public final void testEnsureAtLeastWithShortAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtLeast((short) 0, (short) 1, message, IndexOutOfBoundsException.class);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureAtLeastWithShortAndClassParametersThrowsNoException() {
        Condition.ensureAtLeast((short) 1, (short) 1, "message", IndexOutOfBoundsException.class);
    }

    @Test
    public final void testEnsureAtLeastWithIntegerParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtLeast(0, 1, message);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureAtLeastWithIntegerParameterThrowsNoException() {
        Condition.ensureAtLeast(1, 1, "message");
    }

    @Test
    public final void testEnsureAtLeastWithIntegerAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtLeast(0, 1, message, IndexOutOfBoundsException.class);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureAtLeastWithIntegerAndClassParametersThrowsNoException() {
        Condition.ensureAtLeast(1, 1, "message", IndexOutOfBoundsException.class);
    }

    @Test
    public final void testEnsureAtLeastWithLongParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtLeast(0L, 1L, message);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureAtLeastWithLongParameterThrowsNoException() {
        Condition.ensureAtLeast(1L, 1L, "message");
    }

    @Test
    public final void testEnsureAtLeastWithLongAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtLeast(0L, 1L, message, IndexOutOfBoundsException.class);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureAtLeastWithLongAndClassParametersThrowsNoException() {
        Condition.ensureAtLeast(1L, 1L, "message", IndexOutOfBoundsException.class);
    }

    @Test
    public final void testEnsureAtLeastWithFloatParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtLeast(0f, 1f, message);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureAtLeastWithFloatParameterThrowsNoException() {
        Condition.ensureAtLeast(1f, 1f, "message");
    }

    @Test
    public final void testEnsureAtLeastWithFloatAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtLeast(0f, 1f, message, IndexOutOfBoundsException.class);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureAtLeastWithFloatAndClassParametersThrowsNoException() {
        Condition.ensureAtLeast(1f, 1f, "message", IndexOutOfBoundsException.class);
    }

    @Test
    public final void testEnsureAtLeastWithDoubleParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtLeast(0d, 1d, message);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureAtLeastWithDoubleParameterThrowsNoException() {
        Condition.ensureAtLeast(1d, 1d, "message");
    }

    @Test
    public final void testEnsureAtLeastWithDoubleAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtLeast(0d, 1d, message, IndexOutOfBoundsException.class);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureAtLeastWithDoubleAndClassParametersThrowsNoException() {
        Condition.ensureAtLeast(1d, 1d, "message", IndexOutOfBoundsException.class);
    }

    @Test
    public final void testEnsureAtMaximumWithShortParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtMaximum((short) 2, (short) 1, message);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureAtMaximumWithShortParameterThrowsNoException() {
        Condition.ensureAtMaximum((short) 1, (short) 1, "message");
    }

    @Test
    public final void testEnsureAtMaximumWithShortAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtMaximum((short) 2, (short) 1, message,
                    IndexOutOfBoundsException.class);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureAtMaximumWithShortAndClassParametersThrowsNoException() {
        Condition.ensureAtMaximum((short) 1, (short) 1, "message", IndexOutOfBoundsException.class);
    }

    @Test
    public final void testEnsureAtMaximumWithIntegerParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtMaximum(2, 1, message);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureAtMaximumWithIntegerParameterThrowsNoException() {
        Condition.ensureAtMaximum(1, 1, "message");
    }

    @Test
    public final void testEnsureAtMaximumWithIntegerAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtMaximum(2, 1, message, IndexOutOfBoundsException.class);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureAtMaximumWithIntegerAndClassParametersThrowsNoException() {
        Condition.ensureAtMaximum(1, 1, "message", IndexOutOfBoundsException.class);
    }

    @Test
    public final void testEnsureAtMaximumWithLongParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtMaximum(2L, 1L, message);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureAtMaximumWithLongParameterThrowsNoException() {
        Condition.ensureAtMaximum(1L, 1L, "message");
    }

    @Test
    public final void testEnsureAtMaximumWithLongAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtMaximum(2L, 1L, message, IndexOutOfBoundsException.class);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureAtMaximumWithLongAndClassParametersThrowsNoException() {
        Condition.ensureAtMaximum(1L, 1L, "message", IndexOutOfBoundsException.class);
    }

    @Test
    public final void testEnsureAtMaximumWithFloatParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtMaximum(2f, 1f, message);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureAtMaximumWithFloatParameterThrowsNoException() {
        Condition.ensureAtMaximum(1f, 1f, "message");
    }

    @Test
    public final void testEnsureAtMaximumWithFloatAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtMaximum(2f, 1f, message, IndexOutOfBoundsException.class);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureAtMaximumWithFloatAndClassParametersThrowsNoException() {
        Condition.ensureAtMaximum(1f, 1f, "message", IndexOutOfBoundsException.class);
    }

    @Test
    public final void testEnsureAtMaximumWithDoubleParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtMaximum(2d, 1d, message);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureAtMaximumWithDoubleParameterThrowsNoException() {
        Condition.ensureAtMaximum(1d, 1d, "message");
    }

    @Test
    public final void testEnsureAtMaximumWithDoubleAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtMaximum(2d, 1d, message, IndexOutOfBoundsException.class);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureAtMaximumWithDoubleAndClassParametersThrowsNoException() {
        Condition.ensureAtMaximum(1d, 1d, "message", IndexOutOfBoundsException.class);
    }

    @Test
    public final void testEnsureGreaterWithShortParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureGreater((short) 1, (short) 1, message);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureGreaterWithShortParameterThrowsNoException() {
        Condition.ensureGreater((short) 2, (short) 1, "message");
    }

    @Test
    public final void testEnsureGreaterWithShortAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureGreater((short) 1, (short) 1, message, IndexOutOfBoundsException.class);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureGreaterWithShortAndClassParametersThrowsNoException() {
        Condition.ensureGreater((short) 2, (short) 1, "message", IndexOutOfBoundsException.class);
    }

    @Test
    public final void testEnsureGreaterWithIntegerParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureGreater(1, 1, message);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureGreaterWithIntegerParameterThrowsNoException() {
        Condition.ensureGreater(2, 1, "message");
    }

    @Test
    public final void testEnsureGreaterWithIntegerAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureGreater(1, 1, message, IndexOutOfBoundsException.class);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureGreaterWithIntegerAndClassParametersThrowsNoException() {
        Condition.ensureGreater(2, 1, "message", IndexOutOfBoundsException.class);
    }

    @Test
    public final void testEnsureGreaterWithLongParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureGreater(1L, 1L, message);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureGreaterWithLongParameterThrowsNoException() {
        Condition.ensureGreater(2L, 1L, "message");
    }

    @Test
    public final void testEnsureGreaterWithLongAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureGreater(1L, 1L, message, IndexOutOfBoundsException.class);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureGreaterWithLongAndClassParametersThrowsNoException() {
        Condition.ensureGreater(2L, 1L, "message", IndexOutOfBoundsException.class);
    }

    @Test
    public final void testEnsureGreaterWithFloatParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureGreater(1f, 1f, message);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureGreaterWithFloatParameterThrowsNoException() {
        Condition.ensureGreater(2f, 1f, "message");
    }

    @Test
    public final void testEnsureGreaterWithFloatAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureGreater(1f, 1f, message, IndexOutOfBoundsException.class);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureGreaterWithFloatAndClassParametersThrowsNoException() {
        Condition.ensureGreater(2f, 1f, "message", IndexOutOfBoundsException.class);
    }

    @Test
    public final void testEnsureGreaterWithDoubleParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureGreater(1d, 1d, message);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureGreaterWithDoubleParameterThrowsNoException() {
        Condition.ensureGreater(2d, 1d, "message");
    }

    @Test
    public final void testEnsureGreaterWithDoubleAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureGreater(1d, 1d, message, IndexOutOfBoundsException.class);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureGreaterWithDoubleAndClassParametersThrowsNoException() {
        Condition.ensureGreater(2d, 1d, "message", IndexOutOfBoundsException.class);
    }

    @Test
    public final void testEnsureSmallerWithShortParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureSmaller((short) 1, (short) 1, message);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureSmallerWithShortParameterThrowsNoException() {
        Condition.ensureSmaller((short) 0, (short) 1, "message");
    }

    @Test
    public final void testEnsureSmallerWithShortAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureSmaller((short) 1, (short) 1, message, IndexOutOfBoundsException.class);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureSmallerWithShortAndClassParametersThrowsNoException() {
        Condition.ensureSmaller((short) 0, (short) 1, "message", IndexOutOfBoundsException.class);
    }

    @Test
    public final void testEnsureSmallerWithIntegerParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureSmaller(1, 1, message);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureSmallerWithIntegerParameterThrowsNoException() {
        Condition.ensureSmaller(0, 1, "message");
    }

    @Test
    public final void testEnsureSmallerWithIntegerAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureSmaller(1, 1, message, IndexOutOfBoundsException.class);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureSmallerWithIntegerAndClassParametersThrowsNoException() {
        Condition.ensureSmaller(0, 1, "message", IndexOutOfBoundsException.class);
    }

    @Test
    public final void testEnsureSmallerWithLongParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureSmaller(1L, 1L, message);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureSmallerWithLongParameterThrowsNoException() {
        Condition.ensureSmaller(0L, 1L, "message");
    }

    @Test
    public final void testEnsureSmallerWithLongAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureSmaller(1L, 1L, message, IndexOutOfBoundsException.class);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureSmallerWithLongAndClassParametersThrowsNoException() {
        Condition.ensureSmaller(0L, 1L, "message", IndexOutOfBoundsException.class);
    }

    @Test
    public final void testEnsureSmallerWithFloatParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureSmaller(1f, 1f, message);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureSmallerWithFloatParameterThrowsNoException() {
        Condition.ensureSmaller(0f, 1f, "message");
    }

    @Test
    public final void testEnsureSmallerWithFloatAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureSmaller(1f, 1f, message, IndexOutOfBoundsException.class);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureSmallerWithFloatAndClassParametersThrowsNoException() {
        Condition.ensureSmaller(0f, 1f, "message", IndexOutOfBoundsException.class);
    }

    @Test
    public final void testEnsureSmallerWithDoubleParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureSmaller(1d, 1d, message);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureSmallerWithDoubleParameterThrowsNoException() {
        Condition.ensureSmaller(0d, 1d, "message");
    }

    @Test
    public final void testEnsureSmallerWithDoubleAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureSmaller(1d, 1d, message, IndexOutOfBoundsException.class);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureSmallerWithDoubleAndClassParametersThrowsNoException() {
        Condition.ensureSmaller(0d, 1d, "message", IndexOutOfBoundsException.class);
    }

    @Test
    public final void testEnsureIterableNotEmptyThrowsException() {
        String message = "message";
        List<Object> list = new LinkedList<>();

        try {
            Condition.ensureNotEmpty(list, message);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureIterableNotEmptyThrowsNoException() {
        List<Object> list = new LinkedList<>();
        list.add(new Object());
        Condition.ensureNotEmpty(list, "message");
    }

    @Test
    public final void testEnsureIterableNotEmptyWithClassParameterThrowsException() {
        String message = "message";
        List<Object> list = new LinkedList<>();

        try {
            Condition.ensureNotEmpty(list, message, IllegalStateException.class);
            fail();
        } catch (IllegalStateException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureIterableNotEmptyWithClassParameterThrowsNoException() {
        List<Object> list = new LinkedList<>();
        list.add(new Object());
        Condition.ensureNotEmpty(list, "message", IllegalStateException.class);
    }

    @Test
    public final void testEnsureFileExistsThrowsException() {
        String message = "message";
        File file = new File(getTestFile().getParentFile(), "foo.txt");

        try {
            Condition.ensureFileExists(file, message);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureFileExistsThrowsNoException() throws IOException {
        String message = "message";
        File file = getTestFile();
        Condition.ensureFileExists(file, message);
    }

    @Test
    public final void testEnsureFileExistsWithClassParameterThrowsException() {
        String message = "message";
        File file = new File(getTestFile().getParentFile(), "foo.txt");

        try {
            Condition.ensureFileExists(file, message, IllegalStateException.class);
            fail();
        } catch (IllegalStateException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureFileExistsWithClassParameterThrowsNoException() throws IOException {
        String message = "message";
        File file = getTestFile();
        Condition.ensureFileExists(file, message, IllegalStateException.class);
    }

    @Test
    public final void testEnsureFileIsDirectoryThrowsException() throws IOException {
        String message = "message";
        File file = getTestFile();

        try {
            Condition.ensureFileIsDirectory(file, message);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureFileIsDirectoryThrowsNoException() throws IOException {
        String message = "message";
        File file = getTestFile().getParentFile();
        Condition.ensureFileIsDirectory(file, message);
    }

    @Test
    public final void testEnsureFileIsDirectoryWithClassParameterThrowsException()
            throws IOException {
        String message = "message";
        File file = getTestFile();

        try {
            Condition.ensureFileIsDirectory(file, message, IllegalStateException.class);
            fail();
        } catch (IllegalStateException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureFileIsDirectoryWithClassParameterThrowsNoException()
            throws IOException {
        String message = "message";
        File file = getTestFile().getParentFile();
        Condition.ensureFileIsDirectory(file, message, IllegalStateException.class);
    }

    @Test
    public final void testEnsureFileIsNoDirectoryThrowsException() throws IOException {
        String message = "message";
        File file = getTestFile().getParentFile();

        try {
            Condition.ensureFileIsNoDirectory(file, message);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureFileIsNoDirectoryThrowsNoException() throws IOException {
        String message = "message";
        File file = getTestFile();
        Condition.ensureFileIsNoDirectory(file, message);
    }

    @Test
    public final void testEnsureFileIsNoDirectoryWithClassParameterThrowsException()
            throws IOException {
        String message = "message";
        File file = getTestFile().getParentFile();

        try {
            Condition.ensureFileIsNoDirectory(file, message, IllegalStateException.class);
            fail();
        } catch (IllegalStateException e) {
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public final void testEnsureFileIsNoDirectoryWithClassParameterThrowsNoException()
            throws IOException {
        String message = "message";
        File file = getTestFile();
        Condition.ensureFileIsNoDirectory(file, message, IllegalStateException.class);
    }

}
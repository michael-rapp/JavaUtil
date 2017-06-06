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

import junit.framework.Assert;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import static de.mrapp.util.Condition.ensureNotEmpty;
import static de.mrapp.util.Condition.ensureNotNull;
import static org.junit.Assert.assertEquals;

/**
 * Tests the functionality of the class {@link Condition}.
 *
 * @author Michael Rapp
 */
public class ConditionTest {

    /**
     * Returns the file, which corresponds to a specific file name.
     *
     * @param fileName The file name, which corresponds to the file, which should be returned, as a
     *                 {@link String}. The string may neither be null, nor empty
     * @return The file, which corresponds to the given file name, as an instance of the class
     * {@link File}. The file may not be null
     */
    @NotNull
    private File getFile(@NotNull final String fileName) {
        ensureNotNull(fileName, "The file name may not be null");
        ensureNotEmpty(fileName, "The file name may not be empty");

        try {
            URL url = getClass().getClassLoader().getResource(fileName);

            if (url != null) {
                return Paths.get(url.toURI()).toFile();
            }

            throw new RuntimeException("Failed to retrieve path of file \"" + fileName + "\"");
        } catch (URISyntaxException e) {
            throw new RuntimeException("Failed to read file \"" + fileName + "\"", e);
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a boolean expression is
     * true, if the boolean expression is not true.
     */
    @Test
    public final void testEnsureTrueThrowsException() {
        String message = "message";

        try {
            Condition.ensureTrue(false, message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a boolean expression is
     * true, if the boolean expression is true.
     */
    @Test
    public final void testEnsureTrueThrowsNoException() {
        Condition.ensureTrue(true, "message");
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a boolean expression is
     * true and expects a class as a parameter, if the boolean expression is not true.
     */
    @Test
    public final void testEnsureTrueWithClassParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureTrue(false, message, IllegalStateException.class);
            Assert.fail();
        } catch (IllegalStateException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a boolean expression is
     * true and expects a class as a parameter, if the boolean expression is true.
     */
    @Test
    public final void testEnsureTrueWithClassParameterThrowsNoException() {
        Condition.ensureTrue(true, "message", IllegalStateException.class);
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a boolean expression is
     * false, if the boolean expression is not false.
     */
    @Test
    public final void testEnsureFalseThrowsException() {
        String message = "message";

        try {
            Condition.ensureFalse(true, message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a boolean expression is
     * false, if the boolean expression is false.
     */
    @Test
    public final void testEnsureFalseThrowsNoException() {
        Condition.ensureFalse(false, "message");
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a boolean expression is
     * false and expects a class as a parameter, if the boolean expression is not false.
     */
    @Test
    public final void testEnsureFalseWithClassParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureFalse(true, message, IllegalStateException.class);
            Assert.fail();
        } catch (IllegalStateException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a boolean expression is
     * false and expects a class as a parameter, if the boolean expression is false.
     */
    @Test
    public final void testEnsureFalseWithClassParameterThrowsNoException() {
        Condition.ensureFalse(false, "message", IllegalStateException.class);
    }

    /**
     * Tests the functionality of the method, which allows to ensure that two objects are equal, if
     * the objects are not equal.
     */
    @Test
    public final void testEnsureEqualThrowsException1() {
        String message = "message";

        try {
            Condition.ensureEqual("foo", "bar", message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that two objects are equal, if
     * the objects are not equal.
     */
    @Test
    public final void testEnsureEqualThrowsException2() {
        String message = "message";

        try {
            Condition.ensureEqual(null, "bar", message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that two objects are equal, if
     * the objects are not equal.
     */
    @Test
    public final void testEnsureEqualThrowsException3() {
        String message = "message";

        try {
            Condition.ensureEqual("foo", null, message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that two objects are equal, if
     * the objects are equal.
     */
    @Test
    public final void testEnsureEqualThrowsNoException() {
        Condition.ensureEqual("foo", "foo", "message");
        Condition.ensureEqual(null, null, "message");
    }

    /**
     * Tests the functionality of the method, which allows to ensure that two objects are not equal,
     * if the objects are equal.
     */
    @Test
    public final void testEnsureNotEqualThrowsException1() {
        String message = "message";

        try {
            Condition.ensureNotEqual("foo", "foo", message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that two objects are not equal,
     * if the objects are equal.
     */
    @Test
    public final void testEnsureNotEqualThrowsException2() {
        String message = "message";

        try {
            Condition.ensureNotEqual(null, null, message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that two objects are not equal,
     * if the objects are not equal.
     */
    @Test
    public final void testEnsureNotEqualThrowsNoException() {
        Condition.ensureNotEqual(false, "foo", "bar");
        Condition.ensureNotEqual(false, "foo", null);
        Condition.ensureNotEqual(false, null, "bar");
    }

    /**
     * Tests the functionality of the method, which allows to ensure that an object is not null, if
     * the object is null.
     */
    @Test
    public final void testEnsureNotNullThrowsException() {
        String message = "message";

        try {
            ensureNotNull(null, message);
            Assert.fail();
        } catch (NullPointerException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that an object is not null, if
     * the object is not null.
     */
    @Test
    public final void testEnsureNotNullThrowsNoException() {
        ensureNotNull(new Object(), "message");
    }

    /**
     * Tests the functionality of the method, which allows to ensure that an object is not null and
     * expects a class as a parameter, if the object is null.
     */
    @Test
    public final void testEnsureNotNullWithClassParameterThrowsException() {
        String message = "message";

        try {
            ensureNotNull(null, message, IllegalArgumentException.class);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that an object is not null and
     * expects a class as a parameter, if the object is not null.
     */
    @Test
    public final void testEnsureNotNullWithClassParameterThrowsNoException() {
        ensureNotNull(new Object(), "message", IllegalArgumentException.class);
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a text is not empty, if
     * the text is empty.
     */
    @Test
    public final void testEnsureNotEmptyThrowsException() {
        String message = "message";

        try {
            Condition.ensureNotEmpty("", message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a text is not empty, if
     * the text is not empty.
     */
    @Test
    public final void testEnsureNotEmptyThrowsNoException() {
        Condition.ensureNotEmpty("text", "message");
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a text is not empty and
     * expects a class as a parameter, if the text is empty.
     */
    @Test
    public final void testEnsureNotEmptyWithClassParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureNotEmpty("", message, NullPointerException.class);
            Assert.fail();
        } catch (NullPointerException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a text is not empty and
     * expects a class as a parameter, if the text is not empty.
     */
    @Test
    public final void testEnsureNotEmptyWithClassParameterThrowsNoException() {
        Condition.ensureNotEmpty("text", "message", NullPointerException.class);
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Short} value is
     * at least as great as a reference value, if the value is smaller as the reference value.
     */
    @Test
    public final void testEnsureAtLeastWithShortParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtLeast((short) 0, (short) 1, message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Short} value is
     * at least as great as a reference value, if the value is equal or greater as the reference
     * value.
     */
    @Test
    public final void testEnsureAtLeastWithShortParameterThrowsNoException() {
        Condition.ensureAtLeast((short) 1, (short) 1, "message");
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Short} value is
     * at least as great as a reference value and expects a class as a parameter, if the value is
     * smaller as the reference value.
     */
    @Test
    public final void testEnsureAtLeastWithShortAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtLeast((short) 0, (short) 1, message, IndexOutOfBoundsException.class);
            Assert.fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Short} value is
     * at least as great as a reference value and expects a class as a parameter, if the value is
     * equal or greater as the reference value.
     */
    @Test
    public final void testEnsureAtLeastWithShortAndClassParametersThrowsNoException() {
        Condition.ensureAtLeast((short) 1, (short) 1, "message", IndexOutOfBoundsException.class);
    }

    /**
     * Tests the functionality of the method, which allows to ensure that an {@link Integer} value
     * is at least as great as a reference value, if the value is smaller as the reference value.
     */
    @Test
    public final void testEnsureAtLeastWithIntegerParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtLeast(0, 1, message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that an {@link Integer} value
     * is at least as great as a reference value, if the value is equal or greater as the reference
     * value.
     */
    @Test
    public final void testEnsureAtLeastWithIntegerParameterThrowsNoException() {
        Condition.ensureAtLeast(1, 1, "message");
    }

    /**
     * Tests the functionality of the method, which allows to ensure that an {@link Integer} value
     * is at least as great as a reference value and expects a class as a parameter, if the value is
     * smaller as the reference value.
     */
    @Test
    public final void testEnsureAtLeastWithIntegerAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtLeast(0, 1, message, IndexOutOfBoundsException.class);
            Assert.fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that an {@link Integer} value
     * is at least as great as a reference value and expects a class as a parameter, if the value is
     * equal or greater as the reference value.
     */
    @Test
    public final void testEnsureAtLeastWithIntegerAndClassParametersThrowsNoException() {
        Condition.ensureAtLeast(1, 1, "message", IndexOutOfBoundsException.class);
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Long} value is as
     * least as great as a reference value, if the value is smaller as the reference value.
     */
    @Test
    public final void testEnsureAtLeastWithLongParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtLeast(0L, 1L, message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Long} value is as
     * least as great as a reference value, if the value is equal or greater as the reference
     * value.
     */
    @Test
    public final void testEnsureAtLeastWithLongParameterThrowsNoException() {
        Condition.ensureAtLeast(1L, 1L, "message");
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Long} value is as
     * least as great as a reference value and expects a class as a parameter, if the value is
     * smaller as the reference value.
     */
    @Test
    public final void testEnsureAtLeastWithLongAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtLeast(0L, 1L, message, IndexOutOfBoundsException.class);
            Assert.fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Long} value is as
     * least as great as a reference value and expects a class as a parameter, if the value is equal
     * or greater as the reference value.
     */
    @Test
    public final void testEnsureAtLeastWithLongAndClassParametersThrowsNoException() {
        Condition.ensureAtLeast(1L, 1L, "message", IndexOutOfBoundsException.class);
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Float} value is
     * at least as great as a reference value, if the value is smaller as the reference value.
     */
    @Test
    public final void testEnsureAtLeastWithFloatParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtLeast(0f, 1f, message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Float} value is
     * at least as great as a reference value, if the value is equal or greater as the reference
     * value.
     */
    @Test
    public final void testEnsureAtLeastWithFloatParameterThrowsNoException() {
        Condition.ensureAtLeast(1f, 1f, "message");
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Float} value is
     * at least as great as a reference value and expects a class as a parameter, if the value is
     * smaller as the reference value.
     */
    @Test
    public final void testEnsureAtLeastWithFloatAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtLeast(0f, 1f, message, IndexOutOfBoundsException.class);
            Assert.fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Float} value is
     * at least as great as a reference value and expects a class as a parameter, if the value is
     * equal or greater as the reference value.
     */
    @Test
    public final void testEnsureAtLeastWithFloatAndClassParametersThrowsNoException() {
        Condition.ensureAtLeast(1f, 1f, "message", IndexOutOfBoundsException.class);
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Double} value is
     * at least as great as a reference value, if the value is smaller as the reference value.
     */
    @Test
    public final void testEnsureAtLeastWithDoubleParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtLeast(0d, 1d, message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Double} value is
     * at least as great as a reference value, if the value is equal or greater as the reference
     * value.
     */
    @Test
    public final void testEnsureAtLeastWithDoubleParameterThrowsNoException() {
        Condition.ensureAtLeast(1d, 1d, "message");
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Double} value is
     * at least as great as a reference value and expects a class as a parameter, if the value is
     * smaller as the reference value.
     */
    @Test
    public final void testEnsureAtLeastWithDoubleAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtLeast(0d, 1d, message, IndexOutOfBoundsException.class);
            Assert.fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Double} value is
     * at least as great as a reference value and expects a class as a parameter, if the value is
     * equal or greater as the reference value.
     */
    @Test
    public final void testEnsureAtLeastWithDoubleAndClassParametersThrowsNoException() {
        Condition.ensureAtLeast(1d, 1d, "message", IndexOutOfBoundsException.class);
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Short} value is
     * at maximum as great as a reference value, if the value is greater as the reference value.
     */
    @Test
    public final void testEnsureAtMaximumWithShortParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtMaximum((short) 2, (short) 1, message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Short} value is
     * at maximum as great as a reference value, if the value is equal or smaller as the reference
     * value.
     */
    @Test
    public final void testEnsureAtMaximumWithShortParameterThrowsNoException() {
        Condition.ensureAtMaximum((short) 1, (short) 1, "message");
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Short} value is
     * at maximum as great as a reference value and expects a class as a parameter, if the value is
     * greater as the reference value.
     */
    @Test
    public final void testEnsureAtMaximumWithShortAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtMaximum((short) 2, (short) 1, message,
                    IndexOutOfBoundsException.class);
            Assert.fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Short} value is
     * at maximum as great as a reference value and expects a class as a parameter, if the value is
     * equal or smaller as the reference value.
     */
    @Test
    public final void testEnsureAtMaximumWithShortAndClassParametersThrowsNoException() {
        Condition.ensureAtMaximum((short) 1, (short) 1, "message", IndexOutOfBoundsException.class);
    }

    /**
     * Tests the functionality of the method, which allows to ensure that an {@link Integer} value
     * is at maximum as great as a reference value, if the value is greater as the reference value.
     */
    @Test
    public final void testEnsureAtMaximumWithIntegerParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtMaximum(2, 1, message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that an {@link Integer} value
     * is at maximum as great as a reference value, if the value is equal or smaller as the
     * reference value.
     */
    @Test
    public final void testEnsureAtMaximumWithIntegerParameterThrowsNoException() {
        Condition.ensureAtMaximum(1, 1, "message");
    }

    /**
     * Tests the functionality of the method, which allows to ensure that an {@link Integer} value
     * is at maximum as great as a reference value and expects a class as a parameter, if the value
     * is greater as the reference value.
     */
    @Test
    public final void testEnsureAtMaximumWithIntegerAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtMaximum(2, 1, message, IndexOutOfBoundsException.class);
            Assert.fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that an {@link Integer} value
     * is at maximum as great as a reference value and expects a class as a parameter, if the value
     * is equal or smaller as the reference value.
     */
    @Test
    public final void testEnsureAtMaximumWithIntegerAndClassParametersThrowsNoException() {
        Condition.ensureAtMaximum(1, 1, "message", IndexOutOfBoundsException.class);
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Long} value is at
     * maximum as great as a reference value, if the value is greater as the reference value.
     */
    @Test
    public final void testEnsureAtMaximumWithLongParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtMaximum(2L, 1L, message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Long} value is at
     * maximum as great as a reference value, if the value is equal or smaller as the reference
     * value.
     */
    @Test
    public final void testEnsureAtMaximumWithLongParameterThrowsNoException() {
        Condition.ensureAtMaximum(1L, 1L, "message");
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Long} value is at
     * maximum as great as a reference value and expects a class as a parameter, if the value is
     * greater as the reference value.
     */
    @Test
    public final void testEnsureAtMaximumWithLongAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtMaximum(2L, 1L, message, IndexOutOfBoundsException.class);
            Assert.fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Long} value is at
     * maximum as great as a reference value and expects a class as a parameter, if the value is
     * equal or smaller as the reference value.
     */
    @Test
    public final void testEnsureAtMaximumWithLongAndClassParametersThrowsNoException() {
        Condition.ensureAtMaximum(1L, 1L, "message", IndexOutOfBoundsException.class);
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Float} value is
     * at maximum as great as a reference value, if the value is greater as the reference value.
     */
    @Test
    public final void testEnsureAtMaximumWithFloatParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtMaximum(2f, 1f, message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Float} value is
     * at maximum as great as a reference value, if the value is equal or smaller as the reference
     * value.
     */
    @Test
    public final void testEnsureAtMaximumWithFloatParameterThrowsNoException() {
        Condition.ensureAtMaximum(1f, 1f, "message");
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Float} value is
     * at maximum as great as a reference value and expects a class as a parameter, if the value is
     * greater as the reference value.
     */
    @Test
    public final void testEnsureAtMaximumWithFloatAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtMaximum(2f, 1f, message, IndexOutOfBoundsException.class);
            Assert.fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Float} value is
     * at maximum as great as a reference value and expects a class as a parameter, if the value is
     * equal or smaller as the reference value.
     */
    @Test
    public final void testEnsureAtMaximumWithFloatAndClassParametersThrowsNoException() {
        Condition.ensureAtMaximum(1f, 1f, "message", IndexOutOfBoundsException.class);
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Double} value is
     * at maximum as great as a reference value, if the value is greater as the reference value.
     */
    @Test
    public final void testEnsureAtMaximumWithDoubleParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtMaximum(2d, 1d, message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Double} value is
     * at maximum as great as a reference value, if the value is equal or smaller as the reference
     * value.
     */
    @Test
    public final void testEnsureAtMaximumWithDoubleParameterThrowsNoException() {
        Condition.ensureAtMaximum(1d, 1d, "message");
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Double} value is
     * at maximum as great as a reference value and expects a class as a parameter, if the value is
     * greater as the reference value.
     */
    @Test
    public final void testEnsureAtMaximumWithDoubleAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureAtMaximum(2d, 1d, message, IndexOutOfBoundsException.class);
            Assert.fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Double} value is
     * at maximum as great as a reference value and expects a class as a parameter, if the value is
     * equal or smaller as the reference value.
     */
    @Test
    public final void testEnsureAtMaximumWithDoubleAndClassParametersThrowsNoException() {
        Condition.ensureAtMaximum(1d, 1d, "message", IndexOutOfBoundsException.class);
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Short} value is
     * greater as a reference value, if the value is equal or smaller as the reference value.
     */
    @Test
    public final void testEnsureGreaterWithShortParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureGreater((short) 1, (short) 1, message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Short} value is
     * greater as a reference value, if the value is greater as the reference value.
     */
    @Test
    public final void testEnsureGreaterWithShortParameterThrowsNoException() {
        Condition.ensureGreater((short) 2, (short) 1, "message");
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Short} value is
     * greater as a reference value and expects a class as a parameter, if the value is equal or
     * greater as the reference value.
     */
    @Test
    public final void testEnsureGreaterWithShortAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureGreater((short) 1, (short) 1, message, IndexOutOfBoundsException.class);
            Assert.fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Short} value is
     * greater as a reference value and expects a class as a parameter, if the value is greater as
     * the reference value.
     */
    @Test
    public final void testEnsureGreaterWithShortAndClassParametersThrowsNoException() {
        Condition.ensureGreater((short) 2, (short) 1, "message", IndexOutOfBoundsException.class);
    }

    /**
     * Tests the functionality of the method, which allows to ensure that an {@link Integer} value
     * is greater as a reference value, if the value is equal or smaller as the reference value.
     */
    @Test
    public final void testEnsureGreaterWithIntegerParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureGreater(1, 1, message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that an {@link Integer} value
     * is greater as a reference value, if the value is greater as the reference value.
     */
    @Test
    public final void testEnsureGreaterWithIntegerParameterThrowsNoException() {
        Condition.ensureGreater(2, 1, "message");
    }

    /**
     * Tests the functionality of the method, which allows to ensure that an {@link Integer} value
     * is greater as a reference value and expects a class as a parameter, if the value is equal or
     * greater as the reference value.
     */
    @Test
    public final void testEnsureGreaterWithIntegerAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureGreater(1, 1, message, IndexOutOfBoundsException.class);
            Assert.fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that an {@link Integer} value
     * is greater as a reference value and expects a class as a parameter, if the value is greater
     * as the reference value.
     */
    @Test
    public final void testEnsureGreaterWithIntegerAndClassParametersThrowsNoException() {
        Condition.ensureGreater(2, 1, "message", IndexOutOfBoundsException.class);
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Long} value is
     * greater as a reference value, if the value is equal or smaller as the reference value.
     */
    @Test
    public final void testEnsureGreaterWithLongParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureGreater(1L, 1L, message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Long} value is
     * greater as a reference value, if the value is greater as the reference value.
     */
    @Test
    public final void testEnsureGreaterWithLongParameterThrowsNoException() {
        Condition.ensureGreater(2L, 1L, "message");
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Long} value is
     * greater as a reference value and expects a class as a parameter, if the value is equal or
     * greater as the reference value.
     */
    @Test
    public final void testEnsureGreaterWithLongAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureGreater(1L, 1L, message, IndexOutOfBoundsException.class);
            Assert.fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Long} value is
     * greater as a reference value and expects a class as a parameter, if the value is greater as
     * the reference value.
     */
    @Test
    public final void testEnsureGreaterWithLongAndClassParametersThrowsNoException() {
        Condition.ensureGreater(2L, 1L, "message", IndexOutOfBoundsException.class);
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Float} value is
     * greater as a reference value, if the value is equal or smaller as the reference value.
     */
    @Test
    public final void testEnsureGreaterWithFloatParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureGreater(1f, 1f, message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Float} value is
     * greater as a reference value, if the value is greater as the reference value.
     */
    @Test
    public final void testEnsureGreaterWithFloatParameterThrowsNoException() {
        Condition.ensureGreater(2f, 1f, "message");
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Float} value is
     * greater as a reference value and expects a class as a parameter, if the value is equal or
     * greater as the reference value.
     */
    @Test
    public final void testEnsureGreaterWithFloatAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureGreater(1f, 1f, message, IndexOutOfBoundsException.class);
            Assert.fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Float} value is
     * greater as a reference value and expects a class as a parameter, if the value is greater as
     * the reference value.
     */
    @Test
    public final void testEnsureGreaterWithFloatAndClassParametersThrowsNoException() {
        Condition.ensureGreater(2f, 1f, "message", IndexOutOfBoundsException.class);
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Double} value is
     * greater as a reference value, if the value is equal or smaller as the reference value.
     */
    @Test
    public final void testEnsureGreaterWithDoubleParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureGreater(1d, 1d, message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Double} value is
     * greater as a reference value, if the value is greater as the reference value.
     */
    @Test
    public final void testEnsureGreaterWithDoubleParameterThrowsNoException() {
        Condition.ensureGreater(2d, 1d, "message");
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Double} value is
     * greater as a reference value and expects a class as a parameter, if the value is equal or
     * greater as the reference value.
     */
    @Test
    public final void testEnsureGreaterWithDoubleAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureGreater(1d, 1d, message, IndexOutOfBoundsException.class);
            Assert.fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Double} value is
     * greater as a reference value and expects a class as a parameter, if the value is greater as
     * the reference value.
     */
    @Test
    public final void testEnsureGreaterWithDoubleAndClassParametersThrowsNoException() {
        Condition.ensureGreater(2d, 1d, "message", IndexOutOfBoundsException.class);
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Short} value is
     * smaller as a reference value, if the value is equal or greater as the reference value.
     */
    @Test
    public final void testEnsureSmallerWithShortParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureSmaller((short) 1, (short) 1, message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Short} value is
     * smaller as a reference value, if the value is smaller as the reference value.
     */
    @Test
    public final void testEnsureSmallerWithShortParameterThrowsNoException() {
        Condition.ensureSmaller((short) 0, (short) 1, "message");
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Short} value is
     * smaller as a reference value and expects a class as a parameter, if the value is equal or
     * smaller as the reference value.
     */
    @Test
    public final void testEnsureSmallerWithShortAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureSmaller((short) 1, (short) 1, message, IndexOutOfBoundsException.class);
            Assert.fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Short} value is
     * smaller as a reference value and expects a class as a parameter, if the value is smaller as
     * the reference value.
     */
    @Test
    public final void testEnsureSmallerWithShortAndClassParametersThrowsNoException() {
        Condition.ensureSmaller((short) 0, (short) 1, "message", IndexOutOfBoundsException.class);
    }

    /**
     * Tests the functionality of the method, which allows to ensure that an {@link Integer} value
     * is smaller as a reference value, if the value is equal or greater as the reference value.
     */
    @Test
    public final void testEnsureSmallerWithIntegerParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureSmaller(1, 1, message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that an {@link Integer} value
     * is smaller as a reference value, if the value is smaller as the reference value.
     */
    @Test
    public final void testEnsureSmallerWithIntegerParameterThrowsNoException() {
        Condition.ensureSmaller(0, 1, "message");
    }

    /**
     * Tests the functionality of the method, which allows to ensure that an {@link Integer} value
     * is smaller as a reference value and expects a class as a parameter, if the value is equal or
     * smaller as the reference value.
     */
    @Test
    public final void testEnsureSmallerWithIntegerAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureSmaller(1, 1, message, IndexOutOfBoundsException.class);
            Assert.fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that an {@link Integer} value
     * is smaller as a reference value and expects a class as a parameter, if the value is smaller
     * as the reference value.
     */
    @Test
    public final void testEnsureSmallerWithIntegerAndClassParametersThrowsNoException() {
        Condition.ensureSmaller(0, 1, "message", IndexOutOfBoundsException.class);
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Long} value is
     * smaller as a reference value, if the value is equal or greater as the reference value.
     */
    @Test
    public final void testEnsureSmallerWithLongParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureSmaller(1L, 1L, message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Long} value is
     * smaller as a reference value, if the value is smaller as the reference value.
     */
    @Test
    public final void testEnsureSmallerWithLongParameterThrowsNoException() {
        Condition.ensureSmaller(0L, 1L, "message");
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Long} value is
     * smaller as a reference value and expects a class as a parameter, if the value is equal or
     * smaller as the reference value.
     */
    @Test
    public final void testEnsureSmallerWithLongAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureSmaller(1L, 1L, message, IndexOutOfBoundsException.class);
            Assert.fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Long} value is
     * smaller as a reference value and expects a class as a parameter, if the value is smaller as
     * the reference value.
     */
    @Test
    public final void testEnsureSmallerWithLongAndClassParametersThrowsNoException() {
        Condition.ensureSmaller(0L, 1L, "message", IndexOutOfBoundsException.class);
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Float} value is
     * smaller as a reference value, if the value is equal or greater as the reference value.
     */
    @Test
    public final void testEnsureSmallerWithFloatParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureSmaller(1f, 1f, message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Float} value is
     * smaller as a reference value, if the value is smaller as the reference value.
     */
    @Test
    public final void testEnsureSmallerWithFloatParameterThrowsNoException() {
        Condition.ensureSmaller(0f, 1f, "message");
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Float} value is
     * smaller as a reference value and expects a class as a parameter, if the value is equal or
     * smaller as the reference value.
     */
    @Test
    public final void testEnsureSmallerWithFloatAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureSmaller(1f, 1f, message, IndexOutOfBoundsException.class);
            Assert.fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Float} value is
     * smaller as a reference value and expects a class as a parameter, if the value is smaller as
     * the reference value.
     */
    @Test
    public final void testEnsureSmallerWithFloatAndClassParametersThrowsNoException() {
        Condition.ensureSmaller(0f, 1f, "message", IndexOutOfBoundsException.class);
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Double} value is
     * smaller as a reference value, if the value is equal or greater as the reference value.
     */
    @Test
    public final void testEnsureSmallerWithDoubleParameterThrowsException() {
        String message = "message";

        try {
            Condition.ensureSmaller(1d, 1d, message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Double} value is
     * smaller as a reference value, if the value is smaller as the reference value.
     */
    @Test
    public final void testEnsureSmallerWithDoubleParameterThrowsNoException() {
        Condition.ensureSmaller(0d, 1d, "message");
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Double} value is
     * smaller as a reference value and expects a class as a parameter, if the value is equal or
     * smaller as the reference value.
     */
    @Test
    public final void testEnsureSmallerWithDoubleAndClassParametersThrowsException() {
        String message = "message";

        try {
            Condition.ensureSmaller(1d, 1d, message, IndexOutOfBoundsException.class);
            Assert.fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link Double} value is
     * smaller as a reference value and expects a class as a parameter, if the value is smaller as
     * the reference value.
     */
    @Test
    public final void testEnsureSmallerWithDoubleAndClassParametersThrowsNoException() {
        Condition.ensureSmaller(0d, 1d, "message", IndexOutOfBoundsException.class);
    }

    /**
     * Tests the functionality of the method, which allows to ensure that an {@link Iterable} is not
     * empty, if the iterable is empty.
     */
    @Test
    public final void testEnsureIterableNotEmptyThrowsException() {
        String message = "message";
        List<Object> list = new LinkedList<>();

        try {
            Condition.ensureNotEmpty(list, message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that an {@link Iterable} is not
     * empty, if the iterable is not empty.
     */
    @Test
    public final void testEnsureIterableNotEmptyThrowsNoException() {
        List<Object> list = new LinkedList<>();
        list.add(new Object());
        Condition.ensureNotEmpty(list, "message");
    }

    /**
     * Tests the functionality of the method, which allows to ensure that an {@link Iterable} is not
     * empty and expects a class as a parameter, if the iterable is empty.
     */
    @Test
    public final void testEnsureIterableNotEmptyWithClassParameterThrowsException() {
        String message = "message";
        List<Object> list = new LinkedList<>();

        try {
            Condition.ensureNotEmpty(list, message, IllegalStateException.class);
            Assert.fail();
        } catch (IllegalStateException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that an {@link Iterable} is not
     * empty and expects a class as a parameter, if the iterable is not empty.
     */
    @Test
    public final void testEnsureIterableNotEmptyWithClassParameterThrowsNoException() {
        List<Object> list = new LinkedList<>();
        list.add(new Object());
        Condition.ensureNotEmpty(list, "message", IllegalStateException.class);
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link File} exists, if
     * the file does not exist.
     */
    @Test
    public final void testEnsureFileExistsThrowsException() {
        String message = "message";
        File file = new File(getFile("test.txt").getParentFile(), "foo.txt");

        try {
            Condition.ensureFileExists(file, message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link File} exists, if
     * the file does exist.
     *
     * @throws IOException The exception, which is thrown, if an error occurs while creating the
     *                     file
     */
    @Test
    public final void testEnsureFileExistsThrowsNoException() throws IOException {
        String message = "message";
        File file = getFile("test.txt");
        Condition.ensureFileExists(file, message);
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link File} exists and
     * expects a class as a parameter, if the file does not exist.
     */
    @Test
    public final void testEnsureFileExistsWithClassParameterThrowsException() {
        String message = "message";
        File file = new File(getFile("test.txt").getParentFile(), "foo.txt");

        try {
            Condition.ensureFileExists(file, message, IllegalStateException.class);
            Assert.fail();
        } catch (IllegalStateException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link File} exists and
     * expects a class as a paramter, if the file does exist.
     *
     * @throws IOException The exception, which is thrown, if an error occurs while creating the
     *                     file
     */
    @Test
    public final void testEnsureFileExistsWithClassParameterThrowsNoException() throws IOException {
        String message = "message";
        File file = getFile("test.txt");
        Condition.ensureFileExists(file, message, IllegalStateException.class);
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link File} is a
     * directory, if the file is not a directory.
     *
     * @throws IOException The exception, which is thrown, if an error occurs while creating the
     *                     file
     */
    @Test
    public final void testEnsureFileIsDirectoryThrowsException() throws IOException {
        String message = "message";
        File file = getFile("test.txt");

        try {
            Condition.ensureFileIsDirectory(file, message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link File} is a
     * directory, if the file is a directory.
     *
     * @throws IOException The exception, which is thrown, if an error occurs while creating the
     *                     file
     */
    @Test
    public final void testEnsureFileIsDirectoryThrowsNoException() throws IOException {
        String message = "message";
        File file = getFile("test.txt").getParentFile();
        Condition.ensureFileIsDirectory(file, message);
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link File} is a
     * directory and expects a class as a parameter, if the file is not a directory.
     *
     * @throws IOException The exception, which is thrown, if an error occurs while creating the
     *                     file
     */
    @Test
    public final void testEnsureFileIsDirectoryWithClassParameterThrowsException()
            throws IOException {
        String message = "message";
        File file = getFile("test.txt");

        try {
            Condition.ensureFileIsDirectory(file, message, IllegalStateException.class);
            Assert.fail();
        } catch (IllegalStateException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link File} is a
     * directory and expects a class as a parameter, if the file is a directory.
     *
     * @throws IOException The exception, which is thrown, if an error occurs while creating the
     *                     file
     */
    @Test
    public final void testEnsureFileIsDirectoryWithClassParameterThrowsNoException()
            throws IOException {
        String message = "message";
        File file = getFile("test.txt").getParentFile();
        Condition.ensureFileIsDirectory(file, message, IllegalStateException.class);
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link File} is not a
     * directory, if the file is a directory.
     *
     * @throws IOException The exception, which is thrown, if an error occurs while creating the
     *                     file
     */
    @Test
    public final void testEnsureFileIsNoDirectoryThrowsException() throws IOException {
        String message = "message";
        File file = getFile("test.txt").getParentFile();

        try {
            Condition.ensureFileIsNoDirectory(file, message);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link File} is not a
     * directory, if the file is not a directory.
     *
     * @throws IOException The exception, which is thrown, if an error occurs while creating the
     *                     file
     */
    @Test
    public final void testEnsureFileIsNoDirectoryThrowsNoException() throws IOException {
        String message = "message";
        File file = getFile("test.txt");
        Condition.ensureFileIsNoDirectory(file, message);
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link File} is not a
     * directory and expects a class as a parameter, if the file is a directory.
     *
     * @throws IOException The exception, which is thrown, if an error occurs while creating the
     *                     file
     */
    @Test
    public final void testEnsureFileIsNoDirectoryWithClassParameterThrowsException()
            throws IOException {
        String message = "message";
        File file = getFile("test.txt").getParentFile();

        try {
            Condition.ensureFileIsNoDirectory(file, message, IllegalStateException.class);
            Assert.fail();
        } catch (IllegalStateException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Tests the functionality of the method, which allows to ensure that a {@link File} is not a
     * directory and expects a class as a parameter, if the file is not a directory.
     *
     * @throws IOException The exception, which is thrown, if an error occurs while creating the
     *                     file
     */
    @Test
    public final void testEnsureFileIsNoDirectoryWithClassParameterThrowsNoException()
            throws IOException {
        String message = "message";
        File file = getFile("test.txt");
        Condition.ensureFileIsNoDirectory(file, message, IllegalStateException.class);
    }

}
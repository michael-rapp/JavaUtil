/*
 * Copyright 2017 - 2019 Michael Rapp
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
package de.mrapp.util

import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import java.io.File
import java.io.IOException
import java.util.*
import kotlin.test.Test

/**
 * Tests the functionality of the class [Condition].
 *
 * @author Michael Rapp
 */
class ConditionTest : AbstractFileTest() {

    @Test
    fun testEnsureTrueThrowsException() {
        val message = "message"

        try {
            Condition.ensureTrue(false, message)
            fail()
        } catch (e: IllegalArgumentException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureTrueThrowsNoException() {
        Condition.ensureTrue(true, "message")
    }

    @Test
    fun testEnsureTrueWithClassParameterThrowsException() {
        val message = "message"

        try {
            Condition.ensureTrue(false, message, IllegalStateException::class.java)
            fail()
        } catch (e: IllegalStateException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureTrueWithClassParameterThrowsNoException() {
        Condition.ensureTrue(true, "message", IllegalStateException::class.java)
    }

    @Test
    fun testEnsureFalseThrowsException() {
        val message = "message"

        try {
            Condition.ensureFalse(true, message)
            fail()
        } catch (e: IllegalArgumentException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureFalseThrowsNoException() {
        Condition.ensureFalse(false, "message")
    }

    @Test
    fun testEnsureFalseWithClassParameterThrowsException() {
        val message = "message"

        try {
            Condition.ensureFalse(true, message, IllegalStateException::class.java)
            fail()
        } catch (e: IllegalStateException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureFalseWithClassParameterThrowsNoException() {
        Condition.ensureFalse(false, "message", IllegalStateException::class.java)
    }

    @Test
    fun testEnsureEqualThrowsException1() {
        val message = "message"

        try {
            Condition.ensureEqual("foo", "bar", message)
            fail()
        } catch (e: IllegalArgumentException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureEqualThrowsException2() {
        val message = "message"

        try {
            Condition.ensureEqual(null, "bar", message)
            fail()
        } catch (e: IllegalArgumentException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureEqualThrowsException3() {
        val message = "message"

        try {
            Condition.ensureEqual("foo", null, message)
            fail()
        } catch (e: IllegalArgumentException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureEqualThrowsNoException() {
        Condition.ensureEqual("foo", "foo", "message")
        Condition.ensureEqual(null, null, "message")
    }

    @Test
    fun testEnsureNotEqualThrowsException1() {
        val message = "message"

        try {
            Condition.ensureNotEqual("foo", "foo", message)
            fail()
        } catch (e: IllegalArgumentException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureNotEqualThrowsException2() {
        val message = "message"

        try {
            Condition.ensureNotEqual(null, null, message)
            fail()
        } catch (e: IllegalArgumentException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureNotEqualThrowsNoException() {
        Condition.ensureNotEqual(false, "foo", "bar")
        Condition.ensureNotEqual(false, "foo", null)
        Condition.ensureNotEqual(false, null, "bar")
    }

    @Test
    fun testEnsureNotNullThrowsException() {
        val message = "message"

        try {
            Condition.ensureNotNull(null, message)
            fail()
        } catch (e: IllegalArgumentException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureNotNullThrowsNoException() {
        Condition.ensureNotNull(Any(), "message")
    }

    @Test
    fun testEnsureNotNullWithClassParameterThrowsException() {
        val message = "message"

        try {
            Condition.ensureNotNull(null, message, IllegalStateException::class.java)
            fail()
        } catch (e: IllegalStateException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureNotNullWithClassParameterThrowsNoException() {
        Condition.ensureNotNull(Any(), "message", IllegalArgumentException::class.java)
    }

    @Test
    fun testEnsureNotEmptyThrowsException() {
        val message = "message"

        try {
            Condition.ensureNotEmpty("", message)
            fail()
        } catch (e: IllegalArgumentException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureNotEmptyThrowsNoException() {
        Condition.ensureNotEmpty("text", "message")
    }

    @Test
    fun testEnsureNotEmptyWithClassParameterThrowsException() {
        val message = "message"

        try {
            Condition.ensureNotEmpty("", message, NullPointerException::class.java)
            fail()
        } catch (e: NullPointerException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureNotEmptyWithClassParameterThrowsNoException() {
        Condition.ensureNotEmpty("text", "message", NullPointerException::class.java)
    }

    @Test
    fun testEnsureHasTextThrowsException() {
        val message = "message"

        try {
            Condition.ensureHasText(null, message)
            fail()
        } catch (e: IllegalArgumentException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureHasTextThrowsException2() {
        val message = "message"

        try {
            Condition.ensureHasText("", message)
            fail()
        } catch (e: IllegalArgumentException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureHasTextThrowsException3() {
        val message = "message"

        try {
            Condition.ensureHasText("  ", message)
            fail()
        } catch (e: IllegalArgumentException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureHasTextThrowsNoException() {
        Condition.ensureHasText("text", "message")
    }

    @Test
    fun testEnsureHasTextWithClassParameterThrowsException() {
        val message = "message"

        try {
            Condition.ensureHasText(null, message, NullPointerException::class.java)
            fail()
        } catch (e: NullPointerException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureHasTextWithClassParameterThrowsException2() {
        val message = "message"

        try {
            Condition.ensureHasText("", message, NullPointerException::class.java)
            fail()
        } catch (e: NullPointerException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureHasTextWithClassParameterThrowsException3() {
        val message = "message"

        try {
            Condition.ensureHasText("   ", message, NullPointerException::class.java)
            fail()
        } catch (e: NullPointerException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureHasTextWithClassParameterThrowsNoException() {
        Condition.ensureHasText("text", "message", NullPointerException::class.java)
    }

    @Test
    fun testEnsureAtLeastWithShortParameterThrowsException() {
        val message = "message"

        try {
            Condition.ensureAtLeast(0.toShort(), 1.toShort(), message)
            fail()
        } catch (e: IllegalArgumentException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureAtLeastWithShortParameterThrowsNoException() {
        Condition.ensureAtLeast(1.toShort(), 1.toShort(), "message")
    }

    @Test
    fun testEnsureAtLeastWithShortAndClassParametersThrowsException() {
        val message = "message"

        try {
            Condition.ensureAtLeast(0.toShort(), 1.toShort(), message, IndexOutOfBoundsException::class.java)
            fail()
        } catch (e: IndexOutOfBoundsException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureAtLeastWithShortAndClassParametersThrowsNoException() {
        Condition.ensureAtLeast(1.toShort(), 1.toShort(), "message", IndexOutOfBoundsException::class.java)
    }

    @Test
    fun testEnsureAtLeastWithIntegerParameterThrowsException() {
        val message = "message"

        try {
            Condition.ensureAtLeast(0, 1, message)
            fail()
        } catch (e: IllegalArgumentException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureAtLeastWithIntegerParameterThrowsNoException() {
        Condition.ensureAtLeast(1, 1, "message")
    }

    @Test
    fun testEnsureAtLeastWithIntegerAndClassParametersThrowsException() {
        val message = "message"

        try {
            Condition.ensureAtLeast(0, 1, message, IndexOutOfBoundsException::class.java)
            fail()
        } catch (e: IndexOutOfBoundsException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureAtLeastWithIntegerAndClassParametersThrowsNoException() {
        Condition.ensureAtLeast(1, 1, "message", IndexOutOfBoundsException::class.java)
    }

    @Test
    fun testEnsureAtLeastWithLongParameterThrowsException() {
        val message = "message"

        try {
            Condition.ensureAtLeast(0L, 1L, message)
            fail()
        } catch (e: IllegalArgumentException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureAtLeastWithLongParameterThrowsNoException() {
        Condition.ensureAtLeast(1L, 1L, "message")
    }

    @Test
    fun testEnsureAtLeastWithLongAndClassParametersThrowsException() {
        val message = "message"

        try {
            Condition.ensureAtLeast(0L, 1L, message, IndexOutOfBoundsException::class.java)
            fail()
        } catch (e: IndexOutOfBoundsException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureAtLeastWithLongAndClassParametersThrowsNoException() {
        Condition.ensureAtLeast(1L, 1L, "message", IndexOutOfBoundsException::class.java)
    }

    @Test
    fun testEnsureAtLeastWithFloatParameterThrowsException() {
        val message = "message"

        try {
            Condition.ensureAtLeast(0f, 1f, message)
            fail()
        } catch (e: IllegalArgumentException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureAtLeastWithFloatParameterThrowsNoException() {
        Condition.ensureAtLeast(1f, 1f, "message")
    }

    @Test
    fun testEnsureAtLeastWithFloatAndClassParametersThrowsException() {
        val message = "message"

        try {
            Condition.ensureAtLeast(0f, 1f, message, IndexOutOfBoundsException::class.java)
            fail()
        } catch (e: IndexOutOfBoundsException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureAtLeastWithFloatAndClassParametersThrowsNoException() {
        Condition.ensureAtLeast(1f, 1f, "message", IndexOutOfBoundsException::class.java)
    }

    @Test
    fun testEnsureAtLeastWithDoubleParameterThrowsException() {
        val message = "message"

        try {
            Condition.ensureAtLeast(0.0, 1.0, message)
            fail()
        } catch (e: IllegalArgumentException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureAtLeastWithDoubleParameterThrowsNoException() {
        Condition.ensureAtLeast(1.0, 1.0, "message")
    }

    @Test
    fun testEnsureAtLeastWithDoubleAndClassParametersThrowsException() {
        val message = "message"

        try {
            Condition.ensureAtLeast(0.0, 1.0, message, IndexOutOfBoundsException::class.java)
            fail()
        } catch (e: IndexOutOfBoundsException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureAtLeastWithDoubleAndClassParametersThrowsNoException() {
        Condition.ensureAtLeast(1.0, 1.0, "message", IndexOutOfBoundsException::class.java)
    }

    @Test
    fun testEnsureAtMaximumWithShortParameterThrowsException() {
        val message = "message"

        try {
            Condition.ensureAtMaximum(2.toShort(), 1.toShort(), message)
            fail()
        } catch (e: IllegalArgumentException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureAtMaximumWithShortParameterThrowsNoException() {
        Condition.ensureAtMaximum(1.toShort(), 1.toShort(), "message")
    }

    @Test
    fun testEnsureAtMaximumWithShortAndClassParametersThrowsException() {
        val message = "message"

        try {
            Condition.ensureAtMaximum(2.toShort(), 1.toShort(), message,
                    IndexOutOfBoundsException::class.java)
            fail()
        } catch (e: IndexOutOfBoundsException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureAtMaximumWithShortAndClassParametersThrowsNoException() {
        Condition.ensureAtMaximum(1.toShort(), 1.toShort(), "message", IndexOutOfBoundsException::class.java)
    }

    @Test
    fun testEnsureAtMaximumWithIntegerParameterThrowsException() {
        val message = "message"

        try {
            Condition.ensureAtMaximum(2, 1, message)
            fail()
        } catch (e: IllegalArgumentException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureAtMaximumWithIntegerParameterThrowsNoException() {
        Condition.ensureAtMaximum(1, 1, "message")
    }

    @Test
    fun testEnsureAtMaximumWithIntegerAndClassParametersThrowsException() {
        val message = "message"

        try {
            Condition.ensureAtMaximum(2, 1, message, IndexOutOfBoundsException::class.java)
            fail()
        } catch (e: IndexOutOfBoundsException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureAtMaximumWithIntegerAndClassParametersThrowsNoException() {
        Condition.ensureAtMaximum(1, 1, "message", IndexOutOfBoundsException::class.java)
    }

    @Test
    fun testEnsureAtMaximumWithLongParameterThrowsException() {
        val message = "message"

        try {
            Condition.ensureAtMaximum(2L, 1L, message)
            fail()
        } catch (e: IllegalArgumentException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureAtMaximumWithLongParameterThrowsNoException() {
        Condition.ensureAtMaximum(1L, 1L, "message")
    }

    @Test
    fun testEnsureAtMaximumWithLongAndClassParametersThrowsException() {
        val message = "message"

        try {
            Condition.ensureAtMaximum(2L, 1L, message, IndexOutOfBoundsException::class.java)
            fail()
        } catch (e: IndexOutOfBoundsException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureAtMaximumWithLongAndClassParametersThrowsNoException() {
        Condition.ensureAtMaximum(1L, 1L, "message", IndexOutOfBoundsException::class.java)
    }

    @Test
    fun testEnsureAtMaximumWithFloatParameterThrowsException() {
        val message = "message"

        try {
            Condition.ensureAtMaximum(2f, 1f, message)
            fail()
        } catch (e: IllegalArgumentException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureAtMaximumWithFloatParameterThrowsNoException() {
        Condition.ensureAtMaximum(1f, 1f, "message")
    }

    @Test
    fun testEnsureAtMaximumWithFloatAndClassParametersThrowsException() {
        val message = "message"

        try {
            Condition.ensureAtMaximum(2f, 1f, message, IndexOutOfBoundsException::class.java)
            fail()
        } catch (e: IndexOutOfBoundsException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureAtMaximumWithFloatAndClassParametersThrowsNoException() {
        Condition.ensureAtMaximum(1f, 1f, "message", IndexOutOfBoundsException::class.java)
    }

    @Test
    fun testEnsureAtMaximumWithDoubleParameterThrowsException() {
        val message = "message"

        try {
            Condition.ensureAtMaximum(2.0, 1.0, message)
            fail()
        } catch (e: IllegalArgumentException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureAtMaximumWithDoubleParameterThrowsNoException() {
        Condition.ensureAtMaximum(1.0, 1.0, "message")
    }

    @Test
    fun testEnsureAtMaximumWithDoubleAndClassParametersThrowsException() {
        val message = "message"

        try {
            Condition.ensureAtMaximum(2.0, 1.0, message, IndexOutOfBoundsException::class.java)
            fail()
        } catch (e: IndexOutOfBoundsException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureAtMaximumWithDoubleAndClassParametersThrowsNoException() {
        Condition.ensureAtMaximum(1.0, 1.0, "message", IndexOutOfBoundsException::class.java)
    }

    @Test
    fun testEnsureGreaterWithShortParameterThrowsException() {
        val message = "message"

        try {
            Condition.ensureGreater(1.toShort(), 1.toShort(), message)
            fail()
        } catch (e: IllegalArgumentException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureGreaterWithShortParameterThrowsNoException() {
        Condition.ensureGreater(2.toShort(), 1.toShort(), "message")
    }

    @Test
    fun testEnsureGreaterWithShortAndClassParametersThrowsException() {
        val message = "message"

        try {
            Condition.ensureGreater(1.toShort(), 1.toShort(), message, IndexOutOfBoundsException::class.java)
            fail()
        } catch (e: IndexOutOfBoundsException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureGreaterWithShortAndClassParametersThrowsNoException() {
        Condition.ensureGreater(2.toShort(), 1.toShort(), "message", IndexOutOfBoundsException::class.java)
    }

    @Test
    fun testEnsureGreaterWithIntegerParameterThrowsException() {
        val message = "message"

        try {
            Condition.ensureGreater(1, 1, message)
            fail()
        } catch (e: IllegalArgumentException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureGreaterWithIntegerParameterThrowsNoException() {
        Condition.ensureGreater(2, 1, "message")
    }

    @Test
    fun testEnsureGreaterWithIntegerAndClassParametersThrowsException() {
        val message = "message"

        try {
            Condition.ensureGreater(1, 1, message, IndexOutOfBoundsException::class.java)
            fail()
        } catch (e: IndexOutOfBoundsException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureGreaterWithIntegerAndClassParametersThrowsNoException() {
        Condition.ensureGreater(2, 1, "message", IndexOutOfBoundsException::class.java)
    }

    @Test
    fun testEnsureGreaterWithLongParameterThrowsException() {
        val message = "message"

        try {
            Condition.ensureGreater(1L, 1L, message)
            fail()
        } catch (e: IllegalArgumentException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureGreaterWithLongParameterThrowsNoException() {
        Condition.ensureGreater(2L, 1L, "message")
    }

    @Test
    fun testEnsureGreaterWithLongAndClassParametersThrowsException() {
        val message = "message"

        try {
            Condition.ensureGreater(1L, 1L, message, IndexOutOfBoundsException::class.java)
            fail()
        } catch (e: IndexOutOfBoundsException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureGreaterWithLongAndClassParametersThrowsNoException() {
        Condition.ensureGreater(2L, 1L, "message", IndexOutOfBoundsException::class.java)
    }

    @Test
    fun testEnsureGreaterWithFloatParameterThrowsException() {
        val message = "message"

        try {
            Condition.ensureGreater(1f, 1f, message)
            fail()
        } catch (e: IllegalArgumentException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureGreaterWithFloatParameterThrowsNoException() {
        Condition.ensureGreater(2f, 1f, "message")
    }

    @Test
    fun testEnsureGreaterWithFloatAndClassParametersThrowsException() {
        val message = "message"

        try {
            Condition.ensureGreater(1f, 1f, message, IndexOutOfBoundsException::class.java)
            fail()
        } catch (e: IndexOutOfBoundsException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureGreaterWithFloatAndClassParametersThrowsNoException() {
        Condition.ensureGreater(2f, 1f, "message", IndexOutOfBoundsException::class.java)
    }

    @Test
    fun testEnsureGreaterWithDoubleParameterThrowsException() {
        val message = "message"

        try {
            Condition.ensureGreater(1.0, 1.0, message)
            fail()
        } catch (e: IllegalArgumentException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureGreaterWithDoubleParameterThrowsNoException() {
        Condition.ensureGreater(2.0, 1.0, "message")
    }

    @Test
    fun testEnsureGreaterWithDoubleAndClassParametersThrowsException() {
        val message = "message"

        try {
            Condition.ensureGreater(1.0, 1.0, message, IndexOutOfBoundsException::class.java)
            fail()
        } catch (e: IndexOutOfBoundsException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureGreaterWithDoubleAndClassParametersThrowsNoException() {
        Condition.ensureGreater(2.0, 1.0, "message", IndexOutOfBoundsException::class.java)
    }

    @Test
    fun testEnsureSmallerWithShortParameterThrowsException() {
        val message = "message"

        try {
            Condition.ensureSmaller(1.toShort(), 1.toShort(), message)
            fail()
        } catch (e: IllegalArgumentException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureSmallerWithShortParameterThrowsNoException() {
        Condition.ensureSmaller(0.toShort(), 1.toShort(), "message")
    }

    @Test
    fun testEnsureSmallerWithShortAndClassParametersThrowsException() {
        val message = "message"

        try {
            Condition.ensureSmaller(1.toShort(), 1.toShort(), message, IndexOutOfBoundsException::class.java)
            fail()
        } catch (e: IndexOutOfBoundsException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureSmallerWithShortAndClassParametersThrowsNoException() {
        Condition.ensureSmaller(0.toShort(), 1.toShort(), "message", IndexOutOfBoundsException::class.java)
    }

    @Test
    fun testEnsureSmallerWithIntegerParameterThrowsException() {
        val message = "message"

        try {
            Condition.ensureSmaller(1, 1, message)
            fail()
        } catch (e: IllegalArgumentException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureSmallerWithIntegerParameterThrowsNoException() {
        Condition.ensureSmaller(0, 1, "message")
    }

    @Test
    fun testEnsureSmallerWithIntegerAndClassParametersThrowsException() {
        val message = "message"

        try {
            Condition.ensureSmaller(1, 1, message, IndexOutOfBoundsException::class.java)
            fail()
        } catch (e: IndexOutOfBoundsException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureSmallerWithIntegerAndClassParametersThrowsNoException() {
        Condition.ensureSmaller(0, 1, "message", IndexOutOfBoundsException::class.java)
    }

    @Test
    fun testEnsureSmallerWithLongParameterThrowsException() {
        val message = "message"

        try {
            Condition.ensureSmaller(1L, 1L, message)
            fail()
        } catch (e: IllegalArgumentException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureSmallerWithLongParameterThrowsNoException() {
        Condition.ensureSmaller(0L, 1L, "message")
    }

    @Test
    fun testEnsureSmallerWithLongAndClassParametersThrowsException() {
        val message = "message"

        try {
            Condition.ensureSmaller(1L, 1L, message, IndexOutOfBoundsException::class.java)
            fail()
        } catch (e: IndexOutOfBoundsException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureSmallerWithLongAndClassParametersThrowsNoException() {
        Condition.ensureSmaller(0L, 1L, "message", IndexOutOfBoundsException::class.java)
    }

    @Test
    fun testEnsureSmallerWithFloatParameterThrowsException() {
        val message = "message"

        try {
            Condition.ensureSmaller(1f, 1f, message)
            fail()
        } catch (e: IllegalArgumentException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureSmallerWithFloatParameterThrowsNoException() {
        Condition.ensureSmaller(0f, 1f, "message")
    }

    @Test
    fun testEnsureSmallerWithFloatAndClassParametersThrowsException() {
        val message = "message"

        try {
            Condition.ensureSmaller(1f, 1f, message, IndexOutOfBoundsException::class.java)
            fail()
        } catch (e: IndexOutOfBoundsException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureSmallerWithFloatAndClassParametersThrowsNoException() {
        Condition.ensureSmaller(0f, 1f, "message", IndexOutOfBoundsException::class.java)
    }

    @Test
    fun testEnsureSmallerWithDoubleParameterThrowsException() {
        val message = "message"

        try {
            Condition.ensureSmaller(1.0, 1.0, message)
            fail()
        } catch (e: IllegalArgumentException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureSmallerWithDoubleParameterThrowsNoException() {
        Condition.ensureSmaller(0.0, 1.0, "message")
    }

    @Test
    fun testEnsureSmallerWithDoubleAndClassParametersThrowsException() {
        val message = "message"

        try {
            Condition.ensureSmaller(1.0, 1.0, message, IndexOutOfBoundsException::class.java)
            fail()
        } catch (e: IndexOutOfBoundsException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureSmallerWithDoubleAndClassParametersThrowsNoException() {
        Condition.ensureSmaller(0.0, 1.0, "message", IndexOutOfBoundsException::class.java)
    }

    @Test
    fun testEnsureIterableNotEmptyThrowsException() {
        val message = "message"
        val list = LinkedList<Any>()

        try {
            Condition.ensureNotEmpty(list, message)
            fail()
        } catch (e: IllegalArgumentException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureIterableNotEmptyThrowsNoException() {
        val list = LinkedList<Any>()
        list.add(Any())
        Condition.ensureNotEmpty(list, "message")
    }

    @Test
    fun testEnsureIterableNotEmptyWithClassParameterThrowsException() {
        val message = "message"
        val list = LinkedList<Any>()

        try {
            Condition.ensureNotEmpty(list, message, IllegalStateException::class.java)
            fail()
        } catch (e: IllegalStateException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    fun testEnsureIterableNotEmptyWithClassParameterThrowsNoException() {
        val list = LinkedList<Any>()
        list.add(Any())
        Condition.ensureNotEmpty(list, "message", IllegalStateException::class.java)
    }

    @Test
    fun testEnsureFileExistsThrowsException() {
        val message = "message"
        val file = File(testFile.parentFile, "foo.txt")

        try {
            Condition.ensureFileExists(file, message)
            fail()
        } catch (e: IllegalArgumentException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    @Throws(IOException::class)
    fun testEnsureFileExistsThrowsNoException() {
        val message = "message"
        val file = testFile
        Condition.ensureFileExists(file, message)
    }

    @Test
    fun testEnsureFileExistsWithClassParameterThrowsException() {
        val message = "message"
        val file = File(testFile.parentFile, "foo.txt")

        try {
            Condition.ensureFileExists(file, message, IllegalStateException::class.java)
            fail()
        } catch (e: IllegalStateException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    @Throws(IOException::class)
    fun testEnsureFileExistsWithClassParameterThrowsNoException() {
        val message = "message"
        val file = testFile
        Condition.ensureFileExists(file, message, IllegalStateException::class.java)
    }

    @Test
    @Throws(IOException::class)
    fun testEnsureFileIsDirectoryThrowsException() {
        val message = "message"
        val file = testFile

        try {
            Condition.ensureFileIsDirectory(file, message)
            fail()
        } catch (e: IllegalArgumentException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    @Throws(IOException::class)
    fun testEnsureFileIsDirectoryThrowsNoException() {
        val message = "message"
        val file = testFile.parentFile
        Condition.ensureFileIsDirectory(file, message)
    }

    @Test
    @Throws(IOException::class)
    fun testEnsureFileIsDirectoryWithClassParameterThrowsException() {
        val message = "message"
        val file = testFile

        try {
            Condition.ensureFileIsDirectory(file, message, IllegalStateException::class.java)
            fail()
        } catch (e: IllegalStateException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    @Throws(IOException::class)
    fun testEnsureFileIsDirectoryWithClassParameterThrowsNoException() {
        val message = "message"
        val file = testFile.parentFile
        Condition.ensureFileIsDirectory(file, message, IllegalStateException::class.java)
    }

    @Test
    @Throws(IOException::class)
    fun testEnsureFileIsNoDirectoryThrowsException() {
        val message = "message"
        val file = testFile.parentFile

        try {
            Condition.ensureFileIsNoDirectory(file, message)
            fail()
        } catch (e: IllegalArgumentException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    @Throws(IOException::class)
    fun testEnsureFileIsNoDirectoryThrowsNoException() {
        val message = "message"
        val file = testFile
        Condition.ensureFileIsNoDirectory(file, message)
    }

    @Test
    @Throws(IOException::class)
    fun testEnsureFileIsNoDirectoryWithClassParameterThrowsException() {
        val message = "message"
        val file = testFile.parentFile

        try {
            Condition.ensureFileIsNoDirectory(file, message, IllegalStateException::class.java)
            fail()
        } catch (e: IllegalStateException) {
            assertEquals(message, e.message)
        }
    }

    @Test
    @Throws(IOException::class)
    fun testEnsureFileIsNoDirectoryWithClassParameterThrowsNoException() {
        val message = "message"
        val file = testFile
        Condition.ensureFileIsNoDirectory(file, message, IllegalStateException::class.java)
    }

}
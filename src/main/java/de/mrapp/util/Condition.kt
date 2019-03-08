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

import java.io.File

/**
 * An utility class that provides methods, which allow to ensure, that variables and objects fulfill
 * certain conditions. If a condition is violated, an exception is thrown by each of these methods.
 *
 * @author Michael Rapp
 * @since 1.0.0
 */
object Condition {

    private fun throwException(exceptionMessage: String?,
                               exceptionClass: Class<out RuntimeException>) {
        val exception = try {
            val constructor = exceptionClass.getConstructor(String::class.java)
            constructor.newInstance(exceptionMessage)
        } catch (e: Exception) {
            RuntimeException(exceptionMessage)
        }
        throw exception
    }

    /**
     * Ensures that a specific boolean [expression] is true. Otherwise an [IllegalArgumentException]
     * using a specific [exceptionMessage] is thrown.
     */
    fun ensureTrue(expression: Boolean, exceptionMessage: String?) {
        ensureTrue(expression, exceptionMessage, IllegalArgumentException::class.java)
    }

    /**
     * Ensures that a specific boolean [expression] is true. Otherwise a runtime exception of a
     * specific [exceptionClass] and using a specific [exceptionMessage] is thrown.
     */
    fun ensureTrue(expression: Boolean, exceptionMessage: String?,
                   exceptionClass: Class<out RuntimeException>) {
        if (!expression) {
            throwException(exceptionMessage, exceptionClass)
        }
    }

    /**
     * Ensures that a specific boolean [expression] is false. Otherwise an
     * [IllegalArgumentException] using a specific [exceptionMessage] is thrown.
     */
    fun ensureFalse(expression: Boolean, exceptionMessage: String?) {
        ensureFalse(expression, exceptionMessage, IllegalArgumentException::class.java)
    }

    /**
     * Ensures that a specific boolean [expression] is false. Otherwise a runtime exception of a
     * specific [exceptionClass] and using a specific [exceptionMessage] is thrown.
     */
    fun ensureFalse(expression: Boolean, exceptionMessage: String?,
                    exceptionClass: Class<out RuntimeException>) {
        if (expression) {
            throwException(exceptionMessage, exceptionClass)
        }
    }

    /**
     * Ensures that two objects [object1] and [object2] are equal. Otherwise an
     * [IllegalArgumentException] using a specific [exceptionMessage] is thrown.
     */
    fun ensureEqual(object1: Any?, object2: Any?, exceptionMessage: String?) {
        ensureEqual(object1, object2, exceptionMessage, IllegalArgumentException::class.java)
    }

    /**
     * Ensures that two objects [object1] and [object2] are equal. Otherwise a runtime exception of
     * a specific [exceptionClass] and using a specific [exceptionMessage] is thrown.
     */
    fun ensureEqual(object1: Any?, object2: Any?, exceptionMessage: String?,
                    exceptionClass: Class<out RuntimeException>) {
        if (object1 == null && object2 != null || object1 != null && object1 != object2) {
            throwException(exceptionMessage, exceptionClass)
        }
    }

    /**
     * Ensures that two objects [object1] and [object2] are not equal. Otherwise an
     * [IllegalArgumentException] using a specific [exceptionMessage] is thrown.
     */
    fun ensureNotEqual(object1: Any?, object2: Any?, exceptionMessage: String?) {
        ensureNotEqual(object1, object2, exceptionMessage, IllegalArgumentException::class.java)
    }

    /**
     * Ensures that two objects [object1] and [object2] are not equal. Otherwise a runtime exception
     * of a specific [exceptionClass] and using a specific [exceptionMessage] is thrown.
     */
    fun ensureNotEqual(object1: Any?, object2: Any?, exceptionMessage: String?,
                       exceptionClass: Class<out RuntimeException>) {
        if (object1 == null && object2 == null || object1 != null && object1 == object2) {
            throwException(exceptionMessage, exceptionClass)
        }
    }

    /**
     * Ensures that a specific object [obj] is not null. Otherwise an [IllegalArgumentException]
     * using a specific [exceptionMessage] is thrown.
     */
    fun ensureNotNull(obj: Any?, exceptionMessage: String?) {
        ensureNotNull(obj, exceptionMessage, IllegalArgumentException::class.java)
    }

    /**
     * Ensures that a specific object [obj] is not null. Otherwise a runtime exception of a specific
     * [exceptionClass] and using a specific [exceptionMessage] is thrown.
     */
    fun ensureNotNull(obj: Any?, exceptionMessage: String?,
                      exceptionClass: Class<out RuntimeException>) {
        if (obj == null) {
            throwException(exceptionMessage, exceptionClass)
        }
    }

    /**
     * Ensures that a specific [text] is neither null, nor empty. Otherwise an
     * [IllegalArgumentException] using a specific [exceptionMessage] is thrown.
     */
    fun ensureNotEmpty(text: CharSequence?, exceptionMessage: String?) {
        ensureNotEmpty(text, exceptionMessage, IllegalArgumentException::class.java)
    }

    /**
     * Ensures that a specific [text] is neither null, nor empty. Otherwise a runtime exception of a
     * specific [exceptionClass] and using a specific [exceptionMessage] is thrown.
     */
    fun ensureNotEmpty(text: CharSequence?, exceptionMessage: String?,
                       exceptionClass: Class<out RuntimeException>) {
        if (TextUtil.isEmpty(text)) {
            throwException(exceptionMessage, exceptionClass)
        }
    }

    /**
     * Ensures that a specific [text] is neither null, nor empty, nor does contain only whitespace.
     * Otherwise an [IllegalArgumentException] using a specific [exceptionMessage] is thrown.
     */
    fun ensureHasText(text: CharSequence?, exceptionMessage: String?) {
        ensureHasText(text, exceptionMessage, IllegalArgumentException::class.java)
    }

    /**
     * Ensures that a specific [text] is neither null, nor empty, nor does contain only whitespace.
     * Otherwise a runtime exception of a specific [exceptionClass] and using a specific
     * [exceptionMessage] is thrown.
     */
    fun ensureHasText(text: CharSequence?, exceptionMessage: String?,
                      exceptionClass: Class<out RuntimeException>) {
        if (TextUtil.hasNoText(text)) {
            throwException(exceptionMessage, exceptionClass)
        }
    }

    /**
     * Ensures that a specific short [value] is at least as great as a reference value. Otherwise an
     * [IllegalArgumentException] using a specific [exceptionMessage] is thrown.
     */
    fun ensureAtLeast(value: Short, referenceValue: Short,
                      exceptionMessage: String?) {
        ensureAtLeast(value, referenceValue, exceptionMessage, IllegalArgumentException::class.java)
    }

    /**
     * Ensures that a specific short [value] is at least as great as a reference value. Otherwise a
     * runtime exception of a specific [exceptionClass] and using a specific [exceptionMessage] is
     * thrown.
     */
    fun ensureAtLeast(value: Short, referenceValue: Short, exceptionMessage: String?,
                      exceptionClass: Class<out RuntimeException>) {
        if (value < referenceValue) {
            throwException(exceptionMessage, exceptionClass)
        }
    }

    /**
     * Ensures that a specific integer [value] is at least as great as a reference value. Otherwise
     * an [IllegalArgumentException] using a specific [exceptionMessage] is thrown.
     */
    fun ensureAtLeast(value: Int, referenceValue: Int, exceptionMessage: String?) {
        ensureAtLeast(value, referenceValue, exceptionMessage, IllegalArgumentException::class.java)
    }

    /**
     * Ensures that a specific integer [value] is at least as great as a reference value. Otherwise
     * a runtime exception of a specific [exceptionClass] and using a specific [exceptionMessage] is
     * thrown.
     */
    fun ensureAtLeast(value: Int, referenceValue: Int, exceptionMessage: String?,
                      exceptionClass: Class<out RuntimeException>) {
        if (value < referenceValue) {
            throwException(exceptionMessage, exceptionClass)
        }
    }

    /**
     * Ensures that a specific long [value] is at least as great as a reference value. Otherwise an
     * [IllegalArgumentException] using a specific [exceptionMessage] is thrown.
     */
    fun ensureAtLeast(value: Long, referenceValue: Long, exceptionMessage: String?) {
        ensureAtLeast(value, referenceValue, exceptionMessage, IllegalArgumentException::class.java)
    }

    /**
     * Ensures that a specific long [value] is at least as great as a reference value. Otherwise a
     * runtime exception of a specific [exceptionClass] and using a specific [exceptionMessage] is
     * thrown.
     */
    fun ensureAtLeast(value: Long, referenceValue: Long, exceptionMessage: String?,
                      exceptionClass: Class<out RuntimeException>) {
        if (value < referenceValue) {
            throwException(exceptionMessage, exceptionClass)
        }
    }

    /**
     * Ensures that a specific float [value] is at least as great as a reference value. Otherwise an
     * [IllegalArgumentException] using a specific [exceptionMessage] is thrown.
     */
    fun ensureAtLeast(value: Float, referenceValue: Float, exceptionMessage: String?) {
        ensureAtLeast(value, referenceValue, exceptionMessage, IllegalArgumentException::class.java)
    }

    /**
     * Ensures that a specific float [value] is at least as great as a reference value. Otherwise a
     * runtime exception of a specific [exceptionClass] and using a specific [exceptionMessage] is
     * thrown.
     */
    fun ensureAtLeast(value: Float, referenceValue: Float, exceptionMessage: String?,
                      exceptionClass: Class<out RuntimeException>) {
        if (value < referenceValue) {
            throwException(exceptionMessage, exceptionClass)
        }
    }

    /**
     * Ensures that a specific double [value] is at least as great as a reference value. Otherwise
     * an [IllegalArgumentException] using a specific [exceptionMessage] is thrown.
     */
    fun ensureAtLeast(value: Double, referenceValue: Double, exceptionMessage: String?) {
        ensureAtLeast(value, referenceValue, exceptionMessage, IllegalArgumentException::class.java)
    }

    /**
     * Ensures that a specific double [value] is at least as great as a reference value. Otherwise a
     * runtime exception of a specific [exceptionClass] and using a specific [exceptionMessage] is
     * thrown.
     */
    fun ensureAtLeast(value: Double, referenceValue: Double, exceptionMessage: String?,
                      exceptionClass: Class<out RuntimeException>) {
        if (value < referenceValue) {
            throwException(exceptionMessage, exceptionClass)
        }
    }

    /**
     * Ensures that a specific short [value] is at maximum as great as a reference value. Otherwise
     * an [IllegalArgumentException] using a specific [exceptionMessage] is thrown.
     */
    fun ensureAtMaximum(value: Short, referenceValue: Short, exceptionMessage: String?) {
        ensureAtMaximum(value, referenceValue, exceptionMessage,
                IllegalArgumentException::class.java)
    }

    /**
     * Ensures that a specific short [value] is at maximum as great as a reference value. Otherwise
     * a runtime exception of a specific [exceptionClass] and using a specific [exceptionMessage] is
     * thrown.
     */
    fun ensureAtMaximum(value: Short, referenceValue: Short, exceptionMessage: String?,
                        exceptionClass: Class<out RuntimeException>) {
        if (value > referenceValue) {
            throwException(exceptionMessage, exceptionClass)
        }
    }

    /**
     * Ensures that a specific integer [value] is at maximum as great as a reference value.
     * Otherwise an [IllegalArgumentException] using a specific [exceptionMessage] is thrown.
     */
    fun ensureAtMaximum(value: Int, referenceValue: Int, exceptionMessage: String?) {
        ensureAtMaximum(value, referenceValue, exceptionMessage,
                IllegalArgumentException::class.java)
    }

    /**
     * Ensures that a specific integer [value] is at maximum as great as a reference value.
     * Otherwise a runtime exception of a specific [exceptionClass] and using a specific
     * [exceptionMessage] is thrown.
     */
    fun ensureAtMaximum(value: Int, referenceValue: Int, exceptionMessage: String?,
                        exceptionClass: Class<out RuntimeException>) {
        if (value > referenceValue) {
            throwException(exceptionMessage, exceptionClass)
        }
    }

    /**
     * Ensures that a specific long [value] is at maximum as great as a reference value. Otherwise
     * an [IllegalArgumentException] using a specific [exceptionMessage] is thrown.
     */
    fun ensureAtMaximum(value: Long, referenceValue: Long, exceptionMessage: String?) {
        ensureAtMaximum(value, referenceValue, exceptionMessage,
                IllegalArgumentException::class.java)
    }

    /**
     * Ensures that a specific long [value] is at maximum as great as a reference value. Otherwise a
     * runtime exception of a specific [exceptionClass] and using a specific [exceptionMessage] is
     * thrown.
     */
    fun ensureAtMaximum(value: Long, referenceValue: Long, exceptionMessage: String?,
                        exceptionClass: Class<out RuntimeException>) {
        if (value > referenceValue) {
            throwException(exceptionMessage, exceptionClass)
        }
    }

    /**
     * Ensures that a specific float [value] is at maximum as great as a reference value. Otherwise
     * an [IllegalArgumentException] using a specific [exceptionMessage] is thrown.
     */
    fun ensureAtMaximum(value: Float, referenceValue: Float, exceptionMessage: String?) {
        ensureAtMaximum(value, referenceValue, exceptionMessage, IllegalArgumentException::class.java)
    }

    /**
     * Ensures that a specific float [value] is at maximum as great as a reference value. Otherwise
     * a runtime exception of a specific [exceptionClass] and using a specific [exceptionMessage] is
     * thrown.
     */
    fun ensureAtMaximum(value: Float, referenceValue: Float, exceptionMessage: String?,
                        exceptionClass: Class<out RuntimeException>) {
        if (value > referenceValue) {
            throwException(exceptionMessage, exceptionClass)
        }
    }

    /**
     * Ensures that a specific double [value] is at maximum as great as a reference value. Otherwise
     * an [IllegalArgumentException] using a specific [exceptionMessage] is thrown.
     */
    fun ensureAtMaximum(value: Double, referenceValue: Double, exceptionMessage: String?) {
        ensureAtMaximum(value, referenceValue, exceptionMessage, IllegalArgumentException::class.java)
    }

    /**
     * Ensures that a specific double [value] is at maximum as great as a reference value. Otherwise
     * a runtime exception of a specific [exceptionClass] and using a specific [exceptionMessage] is
     * thrown.
     */
    fun ensureAtMaximum(value: Double, referenceValue: Double, exceptionMessage: String?,
                        exceptionClass: Class<out RuntimeException>) {
        if (value > referenceValue) {
            throwException(exceptionMessage, exceptionClass)
        }
    }

    /**
     * Ensures that a specific short [value] is greater than a reference value. Otherwise an
     * [IllegalArgumentException] using a specific [exceptionMessage] is thrown.
     */
    fun ensureGreater(value: Short, referenceValue: Short, exceptionMessage: String?) {
        ensureGreater(value, referenceValue, exceptionMessage, IllegalArgumentException::class.java)
    }

    /**
     * Ensures that a specific short [value] is greater than a reference value. Otherwise a runtime
     * exception of a specific [exceptionClass] and using a specific [exceptionMessage] is thrown.
     */
    fun ensureGreater(value: Short, referenceValue: Short, exceptionMessage: String?,
                      exceptionClass: Class<out RuntimeException>) {
        if (value <= referenceValue) {
            throwException(exceptionMessage, exceptionClass)
        }
    }

    /**
     * Ensures that a specific integer [value] is greater than a reference value. Otherwise an
     * [IllegalArgumentException] using a specific [exceptionMessage] is thrown.
     */
    fun ensureGreater(value: Int, referenceValue: Int, exceptionMessage: String?) {
        ensureGreater(value, referenceValue, exceptionMessage, IllegalArgumentException::class.java)
    }

    /**
     * Ensures that a specific integer [value] is greater than a reference value. Otherwise a
     * runtime exception of a specific [exceptionClass] and using a specific [exceptionMessage] is
     * thrown.
     */
    fun ensureGreater(value: Int, referenceValue: Int, exceptionMessage: String?,
                      exceptionClass: Class<out RuntimeException>) {
        if (value <= referenceValue) {
            throwException(exceptionMessage, exceptionClass)
        }
    }

    /**
     * Ensures that a specific long [value] is greater than a reference value. Otherwise an
     * [IllegalArgumentException] using a specific [exceptionMessage] is thrown.
     */
    fun ensureGreater(value: Long, referenceValue: Long, exceptionMessage: String?) {
        ensureGreater(value, referenceValue, exceptionMessage, IllegalArgumentException::class.java)
    }

    /**
     * Ensures that a specific long [value] is greater than a reference value. Otherwise a runtime
     * exception of a specific [exceptionClass] and using a specific [exceptionMessage] is thrown.
     */
    fun ensureGreater(value: Long, referenceValue: Long, exceptionMessage: String?,
                      exceptionClass: Class<out RuntimeException>) {
        if (value <= referenceValue) {
            throwException(exceptionMessage, exceptionClass)
        }
    }

    /**
     * Ensures that a specific float [value] is greater than a reference value. Otherwise an
     * [IllegalArgumentException] using a specific [exceptionMessage] is thrown.
     */
    fun ensureGreater(value: Float, referenceValue: Float, exceptionMessage: String?) {
        ensureGreater(value, referenceValue, exceptionMessage, IllegalArgumentException::class.java)
    }

    /**
     * Ensures that a specific float [value] is greater than a reference value. Otherwise a runtime
     * exception of a specific [exceptionClass] and using a specific [exceptionMessage] is thrown.
     */
    fun ensureGreater(value: Float, referenceValue: Float, exceptionMessage: String?,
                      exceptionClass: Class<out RuntimeException>) {
        if (value <= referenceValue) {
            throwException(exceptionMessage, exceptionClass)
        }
    }

    /**
     * Ensures that a specific double [value] is greater than a reference value. Otherwise an
     * [IllegalArgumentException] using a specific [exceptionMessage] is thrown.
     */
    fun ensureGreater(value: Double, referenceValue: Double, exceptionMessage: String?) {
        ensureGreater(value, referenceValue, exceptionMessage, IllegalArgumentException::class.java)
    }

    /**
     * Ensures that a specific double [value] is greater than a reference value. Otherwise a runtime
     * exception of a specific [exceptionClass] and using a specific [exceptionMessage] is thrown.
     */
    fun ensureGreater(value: Double, referenceValue: Double, exceptionMessage: String?,
                      exceptionClass: Class<out RuntimeException>) {
        if (value <= referenceValue) {
            throwException(exceptionMessage, exceptionClass)
        }
    }

    /**
     * Ensures that a specific short [value] is smaller than a reference value. Otherwise an
     * [IllegalArgumentException] using a specific [exceptionMessage] is thrown.
     */
    fun ensureSmaller(value: Short, referenceValue: Short, exceptionMessage: String?) {
        ensureSmaller(value, referenceValue, exceptionMessage, IllegalArgumentException::class.java)
    }

    /**
     * Ensures that a specific short [value] is smaller than a reference value. Otherwise a runtime
     * exception of a specific [exceptionClass] and using a specific [exceptionMessage] is thrown.
     */
    fun ensureSmaller(value: Short, referenceValue: Short, exceptionMessage: String?,
                      exceptionClass: Class<out RuntimeException>) {
        if (value >= referenceValue) {
            throwException(exceptionMessage, exceptionClass)
        }
    }

    /**
     * Ensures that a specific integer [value] is smaller than a reference value. Otherwise an
     * [IllegalArgumentException] using a specific [exceptionMessage] is thrown.
     */
    fun ensureSmaller(value: Int, referenceValue: Int, exceptionMessage: String?) {
        ensureSmaller(value, referenceValue, exceptionMessage, IllegalArgumentException::class.java)
    }

    /**
     * Ensures that a specific integer [value] is smaller than a reference value. Otherwise a
     * runtime exception of a specific [exceptionClass] and using a specific [exceptionMessage] is
     * thrown.
     */
    fun ensureSmaller(value: Int, referenceValue: Int, exceptionMessage: String?,
                      exceptionClass: Class<out RuntimeException>) {
        if (value >= referenceValue) {
            throwException(exceptionMessage, exceptionClass)
        }
    }

    /**
     * Ensures that a specific long [value] is smaller than a reference value. Otherwise an
     * [IllegalArgumentException] using a specific [exceptionMessage] is thrown.
     */
    fun ensureSmaller(value: Long, referenceValue: Long, exceptionMessage: String?) {
        ensureSmaller(value, referenceValue, exceptionMessage, IllegalArgumentException::class.java)
    }

    /**
     * Ensures that a specific long [value] is smaller than a reference value. Otherwise a runtime
     * exception of a specific [exceptionClass] and using a specific [exceptionMessage] is thrown.
     */
    fun ensureSmaller(value: Long, referenceValue: Long, exceptionMessage: String?,
                      exceptionClass: Class<out RuntimeException>) {
        if (value >= referenceValue) {
            throwException(exceptionMessage, exceptionClass)
        }
    }

    /**
     * Ensures that a specific float [value] is smaller than a reference value. Otherwise an
     * [IllegalArgumentException] using a specific [exceptionMessage] is thrown.
     */
    fun ensureSmaller(value: Float, referenceValue: Float, exceptionMessage: String?) {
        ensureSmaller(value, referenceValue, exceptionMessage, IllegalArgumentException::class.java)
    }

    /**
     * Ensures that a specific float [value] is smaller than a reference value. Otherwise a runtime
     * exception of a specific [exceptionClass] and using a specific [exceptionMessage] is thrown.
     */
    fun ensureSmaller(value: Float, referenceValue: Float, exceptionMessage: String?,
                      exceptionClass: Class<out RuntimeException>) {
        if (value >= referenceValue) {
            throwException(exceptionMessage, exceptionClass)
        }
    }

    /**
     * Ensures that a specific double [value] is smaller than a reference value. Otherwise an
     * [IllegalArgumentException] using a specific [exceptionMessage] is thrown.
     */
    fun ensureSmaller(value: Double, referenceValue: Double, exceptionMessage: String?) {
        ensureSmaller(value, referenceValue, exceptionMessage, IllegalArgumentException::class.java)
    }

    /**
     * Ensures that a specific double [value] is smaller than a reference value. Otherwise a runtime
     * exception of a specific [exceptionClass] and using a specific [exceptionMessage] is thrown.
     */
    fun ensureSmaller(value: Double, referenceValue: Double, exceptionMessage: String?,
                      exceptionClass: Class<out RuntimeException>) {
        if (value >= referenceValue) {
            throwException(exceptionMessage, exceptionClass)
        }
    }

    /**
     * Ensures that a specific [iterable] is not empty. Otherwise an [IllegalArgumentException]
     * using a specific [exceptionMessage] is thrown.
     */
    fun ensureNotEmpty(iterable: Iterable<*>, exceptionMessage: String?) {
        ensureNotEmpty(iterable, exceptionMessage, IllegalArgumentException::class.java)
    }

    /**
     * Ensures that a specific [iterable] is not empty. Otherwise a runtime exception of a specific
     * [exceptionClass] and using a specific [exceptionMessage] is thrown.
     */
    fun ensureNotEmpty(iterable: Iterable<*>, exceptionMessage: String?,
                       exceptionClass: Class<out RuntimeException>) {
        if (!iterable.iterator().hasNext()) {
            throwException(exceptionMessage, exceptionClass)
        }
    }

    /**
     * Ensures that a specific [file] exists. Otherwise an [IllegalArgumentException] using a
     * specific [exceptionMessage] is thrown.
     */
    fun ensureFileExists(file: File, exceptionMessage: String) {
        ensureFileExists(file, exceptionMessage, IllegalArgumentException::class.java)
    }

    /**
     * Ensures that a specific [file] exists. Otherwise a runtime exception of a specific
     * [exceptionClass] and using a specific [exceptionMessage] is thrown.
     */
    fun ensureFileExists(file: File, exceptionMessage: String?,
                         exceptionClass: Class<out RuntimeException>) {
        if (!file.exists()) {
            throwException(exceptionMessage, exceptionClass)
        }
    }

    /**
     * Ensures that a specific [file] is a directory. Otherwise an [IllegalArgumentException] using
     * a specific [exceptionMessage] is thrown.
     */
    fun ensureFileIsDirectory(file: File, exceptionMessage: String?) {
        ensureFileIsDirectory(file, exceptionMessage, IllegalArgumentException::class.java)
    }

    /**
     * Ensures that a specific [file] is a directory. Otherwise a runtime exception of a specific
     * [exceptionClass] and using a specific [exceptionMessage] is thrown.
     */
    fun ensureFileIsDirectory(file: File, exceptionMessage: String?,
                              exceptionClass: Class<out RuntimeException>) {
        if (!file.isDirectory) {
            throwException(exceptionMessage, exceptionClass)
        }
    }

    /**
     * Ensures that a specific [file] is not a directory. Otherwise an [IllegalArgumentException]
     * using a specific [exceptionMessage] is thrown.
     */
    fun ensureFileIsNoDirectory(file: File, exceptionMessage: String?) {
        ensureFileIsNoDirectory(file, exceptionMessage, IllegalArgumentException::class.java)
    }

    /**
     * Ensures that a specific [file] is not a directory. Otherwise a runtime exception of a
     * specific [exceptionClass] and using a specific [exceptionMessage] is thrown.
     */
    fun ensureFileIsNoDirectory(file: File, exceptionMessage: String?,
                                exceptionClass: Class<out RuntimeException>) {
        if (!file.isFile) {
            throwException(exceptionMessage, exceptionClass)
        }
    }

}

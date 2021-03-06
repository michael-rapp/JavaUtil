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

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull

/**
 * Tests the functionality of the class [IteratorUtil].
 *
 * @author Michael Rapp
 */
class IteratorUtilTest {

    @Test
    fun testCreateMappedIterable() {
        val iterable = listOf(1, 2, 3)
        val iterable2 = IteratorUtil.createMappedIterable(iterable) { item -> item.toString() }
        val iterator = iterable2.iterator()
        assertEquals("1", iterator.next())
        assertEquals("2", iterator.next())
        assertEquals("3", iterator.next())
        assertFalse { iterator.hasNext() }
    }

    @Test
    fun testCreateMappedIterator() {
        val iterator = listOf(1, 2, 3).iterator()
        val iterator2 = IteratorUtil.createMappedIterator(iterator) { item -> item.toString() }
        assertEquals("1", iterator2.next())
        assertEquals("2", iterator2.next())
        assertEquals("3", iterator2.next())
        assertFalse { iterator2.hasNext() }
    }

    @Test
    fun testCreateConcatenatedIterable() {
        val first = listOf(1, 2, 3)
        val second = listOf(4, 5, 6)
        val concatenatedIterable = IteratorUtil.createConcatenatedIterable(first, second)
        val iterator = concatenatedIterable.iterator()
        assertEquals(1, iterator.next())
        assertEquals(2, iterator.next())
        assertEquals(3, iterator.next())
        assertEquals(4, iterator.next())
        assertEquals(5, iterator.next())
        assertEquals(6, iterator.next())
        assertFalse { iterator.hasNext() }
    }

    @Test
    fun testCreateConcatenatedIterator() {
        val first = listOf(1, 2, 3).iterator()
        val second = listOf(4, 5, 6).iterator()
        val concatenatedIterator = IteratorUtil.createConcatenatedIterator(first, second)
        assertEquals(1, concatenatedIterator.next())
        assertEquals(2, concatenatedIterator.next())
        assertEquals(3, concatenatedIterator.next())
        assertEquals(4, concatenatedIterator.next())
        assertEquals(5, concatenatedIterator.next())
        assertEquals(6, concatenatedIterator.next())
        assertFalse { concatenatedIterator.hasNext() }
    }

    @Test
    fun testCreateFilteredIterable() {
        val iterable = listOf(1, 2, null, 3, 4)
        val iterable2 = IteratorUtil.createFilteredIterable(iterable) { item ->
            item == null || item % 2 == 0
        }
        val iterator = iterable2.iterator()
        assertEquals(2, iterator.next())
        assertNull(iterator.next())
        assertEquals(4, iterator.next())
        assertFalse { iterator.hasNext() }
    }

    @Test
    fun testCreateFilteredIterator() {
        val iterator = listOf(1, 2, null, 3, 4).iterator()
        val iterator2 = IteratorUtil.createFilteredIterator(iterator) { item ->
            item == null || item % 2 == 0
        }
        assertEquals(2, iterator2.next())
        assertNull(iterator2.next())
        assertEquals(4, iterator2.next())
        assertFalse { iterator2.hasNext() }
    }

    @Test
    fun testCreateNotNullIterable() {
        val iterable: Iterable<Int?> = listOf(1, 2, null, 4)
        val iterable2: Iterable<Int> = IteratorUtil.createNotNullIterable(iterable)
        val iterator = iterable2.iterator()
        assertEquals(1, iterator.next())
        assertEquals(2, iterator.next())
        assertEquals(4, iterator.next())
        assertFalse { iterator.hasNext() }
    }

    @Test
    fun testCreateNotNullIterator() {
        val iterator: Iterator<Int?> = listOf(1, 2, null, 4).iterator()
        val iterator2: Iterator<Int> = IteratorUtil.createNotNullIterator(iterator)
        assertEquals(1, iterator2.next())
        assertEquals(2, iterator2.next())
        assertEquals(4, iterator2.next())
        assertFalse { iterator2.hasNext() }
    }

    @Test
    fun testCreateNestedIterable() {
        val outerIterable = listOf(1, 2, null, 3)
        val nestedIterable = IteratorUtil.createNestedIterable(outerIterable) { item ->
            item?.let { listOf(item * 10, item * 100).iterator() } ?: listOf(item).iterator()
        }
        val iterator = nestedIterable.iterator();
        assertEquals(10, iterator.next())
        assertEquals(100, iterator.next())
        assertEquals(20, iterator.next())
        assertEquals(200, iterator.next())
        assertNull(iterator.next())
        assertEquals(30, iterator.next())
        assertEquals(300, iterator.next())
        assertFalse { iterator.hasNext() }
    }

    @Test
    fun testCreateNestedIterator() {
        val outerIterator = listOf(1, 2, null, 3).iterator()
        val nestedIterator = IteratorUtil.createNestedIterator(outerIterator) { item ->
            item?.let { listOf(item * 10, item * 100).iterator() } ?: listOf(item).iterator()
        }
        assertEquals(10, nestedIterator.next())
        assertEquals(100, nestedIterator.next())
        assertEquals(20, nestedIterator.next())
        assertEquals(200, nestedIterator.next())
        assertNull(nestedIterator.next())
        assertEquals(30, nestedIterator.next())
        assertEquals(300, nestedIterator.next())
        assertFalse { nestedIterator.hasNext() }
    }

}

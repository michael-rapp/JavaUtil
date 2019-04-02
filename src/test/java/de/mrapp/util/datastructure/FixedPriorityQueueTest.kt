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
package de.mrapp.util.datastructure

import kotlin.test.*

/**
 * Tests the functionality of the class [FixedPriorityQueue].
 *
 * @author Michael Rapp
 */
class FixedPriorityQueueTest {

    @Test
    fun testConstructor() {
        val queue = FixedPriorityQueue<Int>(3)
        assertEquals(0, queue.size)
        assertNull(queue.comparator())
    }

    @Test(expected = IllegalArgumentException::class)
    fun testConstructorThrowsExceptionIfMaxSizeIsLessThanOne() {
        FixedPriorityQueue<Int>(0)
    }

    @Test
    fun testConstructorWithComparatorArgument() {
        val comparator = Comparator<String> { o1, o2 -> o1.toInt().compareTo(o2.toInt()) }
        val queue = FixedPriorityQueue(3, comparator)
        assertEquals(0, queue.size)
        assertEquals(comparator, queue.comparator())
    }

    @Test(expected = IllegalArgumentException::class)
    fun testConstructorWithComparatorArgumentThrowsExceptionIfMaxSizeIsLessThanOne() {
        val comparator = Comparator<String> { o1, o2 -> o1.toInt().compareTo(o2.toInt()) }
        FixedPriorityQueue(0, comparator)
    }

    @Test
    fun testConstructorWithCollectionArgument() {
        val queue = FixedPriorityQueue(3, listOf(1, 2, 3, 4, 5))
        assertEquals(3, queue.size)
        assertEquals(3, queue.peek())
    }

    @Test(expected = IllegalArgumentException::class)
    fun testConstructorWithCollectionArgumentThrowsExceptionIfMaxSizeIsLessThanOne() {
        FixedPriorityQueue(0, listOf(1, 2, 3))
    }

    @Test
    fun testConstructorWithCollectionAndComparatorArgument() {
        val comparator = Comparator<String> { o1, o2 -> o1.toInt().compareTo(o2.toInt()) }
        val queue = FixedPriorityQueue(3, listOf("1", "2", "3", "4", "5"), comparator)
        assertEquals(3, queue.size)
        assertEquals("3", queue.peek())
    }

    @Test(expected = IllegalArgumentException::class)
    fun testConstructorWithCollectionAndComparatorArgumentThrowsExceptionIfMaxSizeIsLessThanOne() {
        val comparator = Comparator<String> { o1, o2 -> o1.toInt().compareTo(o2.toInt()) }
        FixedPriorityQueue(0, listOf("1", "2", "3"), comparator)
    }

    @Test
    fun testOfferUsingNaturalOrder() {
        val queue = FixedPriorityQueue<Int>(3)
        assertTrue { queue.offer(4) }
        assertEquals(4, queue.peek())
        assertEquals(1, queue.size)
        assertTrue { queue.offer(5) }
        assertEquals(2, queue.size)
        assertEquals(4, queue.peek())
        assertTrue { queue.offer(2) }
        assertEquals(3, queue.size)
        assertEquals(2, queue.peek())
        assertTrue { queue.offer(3) }
        assertEquals(3, queue.size)
        assertEquals(3, queue.peek())
        assertFalse { queue.offer(1) }
        assertEquals(3, queue.size)
        assertEquals(3, queue.peek())
    }

    @Test
    fun testOfferUsingComparator() {
        val comparator = Comparator<String> { o1, o2 -> o1.toInt().compareTo(o2.toInt()) }
        val queue = FixedPriorityQueue(3, comparator)
        assertTrue { queue.offer("4") }
        assertEquals("4", queue.peek())
        assertEquals(1, queue.size)
        assertTrue { queue.offer("5") }
        assertEquals(2, queue.size)
        assertEquals("4", queue.peek())
        assertTrue { queue.offer("2") }
        assertEquals(3, queue.size)
        assertEquals("2", queue.peek())
        assertTrue { queue.offer("3") }
        assertEquals(3, queue.size)
        assertEquals("3", queue.peek())
        assertFalse { queue.offer("1") }
        assertEquals(3, queue.size)
        assertEquals("3", queue.peek())
    }

}

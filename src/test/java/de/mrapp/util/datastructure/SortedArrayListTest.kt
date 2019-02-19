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

import org.junit.Assert.*
import org.mockito.Mockito.mock
import java.util.*
import kotlin.Comparator
import kotlin.test.Test

/**
 * Tests the functionality of the class [SortedArrayList].
 *
 * @author Michael Rapp
 */
class SortedArrayListTest {

    @Test
    fun testDefaultConstructor() {
        val sortedArrayList = SortedArrayList<Int>()
        assertTrue(sortedArrayList.isEmpty())
        assertNull(sortedArrayList.comparator())
    }

    @Test
    fun testConstructorWithCollectionParameter() {
        val collection = Arrays.asList(4, 1, 2, 3, 2)
        val sortedArrayList = SortedArrayList(collection)
        assertEquals(sortedArrayList.size.toLong(), collection.size.toLong())
        assertNull(sortedArrayList.comparator())
        val iterator = sortedArrayList.iterator()
        assertEquals(iterator.next(), 1)
        assertEquals(iterator.next(), 2)
        assertEquals(iterator.next(), 2)
        assertEquals(iterator.next(), 3)
        assertEquals(iterator.next(), 4)
        assertFalse(iterator.hasNext())
    }

    @Test
    fun testConstructorWithInitialCapacityParameter() {
        val initialCapacity = 10
        val sortedArrayList = SortedArrayList<Int>(initialCapacity)
        assertTrue(sortedArrayList.isEmpty())
        assertNull(sortedArrayList.comparator())
    }

    @Suppress("UNCHECKED_CAST")
    @Test
    fun testConstructorWithComparatorParameter() {
        val comparator = mock(Comparator::class.java) as Comparator<Int>
        val sortedArrayList = SortedArrayList(comparator)
        assertTrue(sortedArrayList.isEmpty())
        assertEquals(sortedArrayList.comparator(), comparator)
    }

    @Test
    fun testConstructorWithCollectionAndComparatorParameters() {
        val collection = Arrays.asList(4, 1, 2, 3, 2)
        val comparator = Comparator<Int> { x, y -> x.compareTo(y) }.reversed()
        val sortedArrayList = SortedArrayList(collection, comparator)
        assertEquals(sortedArrayList.size.toLong(), collection.size.toLong())
        assertEquals(sortedArrayList.comparator(), comparator)
        val iterator = sortedArrayList.iterator()
        assertEquals(iterator.next(), 4)
        assertEquals(iterator.next(), 3)
        assertEquals(iterator.next(), 2)
        assertEquals(iterator.next(), 2)
        assertEquals(iterator.next(), 1)
        assertFalse(iterator.hasNext())
    }

    @Suppress("UNCHECKED_CAST")
    @Test
    fun testConstructorWithInitialCapacityAndComparatorParameters() {
        val initialCapacity = 10
        val comparator = mock(Comparator::class.java) as Comparator<Int>
        val sortedArrayList = SortedArrayList(initialCapacity, comparator)
        assertTrue(sortedArrayList.isEmpty())
        assertEquals(sortedArrayList.comparator(), comparator)
    }

    @Test
    fun testAdd() {
        val sortedArrayList = SortedArrayList<Int>()
        sortedArrayList.add(4)
        sortedArrayList.add(1)
        sortedArrayList.add(2)
        sortedArrayList.add(3)
        sortedArrayList.add(2)
        assertEquals(sortedArrayList.size.toLong(), 5)
        val iterator = sortedArrayList.iterator()
        assertEquals(iterator.next(), 1)
        assertEquals(iterator.next(), 2)
        assertEquals(iterator.next(), 2)
        assertEquals(iterator.next(), 3)
        assertEquals(iterator.next(), 4)
    }

    @Test(expected = UnsupportedOperationException::class)
    fun testAddWithIndexParameterThrowsException() {
        SortedArrayList<Any>().add(0, 1)
    }

    @Test
    fun testAddAll() {
        val collection = Arrays.asList(4, 1, 2, 3, 2)
        val sortedArrayList = SortedArrayList<Int>()
        val result = sortedArrayList.addAll(collection)
        assertTrue(result)
        assertEquals(sortedArrayList.size.toLong(), collection.size.toLong())
        val iterator = sortedArrayList.iterator()
        assertEquals(iterator.next(), 1)
        assertEquals(iterator.next(), 2)
        assertEquals(iterator.next(), 2)
        assertEquals(iterator.next(), 3)
        assertEquals(iterator.next(), 4)
    }

    @Test
    fun testAddAllIfCollectionIsEmpty() {
        val collection = LinkedList<Int>()
        val sortedArrayList = SortedArrayList<Int>()
        val result = sortedArrayList.addAll(collection)
        assertFalse(result)
        assertTrue(sortedArrayList.isEmpty())
    }

    @Test(expected = UnsupportedOperationException::class)
    fun testAddAllWithIndexParameterThrowsException() {
        val collection = Arrays.asList(1, 2, 3)
        SortedArrayList<Any>().addAll(0, collection)
    }

    @Test
    fun testIndexOf() {
        val sortedArrayList = SortedArrayList<Int>(Arrays.asList(4, 1, 2, 3, 2))
        assertEquals(0, sortedArrayList.indexOf(1))
        assertEquals(1, sortedArrayList.indexOf(2))
        assertEquals(3, sortedArrayList.indexOf(3))
        assertEquals(4, sortedArrayList.indexOf(4))
        assertEquals(-1, sortedArrayList.indexOf(5))
    }

    @Test
    fun testLastIndexOf() {
        val sortedArrayList = SortedArrayList<Int>(Arrays.asList(4, 1, 2, 3, 2))
        assertEquals(0, sortedArrayList.lastIndexOf(1))
        assertEquals(2, sortedArrayList.lastIndexOf(2))
        assertEquals(3, sortedArrayList.lastIndexOf(3))
        assertEquals(4, sortedArrayList.lastIndexOf(4))
        assertEquals(-1, sortedArrayList.lastIndexOf(5))
    }

    @Test
    fun testContains() {
        val sortedArrayList = SortedArrayList<Int>(Arrays.asList(4, 1, 2, 3, 2))
        assertTrue(sortedArrayList.contains(1))
        assertTrue(sortedArrayList.contains(2))
        assertTrue(sortedArrayList.contains(3))
        assertTrue(sortedArrayList.contains(4))
        assertFalse(sortedArrayList.contains(5))
    }

    @Test
    fun testRemove() {
        val sortedArrayList = SortedArrayList<Int>(Arrays.asList(4, 1, 2, 3, 2))
        val result = sortedArrayList.remove(3)
        assertTrue(result)
        val iterator = sortedArrayList.iterator()
        assertEquals(iterator.next(), 1)
        assertEquals(iterator.next(), 2)
        assertEquals(iterator.next(), 2)
        assertEquals(iterator.next(), 4)
    }

    @Test
    fun testRemoveIfElementIsNotContained() {
        val sortedArrayList = SortedArrayList<Int>(Arrays.asList(4, 1, 2, 3, 2))
        val result = sortedArrayList.remove(5)
        assertFalse(result)
        val iterator = sortedArrayList.iterator()
        assertEquals(iterator.next(), 1)
        assertEquals(iterator.next(), 2)
        assertEquals(iterator.next(), 2)
        assertEquals(iterator.next(), 3)
        assertEquals(iterator.next(), 4)
    }

    @Test
    fun testSort() {
        val sortedArrayList = SortedArrayList<Int>(Arrays.asList(5, 1, 2, 3, 2))
        val comparator = Comparator<Int> { x, y -> x.compareTo(y) }.reversed()
        sortedArrayList.sortWith(comparator)
        sortedArrayList.add(4)
        val iterator = sortedArrayList.iterator()
        assertEquals(iterator.next(), 5)
        assertEquals(iterator.next(), 4)
        assertEquals(iterator.next(), 3)
        assertEquals(iterator.next(), 2)
        assertEquals(iterator.next(), 2)
        assertEquals(iterator.next(), 1)
    }

}
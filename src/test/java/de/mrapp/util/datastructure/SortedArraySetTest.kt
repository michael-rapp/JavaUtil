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
package de.mrapp.util.datastructure

import org.junit.Assert.*
import java.util.*
import kotlin.test.Test

/**
 * Tests the functionality of the class [SortedArraySet].
 *
 * @author Michael Rapp
 */
class SortedArraySetTest {

    @Test
    fun testDefaultConstructor() {
        val sortedArraySet = SortedArraySet<Int>()
        assertTrue(sortedArraySet.isEmpty())
        assertNull(sortedArraySet.comparator())
    }

    @Test
    fun testConstructorWithCollectionParameter() {
        val collection = Arrays.asList(1, 2, 3, 4, 5)
        val sortedArraySet = SortedArraySet(collection)
        assertTrue(sortedArraySet.containsAll(collection))
        assertNull(sortedArraySet.comparator())
    }

    @Test
    fun testConstructorWithInitialCapacityParameter() {
        val initialCapacity = 10
        val sortedArraySet = SortedArraySet<Int>(initialCapacity)
        assertTrue(sortedArraySet.isEmpty())
        assertNull(sortedArraySet.comparator())
    }

    @Test
    fun testConstructorWithComparatorParameter() {
        val comparator = Comparator<Int> { x, y -> x.compareTo(y) }.reversed()
        val sortedArraySet = SortedArraySet(comparator)
        assertTrue(sortedArraySet.isEmpty())
        assertEquals(comparator, sortedArraySet.comparator())
    }

    @Test
    fun testConstructorWithCollectionAndComparatorParameters() {
        val collection = Arrays.asList(2, 3, 5, 2, 4)
        val comparator = Comparator<Int> { x, y -> x.compareTo(y) }.reversed()
        val sortedArraySet = SortedArraySet(collection, comparator)
        val iterator = sortedArraySet.iterator()
        assertEquals(5, iterator.next())
        assertEquals(4, iterator.next())
        assertEquals(3, iterator.next())
        assertEquals(2, iterator.next())
        assertFalse(iterator.hasNext())
        assertEquals(comparator, sortedArraySet.comparator())
    }

    @Test
    fun testConstructorWithInitialCapacityAndComparatorParameters() {
        val initialCapacity = 10
        val comparator = Comparator<Int> { x, y -> x.compareTo(y) }.reversed()
        val sortedArraySet = SortedArraySet(initialCapacity, comparator)
        assertTrue(sortedArraySet.isEmpty())
        assertEquals(comparator, sortedArraySet.comparator())
    }

    @Test
    fun testSubSet() {
        val collection = Arrays.asList(1, 2, 3, 4, 5)
        val sortedArraySet = SortedArraySet(collection)
        val subSet = sortedArraySet.subSet(2, 4)
        assertEquals(3, subSet.size)
        val iterator = subSet.iterator()
        assertEquals(2, iterator.next())
        assertEquals(3, iterator.next())
        assertEquals(4, iterator.next())
    }

    @Test(expected = NoSuchElementException::class)
    fun testSubSetThrowsExceptionIfFromElementIsNotContained() {
        val collection = Arrays.asList(1, 2)
        val sortedArraySet = SortedArraySet(collection)
        sortedArraySet.subSet(0, 2)
    }

    @Test(expected = NoSuchElementException::class)
    fun testSubSetThrowsExceptionIfToElementIsNotContained() {
        val collection = Arrays.asList(1, 2)
        val sortedArraySet = SortedArraySet(collection)
        sortedArraySet.subSet(1, 3)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testSubSetThrowsExceptionIfFromElementIsGreaterThanToElement() {
        val collection = Arrays.asList(1, 2)
        val sortedArraySet = SortedArraySet(collection)
        sortedArraySet.subSet(2, 1)
    }

    @Test
    fun testHeadSet() {
        val collection = Arrays.asList(1, 2, 3, 4, 5)
        val sortedArraySet = SortedArraySet(collection)
        val headSet = sortedArraySet.headSet(3)
        assertEquals(3, headSet.size)
        val iterator = headSet.iterator()
        assertEquals(1, iterator.next())
        assertEquals(2, iterator.next())
        assertEquals(3, iterator.next())
    }

    @Test(expected = NoSuchElementException::class)
    fun testHeadSetThrowsExceptionIfToElementIsNotContained() {
        SortedArraySet<Any>().headSet(1)
    }

    @Test
    fun testTailSet() {
        val collection = Arrays.asList(1, 2, 3, 4, 5)
        val sortedArraySet = SortedArraySet(collection)
        val tailSet = sortedArraySet.tailSet(3)
        assertEquals(3, tailSet.size)
        val iterator = tailSet.iterator()
        assertEquals(3, iterator.next())
        assertEquals(4, iterator.next())
        assertEquals(5, iterator.next())
    }

    @Test(expected = NoSuchElementException::class)
    fun testTailSetThrowsExceptionIfFromElementIsNotContained() {
        SortedArraySet<Any>().tailSet(1)
    }

    @Test
    fun testFirst() {
        val collection = Arrays.asList(1, 2, 3)
        val sortedArraySet = SortedArraySet(collection)
        val first = sortedArraySet.first()
        assertEquals(1, first)
    }

    @Test(expected = NoSuchElementException::class)
    fun testFirstThrowsExceptionIfEmpty() {
        SortedArraySet<Any>().first()
    }

    @Test
    fun testLast() {
        val collection = Arrays.asList(1, 2, 3)
        val sortedArraySet = SortedArraySet(collection)
        val last = sortedArraySet.last()
        assertEquals(3, last)
    }

    @Test(expected = NoSuchElementException::class)
    fun testLastThrowsExceptionIfEmpty() {
        SortedArraySet<Any>().last()
    }

    @Test
    fun testSize() {
        val collection = Arrays.asList(1, 2, 3)
        val sortedArraySet = SortedArraySet(collection)
        assertEquals(collection.size, sortedArraySet.size)
    }

    @Test
    fun testIsEmpty() {
        val sortedArraySet = SortedArraySet<Int>()
        assertTrue(sortedArraySet.isEmpty())
        sortedArraySet.add(1)
        assertFalse(sortedArraySet.isEmpty())
    }

    @Test
    fun testContains() {
        val sortedArraySet = SortedArraySet<Int>()
        sortedArraySet.add(1)
        assertFalse(sortedArraySet.contains(2))
        sortedArraySet.add(2)
        assertTrue(sortedArraySet.contains(2))
    }

    @Test
    fun testIterator() {
        val collection = Arrays.asList(2, 3, 1, 4, 2)
        val sortedArraySet = SortedArraySet(collection)
        val iterator = sortedArraySet.iterator()
        assertEquals(1, iterator.next())
        assertEquals(2, iterator.next())
        assertEquals(3, iterator.next())
        assertEquals(4, iterator.next())
        assertFalse(iterator.hasNext())
    }

    @Test
    fun testToArray() {
        val collection = Arrays.asList(2, 3, 1, 4, 2)
        val sortedArraySet = SortedArraySet(collection)
        val array = sortedArraySet.toTypedArray()
        assertEquals(4, array.size)
        assertEquals(1, array[0])
        assertEquals(2, array[1])
        assertEquals(3, array[2])
        assertEquals(4, array[3])
    }

    @Test
    fun testAdd() {
        val sortedArraySet = SortedArraySet<Int>()
        var result = sortedArraySet.add(3)
        assertTrue(result)
        assertEquals(1, sortedArraySet.size)
        result = sortedArraySet.add(3)
        assertFalse(result)
        assertEquals(1, sortedArraySet.size)
        sortedArraySet.add(4)
        sortedArraySet.add(2)
        sortedArraySet.add(1)
        sortedArraySet.add(5)
        assertEquals(5, sortedArraySet.size)
        val iterator = sortedArraySet.iterator()
        assertEquals(1, iterator.next())
        assertEquals(2, iterator.next())
        assertEquals(3, iterator.next())
        assertEquals(4, iterator.next())
        assertEquals(5, iterator.next())
        assertFalse(iterator.hasNext())
    }

    @Test
    fun testRemove() {
        val sortedArraySet = SortedArraySet<Int>()
        sortedArraySet.add(1)
        sortedArraySet.add(2)
        assertEquals(2, sortedArraySet.size)
        var result = sortedArraySet.remove(1)
        assertTrue(result)
        assertEquals(1, sortedArraySet.size)
        result = sortedArraySet.remove(1)
        assertFalse(result)
        assertEquals(1, sortedArraySet.size)
        assertEquals(2, sortedArraySet.first())
    }

    @Test(expected = IllegalArgumentException::class)
    fun testRemoveThrowsExceptionIfItemIsNull() {
        SortedArraySet<Any>().remove(null)
    }

    @Test
    fun testContainsAll() {
        val collection = Arrays.asList(3, 2, 5, 1)
        val sortedArraySet = SortedArraySet(collection)
        assertTrue(sortedArraySet.containsAll(collection))
        sortedArraySet.remove(3)
        assertFalse(sortedArraySet.containsAll(collection))
    }

    @Test
    fun testAddAll() {
        val collection = Arrays.asList(3, 2, 4, 1, 5)
        val sortedArraySet = SortedArraySet<Int>()
        assertTrue(sortedArraySet.isEmpty())
        var result = sortedArraySet.addAll(collection)
        assertTrue(result)
        assertTrue(sortedArraySet.containsAll(collection))
        val iterator = sortedArraySet.iterator()
        assertEquals(1, iterator.next())
        assertEquals(2, iterator.next())
        assertEquals(3, iterator.next())
        assertEquals(4, iterator.next())
        assertEquals(5, iterator.next())
        assertFalse(iterator.hasNext())
        val collection2 = Arrays.asList(5, 6)
        result = sortedArraySet.addAll(collection2)
        assertFalse(result)
        assertTrue(sortedArraySet.containsAll(collection2))
    }

    @Test
    fun testAddAllIfCollectionIfEmpty() {
        val collection = LinkedList<Int>()
        val sortedArraySet = SortedArraySet<Int>()
        assertTrue(sortedArraySet.isEmpty())
        val result = sortedArraySet.addAll(collection)
        assertFalse(result)
        assertTrue(sortedArraySet.isEmpty())
    }

    @Test
    fun testRetainAll() {
        val collection = Arrays.asList(3, 2, 5, 1)
        val collection2 = Arrays.asList(5, 6)
        val sortedArraySet = SortedArraySet<Int>()
        sortedArraySet.addAll(collection)
        sortedArraySet.addAll(collection2)
        assertTrue(sortedArraySet.containsAll(collection))
        assertTrue(sortedArraySet.containsAll(collection2))
        var result = sortedArraySet.retainAll(collection)
        assertTrue(result)
        assertTrue(sortedArraySet.containsAll(collection))
        assertFalse(sortedArraySet.containsAll(collection2))
        result = sortedArraySet.retainAll(collection)
        assertFalse(result)
        assertTrue(sortedArraySet.containsAll(collection))
        assertFalse(sortedArraySet.containsAll(collection2))
    }

    @Test
    fun testRetainAllIfCollectionIsEmpty() {
        val collection = Arrays.asList(3, 2, 5, 1)
        val collection2 = LinkedList<Int>()
        val sortedArraySet = SortedArraySet(collection)
        assertTrue(sortedArraySet.containsAll(collection))
        val result = sortedArraySet.retainAll(collection2)
        assertFalse(result)
        assertTrue(sortedArraySet.containsAll(collection))
    }

    @Test
    fun testRemoveAll() {
        val collection = Arrays.asList(3, 2, 5, 1)
        val sortedArraySet = SortedArraySet(collection)
        assertTrue(sortedArraySet.containsAll(collection))
        var result = sortedArraySet.removeAll(collection)
        assertTrue(result)
        assertTrue(sortedArraySet.isEmpty())
        result = sortedArraySet.removeAll(collection)
        assertFalse(result)
    }

    @Test
    fun testRemoveAllIfCollectionIsEmpty() {
        val collection = Arrays.asList(3, 2, 5, 1)
        val sortedArraySet = SortedArraySet(collection)
        assertTrue(sortedArraySet.containsAll(collection))
        val result = sortedArraySet.removeAll(LinkedList<Any>())
        assertFalse(result)
        assertTrue(sortedArraySet.containsAll(collection))
    }

    @Test
    fun testClear() {
        val collection = Arrays.asList(3, 2, 5, 1)
        val sortedArraySet = SortedArraySet(collection)
        assertFalse(sortedArraySet.isEmpty())
        sortedArraySet.clear()
        assertTrue(sortedArraySet.isEmpty())
    }

    @Test
    fun testToString() {
        val collection = Arrays.asList(1, 2, 3)
        val sortedArraySet = SortedArraySet(collection)
        assertEquals("[1, 2, 3]", sortedArraySet.toString())
    }

    @Test
    fun testHashCode() {
        val sortedArraySet1 = SortedArraySet<Int>()
        val sortedArraySet2 = SortedArraySet<Int>()
        assertEquals(sortedArraySet1.hashCode(), sortedArraySet1.hashCode())
        assertEquals(sortedArraySet1.hashCode(), sortedArraySet2.hashCode())
        sortedArraySet2.add(1)
        assertNotEquals(sortedArraySet1.hashCode(), sortedArraySet2.hashCode())
        sortedArraySet1.add(1)
        assertEquals(sortedArraySet1.hashCode(), sortedArraySet2.hashCode())
    }

    @Test
    fun testEquals() {
        val sortedArraySet1 = SortedArraySet<Int>()
        val sortedArraySet2 = SortedArraySet<Int>()
        assertFalse(sortedArraySet1 == null)
        assertFalse(sortedArraySet1 == Any())
        assertTrue(sortedArraySet1 == sortedArraySet1)
        assertTrue(sortedArraySet1 == sortedArraySet2)
        sortedArraySet2.add(1)
        assertFalse(sortedArraySet1 == sortedArraySet2)
        sortedArraySet1.add(1)
        assertTrue(sortedArraySet1 == sortedArraySet2)
    }

}
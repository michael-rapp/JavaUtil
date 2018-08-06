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

import de.mrapp.util.datastructure.ListenerList.CompareMethod
import org.junit.Assert.*
import java.util.*
import kotlin.test.Test

/**
 * Tests the functionality of the class [ListenerList].
 *
 * @author Michael Rapp
 */
class ListenerListTest {

    private data class TestType internal constructor(private val value: Int)

    @Test
    fun testDefaultConstructor() {
        val list = ListenerList<TestType>()
        assertEquals(CompareMethod.EQUALITY, list.compareMethod)
        assertTrue(list.isEmpty())
        assertEquals(0, list.size())
    }

    @Test
    fun testConstructorWithCompareMethodParameter() {
        val compareMethod = CompareMethod.IDENTITY
        val list = ListenerList<TestType>(compareMethod)
        assertEquals(compareMethod, list.compareMethod)
        assertTrue(list.isEmpty())
        assertEquals(0, list.size())
    }

    @Test
    fun testAdd() {
        val listener = TestType(1)
        val list = ListenerList<TestType>()
        val added = list.add(listener)
        assertTrue(added)
        assertEquals(1, list.size())
        assertFalse(list.isEmpty())
    }

    @Test
    fun testAddDuplicateByEquality() {
        val listener = TestType(1)
        val listener2 = TestType(1)
        val listener3 = TestType(2)
        val list = ListenerList<TestType>()
        list.add(listener)
        var added = list.add(listener2)
        assertFalse(added)
        assertEquals(1, list.size())
        assertFalse(list.isEmpty())
        added = list.add(listener3)
        assertTrue(added)
        assertEquals(2, list.size())
        assertFalse(list.isEmpty())
    }

    @Test
    fun testAddDuplicateByIdentity() {
        val listener = TestType(1)
        val listener2 = TestType(1)
        val list = ListenerList<TestType>(CompareMethod.IDENTITY)
        list.add(listener)
        var added = list.add(listener)
        assertFalse(added)
        assertEquals(1, list.size())
        assertFalse(list.isEmpty())
        added = list.add(listener2)
        assertTrue(added)
        assertEquals(2, list.size())
        assertFalse(list.isEmpty())
    }

    @Test
    fun testNoConcurrentModificationExceptionIsThrownWhenAddingListenerWhileIterating() {
        val listener1 = TestType(1)
        val listener2 = TestType(2)
        val list = ListenerList<TestType>()
        list.add(listener1)
        list.add(listener2)
        assertEquals(2, list.size())
        val iterator = list.iterator()
        assertTrue(iterator.hasNext())
        assertEquals(listener1, iterator.next())
        assertTrue(iterator.hasNext())
        list.add(TestType(3))
        assertEquals(listener2, iterator.next())
        assertFalse(iterator.hasNext())
    }

    @Test
    fun testAddAll() {
        val listener1 = TestType(1)
        val listener2 = TestType(2)
        val listener3 = TestType(3)
        val list = ListenerList<TestType>()
        list.add(listener1)
        list.add(listener2)
        assertEquals(2, list.size())
        assertFalse(list.isEmpty())
        val collection = LinkedList<TestType>()
        collection.add(listener2)
        collection.add(listener3)
        collection.add(listener3)
        list.addAll(collection)
        assertEquals(3, list.size())
        assertFalse(list.isEmpty())
    }

    @Test
    fun testRemoveByEquality() {
        val listener = TestType(1)
        val list = ListenerList<TestType>()
        list.add(listener)
        assertEquals(1, list.size())
        assertFalse(list.isEmpty())
        var removed = list.remove(TestType(2))
        assertFalse(removed)
        assertEquals(1, list.size())
        assertFalse(list.isEmpty())
        removed = list.remove(TestType(1))
        assertTrue(removed)
        assertEquals(0, list.size())
        assertTrue(list.isEmpty())
    }

    @Test
    fun testRemoveByIdentity() {
        val listener = TestType(1)
        val list = ListenerList<TestType>(CompareMethod.IDENTITY)
        list.add(listener)
        assertEquals(1, list.size())
        assertFalse(list.isEmpty())
        var removed = list.remove(TestType(1))
        assertFalse(removed)
        assertEquals(1, list.size())
        assertFalse(list.isEmpty())
        removed = list.remove(listener)
        assertTrue(removed)
        assertEquals(0, list.size())
        assertTrue(list.isEmpty())
    }

    @Test
    fun testRemoveAll() {
        val listener1 = TestType(1)
        val listener2 = TestType(2)
        val listener3 = TestType(3)
        val list = ListenerList<TestType>()
        list.add(listener1)
        list.add(listener2)
        list.add(listener3)
        assertEquals(3, list.size())
        assertFalse(list.isEmpty())
        val collection = LinkedList<TestType>()
        collection.add(listener1)
        collection.add(listener2)
        collection.add(listener2)
        collection.add(TestType(4))
        list.removeAll(collection)
        assertEquals(1, list.size())
        assertFalse(list.isEmpty())
    }

    @Test
    fun clear() {
        val list = ListenerList<TestType>()
        list.add(TestType(1))
        list.add(TestType(2))
        assertEquals(2, list.size())
        assertFalse(list.isEmpty())
        list.clear()
        assertEquals(0, list.size())
        assertTrue(list.isEmpty())
    }

    @Test
    fun testGetAll() {
        val listener1 = TestType(1)
        val listener2 = TestType(2)
        val list = ListenerList<TestType>()
        list.add(listener1)
        list.add(listener2)
        val all = list.getAll()
        assertEquals(list.size(), all.size)
        val iterator = all.iterator()
        assertEquals(listener1, iterator.next())
        assertEquals(listener2, iterator.next())
    }

    @Test
    fun testGellAllReturnedCollectionDoesNotReflectChanges() {
        val list = ListenerList<TestType>()
        list.add(TestType(1))
        list.add(TestType(2))
        val all = list.getAll()
        list.add(TestType(3))
        assertEquals(3, list.size())
        assertEquals(2, all.size)
    }

    @Test
    fun testIterator() {
        val listener1 = TestType(1)
        val listener2 = TestType(2)
        val list = ListenerList<TestType>()
        list.add(listener1)
        list.add(listener2)
        assertEquals(2, list.size())
        val iterator = list.iterator()
        assertTrue(iterator.hasNext())
        assertEquals(listener1, iterator.next())
        assertTrue(iterator.hasNext())
        assertEquals(listener2, iterator.next())
        assertFalse(iterator.hasNext())
    }

}
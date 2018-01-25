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
package de.mrapp.util.datastructure;

import de.mrapp.util.datastructure.ListenerList.CompareMethod;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * Tests the functionality of the class {@link ListenerList}.
 *
 * @author Michael Rapp
 */
public class ListenerListTest {

    private static class TestType {

        private final int value;

        TestType(final int value) {
            this.value = value;
        }

        @Override
        public int hashCode() {
            return value;
        }

        @Override
        public boolean equals(final Object obj) {
            if (obj == this)
                return true;
            if (obj == null)
                return false;
            if (obj.getClass() != getClass())
                return false;
            TestType other = (TestType) obj;
            return value == other.value;
        }

    }

    @Test
    public void testDefaultConstructor() {
        ListenerList<TestType> list = new ListenerList<>();
        assertEquals(CompareMethod.EQUALITY, list.getCompareMethod());
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    public void testConstructorWithCompareMethodParameter() {
        CompareMethod compareMethod = CompareMethod.IDENTITY;
        ListenerList<TestType> list = new ListenerList<>(compareMethod);
        assertEquals(compareMethod, list.getCompareMethod());
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    public void testAdd() {
        TestType listener = new TestType(1);
        ListenerList<TestType> list = new ListenerList<>();
        boolean added = list.add(listener);
        assertTrue(added);
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
    }

    @Test
    public void testAddDuplicateByEquality() {
        TestType listener = new TestType(1);
        TestType listener2 = new TestType(1);
        TestType listener3 = new TestType(2);
        ListenerList<TestType> list = new ListenerList<>();
        list.add(listener);
        boolean added = list.add(listener2);
        assertFalse(added);
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        added = list.add(listener3);
        assertTrue(added);
        assertEquals(2, list.size());
        assertFalse(list.isEmpty());
    }

    @Test
    public void testAddDuplicateByIdentity() {
        TestType listener = new TestType(1);
        TestType listener2 = new TestType(1);
        ListenerList<TestType> list = new ListenerList<>(CompareMethod.IDENTITY);
        list.add(listener);
        boolean added = list.add(listener);
        assertFalse(added);
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        added = list.add(listener2);
        assertTrue(added);
        assertEquals(2, list.size());
        assertFalse(list.isEmpty());
    }

    @Test
    public void testNoConcurrentModificationExceptionIsThrownWhenAddingListenerWhileIterating() {
        TestType listener1 = new TestType(1);
        TestType listener2 = new TestType(2);
        ListenerList<TestType> list = new ListenerList<>();
        list.add(listener1);
        list.add(listener2);
        assertEquals(2, list.size());
        Iterator<TestType> iterator = list.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(listener1, iterator.next());
        assertTrue(iterator.hasNext());
        list.add(new TestType(3));
        assertEquals(listener2, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddThrowsExceptionIfListenerIsNull() {
        new ListenerList<TestType>().add(null);
    }

    @Test
    public void testAddAll() {
        TestType listener1 = new TestType(1);
        TestType listener2 = new TestType(2);
        TestType listener3 = new TestType(3);
        ListenerList<TestType> list = new ListenerList<>();
        list.add(listener1);
        list.add(listener2);
        assertEquals(2, list.size());
        assertFalse(list.isEmpty());
        Collection<TestType> collection = new LinkedList<>();
        collection.add(listener2);
        collection.add(listener3);
        collection.add(listener3);
        list.addAll(collection);
        assertEquals(3, list.size());
        assertFalse(list.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddAllThrowsExceptionIfIterableIsNull() {
        new ListenerList<TestType>().addAll(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddAllThrowsExceptionIfIterableContainsNullElement() {
        new ListenerList<TestType>().addAll(Collections.singletonList(null));
    }

    @Test
    public void testRemoveByEquality() {
        TestType listener = new TestType(1);
        ListenerList<TestType> list = new ListenerList<>();
        list.add(listener);
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        boolean removed = list.remove(new TestType(2));
        assertFalse(removed);
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        removed = list.remove(new TestType(1));
        assertTrue(removed);
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @Test
    public void testRemoveByIdentity() {
        TestType listener = new TestType(1);
        ListenerList<TestType> list = new ListenerList<>(CompareMethod.IDENTITY);
        list.add(listener);
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        boolean removed = list.remove(new TestType(1));
        assertFalse(removed);
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        removed = list.remove(listener);
        assertTrue(removed);
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveThrowsExceptionIfListenerIsNull() {
        new ListenerList<TestType>().remove(null);
    }

    @Test
    public void testRemoveAll() {
        TestType listener1 = new TestType(1);
        TestType listener2 = new TestType(2);
        TestType listener3 = new TestType(3);
        ListenerList<TestType> list = new ListenerList<>();
        list.add(listener1);
        list.add(listener2);
        list.add(listener3);
        assertEquals(3, list.size());
        assertFalse(list.isEmpty());
        Collection<TestType> collection = new LinkedList<>();
        collection.add(listener1);
        collection.add(null);
        collection.add(listener2);
        collection.add(listener2);
        collection.add(new TestType(4));
        list.removeAll(collection);
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveAllThrowsExceptionIfIterableIsNull() {
        new ListenerList<TestType>().removeAll(null);
    }

    @Test
    public void clear() {
        ListenerList<TestType> list = new ListenerList<>();
        list.add(new TestType(1));
        list.add(new TestType(2));
        assertEquals(2, list.size());
        assertFalse(list.isEmpty());
        list.clear();
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @Test
    public void testGetAll() {
        TestType listener1 = new TestType(1);
        TestType listener2 = new TestType(2);
        ListenerList<TestType> list = new ListenerList<>();
        list.add(listener1);
        list.add(listener2);
        Collection<TestType> all = list.getAll();
        assertEquals(list.size(), all.size());
        Iterator<TestType> iterator = all.iterator();
        assertEquals(listener1, iterator.next());
        assertEquals(listener2, iterator.next());
    }

    @Test
    public final void testGellAllReturnedCollectionDoesNotReflectChanges() {
        ListenerList<TestType> list = new ListenerList<>();
        list.add(new TestType(1));
        list.add(new TestType(2));
        Collection<TestType> all = list.getAll();
        list.add(new TestType(3));
        assertEquals(3, list.size());
        assertEquals(2, all.size());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetAllReturnedCollectionIsUnmodifiable() {
        ListenerList<TestType> list = new ListenerList<>();
        list.add(new TestType(1));
        list.add(new TestType(2));
        list.getAll().add(new TestType(3));
    }

    @Test
    public void testIterator() {
        TestType listener1 = new TestType(1);
        TestType listener2 = new TestType(2);
        ListenerList<TestType> list = new ListenerList<>();
        list.add(listener1);
        list.add(listener2);
        assertEquals(2, list.size());
        Iterator<TestType> iterator = list.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(listener1, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(listener2, iterator.next());
        assertFalse(iterator.hasNext());
    }

}
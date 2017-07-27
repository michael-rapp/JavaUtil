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

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * Tests the functionality of the class {@link SortedArrayList}.
 *
 * @author Michael Rapp
 */
public class SortedArrayListTest {

    @Test
    public final void testDefaultConstructor() {
        SortedArrayList<Integer> sortedArrayList = new SortedArrayList<>();
        assertTrue(sortedArrayList.isEmpty());
        assertNull(sortedArrayList.comparator());
    }

    @Test
    public final void testConstructorWithCollectionParameter() {
        Collection<Integer> collection = Arrays.asList(4, 1, 2, 3, 2);
        SortedArrayList<Integer> sortedArrayList = new SortedArrayList<>(collection);
        assertEquals(sortedArrayList.size(), collection.size());
        assertNull(sortedArrayList.comparator());
        Iterator<Integer> iterator = sortedArrayList.iterator();
        assertEquals(iterator.next(), (Integer) 1);
        assertEquals(iterator.next(), (Integer) 2);
        assertEquals(iterator.next(), (Integer) 2);
        assertEquals(iterator.next(), (Integer) 3);
        assertEquals(iterator.next(), (Integer) 4);
        assertFalse(iterator.hasNext());
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testConstructorWithCollectionParameterThrowsExceptionIfCollectionIsNull() {
        new SortedArrayList<>((Collection) null);
    }

    @Test
    public final void testConstructorWithInitialCapacityParameter() {
        int initialCapacity = 10;
        SortedArrayList<Integer> sortedArrayList = new SortedArrayList<>(initialCapacity);
        assertTrue(sortedArrayList.isEmpty());
        assertNull(sortedArrayList.comparator());
    }

    @Test
    public final void testConstructorWithComparatorParameter() {
        Comparator<Integer> comparator = mock(Comparator.class);
        SortedArrayList<Integer> sortedArrayList = new SortedArrayList<>(comparator);
        assertTrue(sortedArrayList.isEmpty());
        assertEquals(sortedArrayList.comparator(), comparator);
    }

    @Test
    public final void testConstructorWithCollectionAndComparatorParameters() {
        Collection<Integer> collection = Arrays.asList(4, 1, 2, 3, 2);
        Comparator<Integer> comparator = Comparator.reverseOrder();
        SortedArrayList<Integer> sortedArrayList = new SortedArrayList<>(collection, comparator);
        assertEquals(sortedArrayList.size(), collection.size());
        assertEquals(sortedArrayList.comparator(), comparator);
        Iterator<Integer> iterator = sortedArrayList.iterator();
        assertEquals(iterator.next(), (Integer) 4);
        assertEquals(iterator.next(), (Integer) 3);
        assertEquals(iterator.next(), (Integer) 2);
        assertEquals(iterator.next(), (Integer) 2);
        assertEquals(iterator.next(), (Integer) 1);
        assertFalse(iterator.hasNext());
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testConstructorWithCollectionAndComparatorParametersThrowsExceptionIfCollectionIsNull() {
        new SortedArrayList<>(null, mock(Comparator.class));
    }

    @Test
    public final void testConstructorWithInitialCapacityAndComparatorParameters() {
        int initialCapacity = 10;
        Comparator<Integer> comparator = mock(Comparator.class);
        SortedArrayList<Integer> sortedArrayList = new SortedArrayList<>(initialCapacity,
                comparator);
        assertTrue(sortedArrayList.isEmpty());
        assertEquals(sortedArrayList.comparator(), comparator);
    }

    @Test
    public final void testAdd() {
        SortedArrayList<Integer> sortedArrayList = new SortedArrayList<>();
        sortedArrayList.add(4);
        sortedArrayList.add(1);
        sortedArrayList.add(2);
        sortedArrayList.add(3);
        sortedArrayList.add(2);
        assertEquals(sortedArrayList.size(), 5);
        Iterator<Integer> iterator = sortedArrayList.iterator();
        assertEquals(iterator.next(), (Integer) 1);
        assertEquals(iterator.next(), (Integer) 2);
        assertEquals(iterator.next(), (Integer) 2);
        assertEquals(iterator.next(), (Integer) 3);
        assertEquals(iterator.next(), (Integer) 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testAddThrowsExceptionIfItemIsNull() {
        new SortedArrayList<>().add(null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public final void testAddWithIndexParameterThrowsException() {
        new SortedArrayList<>().add(0, 1);
    }

    @Test
    public final void testAddAll() {
        Collection<Integer> collection = Arrays.asList(4, 1, 2, 3, 2);
        SortedArrayList<Integer> sortedArrayList = new SortedArrayList<>();
        boolean result = sortedArrayList.addAll(collection);
        assertTrue(result);
        assertEquals(sortedArrayList.size(), collection.size());
        Iterator<Integer> iterator = sortedArrayList.iterator();
        assertEquals(iterator.next(), (Integer) 1);
        assertEquals(iterator.next(), (Integer) 2);
        assertEquals(iterator.next(), (Integer) 2);
        assertEquals(iterator.next(), (Integer) 3);
        assertEquals(iterator.next(), (Integer) 4);
    }

    @Test
    public final void testAddAllIfCollectionIsEmpty() {
        Collection<Integer> collection = new LinkedList<>();
        SortedArrayList<Integer> sortedArrayList = new SortedArrayList<>();
        boolean result = sortedArrayList.addAll(collection);
        assertFalse(result);
        assertTrue(sortedArrayList.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testAddAllThrowsExceptionIfCollectionIsNull() {
        new SortedArrayList<>().addAll(null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public final void testAddAllWithIndexParameterThrowsException() {
        Collection<Integer> collection = Arrays.asList(1, 2, 3);
        new SortedArrayList<>().addAll(0, collection);
    }

}
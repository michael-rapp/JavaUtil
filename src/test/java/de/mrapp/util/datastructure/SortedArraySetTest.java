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

/**
 * Tests the functionality of the class {@link SortedArraySet}.
 *
 * @author Michael Rapp
 */
public class SortedArraySetTest {

    // TODO: test constructors

    @Test
    public final void testDefaultConstructor() {
        SortedArraySet<Integer> sortedArraySet = new SortedArraySet<>();
        assertTrue(sortedArraySet.isEmpty());
        assertNull(sortedArraySet.comparator());
    }

    @Test
    public final void testConstructorWithCollectionParameter() {
        Collection<Integer> collection = Arrays.asList(1, 2, 3, 4, 5);
        SortedArraySet<Integer> sortedArraySet = new SortedArraySet<>(collection);
        assertTrue(sortedArraySet.containsAll(collection));
        assertNull(sortedArraySet.comparator());
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testConstructorWithCollectionParameterThrowsException() {
        new SortedArraySet<Integer>((Collection) null);
    }

    @Test
    public final void testConstructorWithInitialCapacityParameter() {
        int initialCapacity = 10;
        SortedArraySet<Integer> sortedArraySet = new SortedArraySet<>(initialCapacity);
        assertTrue(sortedArraySet.isEmpty());
        assertNull(sortedArraySet.comparator());
    }

    @Test
    public final void testConstructorWithComparatorParameter() {
        Comparator<Integer> comparator = Comparator.reverseOrder();
        SortedArraySet<Integer> sortedArraySet = new SortedArraySet<>(comparator);
        assertTrue(sortedArraySet.isEmpty());
        assertEquals(comparator, sortedArraySet.comparator());
    }

    @Test
    public final void testConstructorWithCollectionAndComparatorParameters() {
        Collection<Integer> collection = Arrays.asList(2, 3, 5, 2, 4);
        Comparator<Integer> comparator = Comparator.reverseOrder();
        SortedArraySet<Integer> sortedArraySet = new SortedArraySet<>(collection, comparator);
        Iterator<Integer> iterator = sortedArraySet.iterator();
        assertEquals((Integer) 5, iterator.next());
        assertEquals((Integer) 4, iterator.next());
        assertEquals((Integer) 3, iterator.next());
        assertEquals((Integer) 2, iterator.next());
        assertFalse(iterator.hasNext());
        assertEquals(comparator, sortedArraySet.comparator());
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testConstructorWithCollectionAndComparatorParameterThrowsExceptionIfCollectionIsNull() {
        new SortedArraySet<>(null, Comparator.reverseOrder());
    }

    @Test
    public final void testConstructorWithInitialCapacityAndComparatorParameters() {
        int initialCapacity = 10;
        Comparator<Integer> comparator = Comparator.reverseOrder();
        SortedArraySet<Integer> sortedArraySet = new SortedArraySet<>(initialCapacity, comparator);
        assertTrue(sortedArraySet.isEmpty());
        assertEquals(comparator, sortedArraySet.comparator());
    }

    @Test
    public final void testSubSet() {
        Collection<Integer> collection = Arrays.asList(1, 2, 3, 4, 5);
        SortedArraySet<Integer> sortedArraySet = new SortedArraySet<>(collection);
        SortedSet<Integer> subSet = sortedArraySet.subSet(2, 4);
        assertEquals(3, subSet.size());
        Iterator<Integer> iterator = subSet.iterator();
        assertEquals((Integer) 2, iterator.next());
        assertEquals((Integer) 3, iterator.next());
        assertEquals((Integer) 4, iterator.next());
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testSubSetThrowsExceptionIfFromElementIsNull() {
        new SortedArraySet<>().subSet(null, 1);
    }

    @Test(expected = NoSuchElementException.class)
    public final void testSubSetThrowsExceptionIfFromElementIsNotContained() {
        Collection<Integer> collection = Arrays.asList(1, 2);
        SortedArraySet<Integer> sortedArraySet = new SortedArraySet<>(collection);
        sortedArraySet.subSet(0, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testSubSetThrowsExceptionIfToElementIsNull() {
        new SortedArraySet<>().subSet(1, null);
    }

    @Test(expected = NoSuchElementException.class)
    public final void testSubSetThrowsExceptionIfToElementIsNotContained() {
        Collection<Integer> collection = Arrays.asList(1, 2);
        SortedArraySet<Integer> sortedArraySet = new SortedArraySet<>(collection);
        sortedArraySet.subSet(1, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testSubSetThrowsExceptionIfFromElementIsGreaterThanToElement() {
        Collection<Integer> collection = Arrays.asList(1, 2);
        SortedArraySet<Integer> sortedArraySet = new SortedArraySet<>(collection);
        sortedArraySet.subSet(2, 1);
    }

    @Test
    public final void testHeadSet() {
        Collection<Integer> collection = Arrays.asList(1, 2, 3, 4, 5);
        SortedArraySet<Integer> sortedArraySet = new SortedArraySet<>(collection);
        SortedSet<Integer> headSet = sortedArraySet.headSet(3);
        assertEquals(3, headSet.size());
        Iterator<Integer> iterator = headSet.iterator();
        assertEquals((Integer) 1, iterator.next());
        assertEquals((Integer) 2, iterator.next());
        assertEquals((Integer) 3, iterator.next());
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testHeadSetThrowsExceptionIfToElementIsNull() {
        new SortedArraySet<>().headSet(null);
    }

    @Test(expected = NoSuchElementException.class)
    public final void testHeadSetThrowsExceptionIfToElementIsNotContained() {
        new SortedArraySet<>().headSet(1);
    }

    @Test
    public final void testTailSet() {
        Collection<Integer> collection = Arrays.asList(1, 2, 3, 4, 5);
        SortedArraySet<Integer> sortedArraySet = new SortedArraySet<>(collection);
        SortedSet<Integer> tailSet = sortedArraySet.tailSet(3);
        assertEquals(3, tailSet.size());
        Iterator<Integer> iterator = tailSet.iterator();
        assertEquals((Integer) 3, iterator.next());
        assertEquals((Integer) 4, iterator.next());
        assertEquals((Integer) 5, iterator.next());
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testTailSetThrowsExceptionIfFromElementIsNull() {
        new SortedArraySet<>().tailSet(null);
    }

    @Test(expected = NoSuchElementException.class)
    public final void testTailSetThrowsExceptionIfFromElementIsNotContained() {
        new SortedArraySet<>().tailSet(1);
    }

    @Test
    public final void testFirst() {
        Collection<Integer> collection = Arrays.asList(1, 2, 3);
        SortedArraySet<Integer> sortedArraySet = new SortedArraySet<>(collection);
        int first = sortedArraySet.first();
        assertEquals(1, first);
    }

    @Test(expected = NoSuchElementException.class)
    public final void testFirstThrowsExceptionIfEmpty() {
        new SortedArraySet<>().first();
    }

    @Test
    public final void testLast() {
        Collection<Integer> collection = Arrays.asList(1, 2, 3);
        SortedArraySet<Integer> sortedArraySet = new SortedArraySet<>(collection);
        int last = sortedArraySet.last();
        assertEquals(3, last);
    }

    @Test(expected = NoSuchElementException.class)
    public final void testLastThrowsExceptionIfEmpty() {
        new SortedArraySet<>().last();
    }

    @Test
    public final void testSize() {
        Collection<Integer> collection = Arrays.asList(1, 2, 3);
        SortedArraySet<Integer> sortedArraySet = new SortedArraySet<>(collection);
        assertEquals(collection.size(), sortedArraySet.size());
    }

    @Test
    public final void testIsEmpty() {
        SortedArraySet<Integer> sortedArraySet = new SortedArraySet<>();
        assertTrue(sortedArraySet.isEmpty());
        sortedArraySet.add(1);
        assertFalse(sortedArraySet.isEmpty());
    }

    @Test
    public final void testContains() {
        SortedArraySet<Integer> sortedArraySet = new SortedArraySet<>();
        sortedArraySet.add(1);
        assertFalse(sortedArraySet.contains(2));
        sortedArraySet.add(2);
        assertTrue(sortedArraySet.contains(2));
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testContainsThrowsExceptionIfItemIsNull() {
        new SortedArraySet<>().contains(null);
    }

    @Test
    public final void testIterator() {
        Collection<Integer> collection = Arrays.asList(2, 3, 1, 4, 2);
        SortedArraySet<Integer> sortedArraySet = new SortedArraySet<>(collection);
        Iterator<Integer> iterator = sortedArraySet.iterator();
        assertEquals((Integer) 1, iterator.next());
        assertEquals((Integer) 2, iterator.next());
        assertEquals((Integer) 3, iterator.next());
        assertEquals((Integer) 4, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public final void testToArray() {
        Collection<Integer> collection = Arrays.asList(2, 3, 1, 4, 2);
        SortedArraySet<Integer> sortedArraySet = new SortedArraySet<>(collection);
        Object[] array = sortedArraySet.toArray();
        assertEquals(4, array.length);
        assertEquals(1, array[0]);
        assertEquals(2, array[1]);
        assertEquals(3, array[2]);
        assertEquals(4, array[3]);
    }

    @Test
    public final void testToArrayWithArrayParameter() {
        Collection<Integer> collection = Arrays.asList(2, 3, 1, 4, 2);
        SortedArraySet<Integer> sortedArraySet = new SortedArraySet<>(collection);
        Integer[] array = new Integer[sortedArraySet.size()];
        sortedArraySet.toArray(array);
        assertEquals(4, array.length);
        assertEquals((Integer) 1, array[0]);
        assertEquals((Integer) 2, array[1]);
        assertEquals((Integer) 3, array[2]);
        assertEquals((Integer) 4, array[3]);
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testToArrayWithArrayParameterThrowsExceptionIfArrayIsNull() {
        new SortedArraySet<>().toArray(null);
    }

    @Test
    public final void testAdd() {
        SortedArraySet<Integer> sortedArraySet = new SortedArraySet<>();
        boolean result = sortedArraySet.add(3);
        assertTrue(result);
        assertEquals(1, sortedArraySet.size());
        result = sortedArraySet.add(3);
        assertFalse(result);
        assertEquals(1, sortedArraySet.size());
        sortedArraySet.add(4);
        sortedArraySet.add(2);
        sortedArraySet.add(1);
        sortedArraySet.add(5);
        assertEquals(5, sortedArraySet.size());
        Iterator<Integer> iterator = sortedArraySet.iterator();
        assertEquals((Integer) 1, iterator.next());
        assertEquals((Integer) 2, iterator.next());
        assertEquals((Integer) 3, iterator.next());
        assertEquals((Integer) 4, iterator.next());
        assertEquals((Integer) 5, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testAddThrowsExceptionIfItemIsNull() {
        new SortedArraySet<>().add(null);
    }

    @Test
    public final void testRemove() {
        SortedArraySet<Integer> sortedArraySet = new SortedArraySet<>();
        sortedArraySet.add(1);
        sortedArraySet.add(2);
        assertEquals(2, sortedArraySet.size());
        boolean result = sortedArraySet.remove(1);
        assertTrue(result);
        assertEquals(1, sortedArraySet.size());
        result = sortedArraySet.remove(1);
        assertFalse(result);
        assertEquals(1, sortedArraySet.size());
        assertEquals((Integer) 2, sortedArraySet.first());
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testRemoveThrowsExceptionIfItemIsNull() {
        new SortedArraySet<>().remove(null);
    }

    @Test
    public final void testContainsAll() {
        Collection<Integer> collection = Arrays.asList(3, 2, 5, 1);
        SortedArraySet<Integer> sortedArraySet = new SortedArraySet<>(collection);
        assertTrue(sortedArraySet.containsAll(collection));
        sortedArraySet.remove(3);
        assertFalse(sortedArraySet.containsAll(collection));
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testContainsAllThrowsExceptionIfCollectionIsNull() {
        new SortedArraySet<>().containsAll(null);
    }

    @Test
    public final void testAddAll() {
        Collection<Integer> collection = Arrays.asList(3, 2, 4, 1, 5);
        SortedArraySet<Integer> sortedArraySet = new SortedArraySet<>();
        assertTrue(sortedArraySet.isEmpty());
        boolean result = sortedArraySet.addAll(collection);
        assertTrue(result);
        assertTrue(sortedArraySet.containsAll(collection));
        Iterator<Integer> iterator = sortedArraySet.iterator();
        assertEquals((Integer) 1, iterator.next());
        assertEquals((Integer) 2, iterator.next());
        assertEquals((Integer) 3, iterator.next());
        assertEquals((Integer) 4, iterator.next());
        assertEquals((Integer) 5, iterator.next());
        assertFalse(iterator.hasNext());
        Collection<Integer> collection2 = Arrays.asList(5, 6);
        result = sortedArraySet.addAll(collection2);
        assertFalse(result);
        assertTrue(sortedArraySet.containsAll(collection2));
    }

    @Test
    public final void testAddAllIfCollectionIfEmpty() {
        Collection<Integer> collection = new LinkedList<>();
        SortedArraySet<Integer> sortedArraySet = new SortedArraySet<>();
        assertTrue(sortedArraySet.isEmpty());
        boolean result = sortedArraySet.addAll(collection);
        assertFalse(result);
        assertTrue(sortedArraySet.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testAddAllThrowsExceptionIfCollectionIsNull() {
        new SortedArraySet<>().addAll(null);
    }

    @Test
    public final void testRetainAll() {
        Collection<Integer> collection = Arrays.asList(3, 2, 5, 1);
        Collection<Integer> collection2 = Arrays.asList(5, 6);
        SortedArraySet<Integer> sortedArraySet = new SortedArraySet<>();
        sortedArraySet.addAll(collection);
        sortedArraySet.addAll(collection2);
        assertTrue(sortedArraySet.containsAll(collection));
        assertTrue(sortedArraySet.containsAll(collection2));
        boolean result = sortedArraySet.retainAll(collection);
        assertTrue(result);
        assertTrue(sortedArraySet.containsAll(collection));
        assertFalse(sortedArraySet.containsAll(collection2));
        result = sortedArraySet.retainAll(collection);
        assertFalse(result);
        assertTrue(sortedArraySet.containsAll(collection));
        assertFalse(sortedArraySet.containsAll(collection2));
    }

    @Test
    public final void testRetainAllIfCollectionIsEmpty() {
        Collection<Integer> collection = Arrays.asList(3, 2, 5, 1);
        Collection<Integer> collection2 = new LinkedList<>();
        SortedArraySet<Integer> sortedArraySet = new SortedArraySet<>(collection);
        assertTrue(sortedArraySet.containsAll(collection));
        boolean result = sortedArraySet.retainAll(collection2);
        assertFalse(result);
        assertTrue(sortedArraySet.containsAll(collection));
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testRetainAllThrowsExceptionIfCollectionIsNull() {
        new SortedArraySet<>().retainAll(null);
    }

    @Test
    public final void testRemoveAll() {
        Collection<Integer> collection = Arrays.asList(3, 2, 5, 1);
        SortedArraySet<Integer> sortedArraySet = new SortedArraySet<>(collection);
        assertTrue(sortedArraySet.containsAll(collection));
        boolean result = sortedArraySet.removeAll(collection);
        assertTrue(result);
        assertTrue(sortedArraySet.isEmpty());
        result = sortedArraySet.removeAll(collection);
        assertFalse(result);
    }

    @Test
    public final void testRemoveAllIfCollectionIsEmpty() {
        Collection<Integer> collection = Arrays.asList(3, 2, 5, 1);
        SortedArraySet<Integer> sortedArraySet = new SortedArraySet<>(collection);
        assertTrue(sortedArraySet.containsAll(collection));
        boolean result = sortedArraySet.removeAll(new LinkedList<>());
        assertFalse(result);
        assertTrue(sortedArraySet.containsAll(collection));
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testRemoveAllThrowsExceptionIfCollectionIsNull() {
        new SortedArraySet<>().removeAll(null);
    }

    @Test
    public final void testClear() {
        Collection<Integer> collection = Arrays.asList(3, 2, 5, 1);
        SortedArraySet<Integer> sortedArraySet = new SortedArraySet<>(collection);
        assertFalse(sortedArraySet.isEmpty());
        sortedArraySet.clear();
        assertTrue(sortedArraySet.isEmpty());
    }

    @Test
    public final void testToString() {
        Collection<Integer> collection = Arrays.asList(1, 2, 3);
        SortedArraySet<Integer> sortedArraySet = new SortedArraySet<>(collection);
        assertEquals("[1, 2, 3]", sortedArraySet.toString());
    }

    @Test
    public final void testHashCode() {
        SortedArraySet<Integer> sortedArraySet1 = new SortedArraySet<>();
        SortedArraySet<Integer> sortedArraySet2 = new SortedArraySet<>();
        assertEquals(sortedArraySet1.hashCode(), sortedArraySet1.hashCode());
        assertEquals(sortedArraySet1.hashCode(), sortedArraySet2.hashCode());
        sortedArraySet2.add(1);
        assertNotEquals(sortedArraySet1.hashCode(), sortedArraySet2.hashCode());
        sortedArraySet1.add(1);
        assertEquals(sortedArraySet1.hashCode(), sortedArraySet2.hashCode());
    }

    @Test
    public final void testEquals() {
        SortedArraySet<Integer> sortedArraySet1 = new SortedArraySet<>();
        SortedArraySet<Integer> sortedArraySet2 = new SortedArraySet<>();
        assertFalse(sortedArraySet1.equals(null));
        assertFalse(sortedArraySet1.equals(new Object()));
        assertTrue(sortedArraySet1.equals(sortedArraySet1));
        assertTrue(sortedArraySet1.equals(sortedArraySet2));
        sortedArraySet2.add(1);
        assertFalse(sortedArraySet1.equals(sortedArraySet2));
        sortedArraySet1.add(1);
        assertTrue(sortedArraySet1.equals(sortedArraySet2));
    }

}
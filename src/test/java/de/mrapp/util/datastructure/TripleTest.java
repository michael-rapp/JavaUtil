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

import static junit.framework.TestCase.*;
import static org.junit.Assert.assertEquals;

/**
 * Tests the functionality of the class {@link Triple}.
 *
 * @author Michael Rapp
 */
public class TripleTest {

    /**
     * Tests, if all properties are set correctly by the constructor.
     */
    @Test
    public final void testConstructor() {
        Object first = new Object();
        Object second = new Object();
        Object third = new Object();
        Triple<Object, Object, Object> triple = new Triple<>(first, second, third);
        assertEquals(first, triple.first);
        assertEquals(second, triple.second);
        assertEquals(third, triple.third);
    }

    /**
     * Tests the functionality of the create-method.
     */
    @Test
    public final void testCreate() {
        Object first = new Object();
        Object second = new Object();
        Object third = new Object();
        Triple<Object, Object, Object> triple = Triple.create(first, second, third);
        assertEquals(first, triple.first);
        assertEquals(second, triple.second);
        assertEquals(third, triple.third);
    }

    /**
     * Tests the functionality of the hashCode-method.
     */
    @Test
    public final void testHashCode() {
        Triple<String, String, String> triple1 = new Triple<>(null, null, null);
        Triple<String, String, String> triple2 = new Triple<>(null, null, null);
        assertEquals(triple1.hashCode(), triple1.hashCode());
        assertEquals(triple2.hashCode(), triple2.hashCode());
        triple2 = new Triple<>("first", null, null);
        assertNotSame(triple1.hashCode(), triple2.hashCode());
        triple1 = new Triple<>("foo", null, null);
        assertNotSame(triple1.hashCode(), triple2.hashCode());
        triple1 = new Triple<>(triple2.first, null, null);
        triple2 = new Triple<>("first", "second", null);
        assertNotSame(triple1.hashCode(), triple2.hashCode());
        triple1 = new Triple<>("first", "foo", null);
        assertNotSame(triple1.hashCode(), triple2.hashCode());
        triple1 = new Triple<>("first", "second", null);
        triple2 = new Triple<>("first", "second", "third");
        assertNotSame(triple1.hashCode(), triple2.hashCode());
        triple1 = new Triple<>("first", "second", "foo");
        assertNotSame(triple1.hashCode(), triple2.hashCode());
    }

    /**
     * Tests the functionality of the equals-method.
     */
    @Test
    public final void testEquals() {
        Triple<String, String, String> triple1 = new Triple<>(null, null, null);
        Triple<String, String, String> triple2 = new Triple<>(null, null, null);
        assertTrue(triple1.equals(triple1));
        assertTrue(triple1.equals(triple2));
        assertFalse(triple1.equals(null));
        assertFalse(triple1.equals(new Object()));
        triple2 = new Triple<>("first", null, null);
        assertFalse(triple1.equals(triple2));
        triple1 = new Triple<>("foo", null, null);
        assertFalse(triple1.equals(triple2));
        triple1 = new Triple<>("first", null, null);
        triple2 = new Triple<>("first", "second", null);
        assertFalse(triple1.equals(triple2));
        triple1 = new Triple<>("first", "foo", null);
        assertFalse(triple1.equals(triple2));
        triple1 = new Triple<>("first", "second", null);
        triple2 = new Triple<>("first", "second", "third");
        assertFalse(triple1.equals(triple2));
        triple1 = new Triple<>("first", "second", "foo");
        assertFalse(triple1.equals(triple2));
    }

}
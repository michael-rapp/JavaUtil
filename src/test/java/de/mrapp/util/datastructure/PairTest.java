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
 * Tests the functionality of the class {@link Pair}.
 *
 * @author Michael Rapp
 */
public class PairTest {

    /**
     * Tests, if all properties are set correctly by the constructor.
     */
    @Test
    public final void testConstructor() {
        Object first = new Object();
        Object second = new Object();
        Pair<Object, Object> pair = new Pair<>(first, second);
        assertEquals(first, pair.first);
        assertEquals(second, pair.second);
    }

    /**
     * Tests the functionality of the create-method.
     */
    @Test
    public final void testCreate() {
        Object first = new Object();
        Object second = new Object();
        Pair<Object, Object> pair = Pair.create(first, second);
        assertEquals(first, pair.first);
        assertEquals(second, pair.second);
    }

    /**
     * Tests the functionality of the hashCode-method.
     */
    @Test
    public final void testHashCode() {
        Pair<String, String> pair1 = new Pair<>(null, null);
        Pair<String, String> pair2 = new Pair<>(null, null);
        assertEquals(pair1.hashCode(), pair1.hashCode());
        assertEquals(pair2.hashCode(), pair2.hashCode());
        pair2 = new Pair<>("first", null);
        assertNotSame(pair1.hashCode(), pair2.hashCode());
        pair1 = new Pair<>("foo", null);
        assertNotSame(pair1.hashCode(), pair2.hashCode());
        pair1 = new Pair<>(pair2.first, null);
        pair2 = new Pair<>("first", "second");
        assertNotSame(pair1.hashCode(), pair2.hashCode());
        pair1 = new Pair<>("first", "foo");
        assertNotSame(pair1.hashCode(), pair2.hashCode());
    }

    /**
     * Tests the functionality of the equals-method.
     */
    @Test
    public final void testEquals() {
        Pair<String, String> pair1 = new Pair<>(null, null);
        Pair<String, String> pair2 = new Pair<>(null, null);
        assertTrue(pair1.equals(pair1));
        assertTrue(pair1.equals(pair2));
        assertFalse(pair1.equals(null));
        assertFalse(pair1.equals(new Object()));
        pair2 = new Pair<>("first", null);
        assertFalse(pair1.equals(pair2));
        pair1 = new Pair<>("foo", null);
        assertFalse(pair1.equals(pair2));
        pair1 = new Pair<>("first", null);
        pair2 = new Pair<>("first", "second");
        assertFalse(pair1.equals(pair2));
        pair1 = new Pair<>("first", "foo");
        assertFalse(pair1.equals(pair2));
    }

}
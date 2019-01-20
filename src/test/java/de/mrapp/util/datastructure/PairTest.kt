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

import junit.framework.TestCase.*
import org.junit.Assert.assertEquals
import kotlin.test.Test

/**
 * Tests the functionality of the class [Pair].
 *
 * @author Michael Rapp
 */
class PairTest {

    @Test
    fun testConstructor() {
        val first = Any()
        val second = Any()
        val pair = Pair(first, second)
        assertEquals(first, pair.first)
        assertEquals(second, pair.second)
    }

    @Test
    fun testCreate() {
        val first = Any()
        val second = Any()
        val pair = Pair.create(first, second)
        assertEquals(first, pair.first)
        assertEquals(second, pair.second)
    }

    @Test
    fun testHashCode() {
        var pair1 = Pair<String, String>(null, null)
        var pair2 = Pair<String, String>(null, null)
        assertEquals(pair1.hashCode().toLong(), pair1.hashCode().toLong())
        assertEquals(pair2.hashCode().toLong(), pair2.hashCode().toLong())
        pair2 = Pair<String, String>("first", null)
        assertNotSame(pair1.hashCode(), pair2.hashCode())
        pair1 = Pair<String, String>("foo", null)
        assertNotSame(pair1.hashCode(), pair2.hashCode())
        pair1 = Pair<String, String>(pair2.first, null)
        pair2 = Pair("first", "second")
        assertNotSame(pair1.hashCode(), pair2.hashCode())
        pair1 = Pair("first", "foo")
        assertNotSame(pair1.hashCode(), pair2.hashCode())
    }

    @Test
    fun testEquals() {
        var pair1 = Pair<String, String>(null, null)
        var pair2 = Pair<String, String>(null, null)
        assertTrue(pair1 == pair1)
        assertTrue(pair1 == pair2)
        assertFalse(pair1 == null)
        assertFalse(pair1 == Any())
        pair2 = Pair<String, String>("first", null)
        assertFalse(pair1 == pair2)
        pair1 = Pair<String, String>("foo", null)
        assertFalse(pair1 == pair2)
        pair1 = Pair<String, String>("first", null)
        pair2 = Pair("first", "second")
        assertFalse(pair1 == pair2)
        pair1 = Pair("first", "foo")
        assertFalse(pair1 == pair2)
    }

}
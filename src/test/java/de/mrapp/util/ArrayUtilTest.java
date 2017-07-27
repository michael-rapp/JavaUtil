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
package de.mrapp.util;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests the functionality of the class {@link ArrayUtil}.
 *
 * @author Michael Rapp
 */
public class ArrayUtilTest {

    @Test
    public final void testIndexOf() {
        boolean[] array = {false, true, true, false};
        int index = ArrayUtil.indexOf(array, true);
        assertEquals(1, index);
    }

    @Test
    public final void testIndexOfIfValueIsNotContained() {
        boolean[] array = {false, false, false, false};
        int index = ArrayUtil.indexOf(array, true);
        assertEquals(-1, index);
    }

    @Test
    public final void testLastIndexOf() {
        boolean[] array = {false, true, true, false};
        int index = ArrayUtil.lastIndexOf(array, true);
        assertEquals(2, index);
    }

    @Test
    public final void testLastIndexOfIfValueIsNotContained() {
        boolean[] array = {false, false, false, false};
        int index = ArrayUtil.lastIndexOf(array, true);
        assertEquals(-1, index);
    }

    @Test
    public final void testContains() {
        boolean[] array = {false, true, true, false};
        assertTrue(ArrayUtil.contains(array, true));
    }

    @Test
    public final void testContainsIfValueIsNotContained() {
        boolean[] array = {false, false, false, false};
        assertFalse(ArrayUtil.contains(array, true));
    }

}
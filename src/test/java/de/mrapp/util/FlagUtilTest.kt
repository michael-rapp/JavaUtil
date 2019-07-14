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
package de.mrapp.util

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import kotlin.test.Test

/**
 * Tests the functionality of the class [FlagUtil].
 *
 * @author Michael Rapp
 */
class FlagUtilTest {

    companion object {

        private const val FLAG1 = 0x100

        private const val FLAG2 = 0x010

        private const val FLAG3 = 0x001
    }

    @Test
    fun testIsFlagSet() {
        val flags = FLAG1.or(FLAG2)
        assertTrue(FlagUtil.isFlagSet(flags, FLAG1))
        assertFalse(FlagUtil.isFlagSet(flags, FLAG3))
    }

    @Test
    fun testSetFlag() {
        var flags = FLAG1.or(FLAG2)
        flags = FlagUtil.setFlag(flags, FLAG3)
        assertTrue(FlagUtil.isFlagSet(flags, FLAG3))
    }

    @Test
    fun testSetFlagWithTrueArgument() {
        var flags = FLAG1.or(FLAG2)
        flags = FlagUtil.setFlag(flags, FLAG3, true)
        assertTrue(FlagUtil.isFlagSet(flags, FLAG3))
    }

    @Test
    fun testUnsetFlag() {
        var flags = FLAG1.or(FLAG2)
        flags = FlagUtil.unsetFlag(flags, FLAG2)
        assertFalse(FlagUtil.isFlagSet(flags, FLAG2))
    }

    @Test
    fun testSetFlagWithFalseArgument() {
        var flags = FLAG1.or(FLAG2)
        flags = FlagUtil.setFlag(flags, FLAG2, false)
        assertFalse(FlagUtil.isFlagSet(flags, FLAG2))
    }

    @Test
    fun testToggleFlag() {
        var flags = FLAG1.or(FLAG2)
        flags = FlagUtil.toggleFlag(flags, FLAG2)
        flags = FlagUtil.toggleFlag(flags, FLAG3)
        assertFalse(FlagUtil.isFlagSet(flags, FLAG2))
        assertTrue(FlagUtil.isFlagSet(flags, FLAG3))
    }

}
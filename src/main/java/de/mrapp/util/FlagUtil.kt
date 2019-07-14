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

/**
 * An utility class that provides method for handling flags.
 *
 * @author Michael Rapp
 * @since 2.4.0
 */
object FlagUtil {

    /**
     * Returns whether a specific [flag] is set.
     */
    fun isFlagSet(flags: Int, flag: Int) = flags.and(flag) == flag

    /**
     * Sets a specific [flag].
     */
    fun setFlag(flags: Int, flag: Int): Int {
        if (!isFlagSet(flags, flag)) {
            return flags.or(flag)
        }

        return flags
    }

    /**
     * Sets or unsets a specific [flag].
     *
     * @param flag True, if the flag should be set, false, if it should be unset
     */
    fun setFlag(flags: Int, flag: Int, set: Boolean) =
            if (set) setFlag(flags, flag) else unsetFlag(flags, flag)

    /**
     * Unsets a specific [flag].
     */
    fun unsetFlag(flags: Int, flag: Int): Int {
        if (isFlagSet(flags, flag)) {
            return flags.xor(flag)
        }

        return flags
    }

    fun toggleFlag(flags: Int, flag: Int): Int =
            if (isFlagSet(flags, flag)) unsetFlag(flags, flag) else setFlag(flags, flag)

}

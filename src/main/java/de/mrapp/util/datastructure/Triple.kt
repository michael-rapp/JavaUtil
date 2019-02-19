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

import java.io.Serializable

/**
 * A data structure that eases to pass around a triple of three objects. This object provides a
 * sensible implementation of equals(), returning true if equals() is true on each of the contained
 * objects.
 *
 * @param F The type of the first object
 * @param S The type of the second object
 * @param T The type of the third object
 * @property first The first object
 * @property second The second object
 * @property third The third object
 * @author Michael Rapp
 * @since 1.0.0
 */
data class Triple<F, S, T>(val first: F?, val second: S?, val third: T?) : Serializable {

    companion object {

        /**
         * Creates a new triple, consisting of three objects [first], [second] and [third].
         *
         * @param F The type of the first object
         * @param S The type of the second object
         * @param T The type of the third object
         * @return The triple, which has been created
         */
        fun <F, S, T> create(first: F, second: S, third: T): Triple<F, S, T> {
            return Triple(first, second, third)
        }

    }

}

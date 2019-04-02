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

import de.mrapp.util.Condition
import java.util.*

/**
 * An extension of the class [PriorityQueue] that has a fixed size. If adding an item causes the
 * maximum size to be exceeded, the smallest/first element is removed.
 *
 * @param T The type of the items that are contained by the queue
 * @param maxSize The maximum size of the queue
 * @author Michael Rapp
 * @since 2.3.0
 */
class FixedPriorityQueue<T> @JvmOverloads constructor(private val maxSize: Int,
                                                      comparator: Comparator<in T>? = null) :
        PriorityQueue<T>(maxSize, comparator) {

    @Suppress("UNCHECKED_CAST")
    private fun compare(first: T, second: T) = comparator()?.compare(first, second)
            ?: (first as Comparable<T>).compareTo(second)

    init {
        Condition.ensureAtLeast(maxSize, 1, "The maximum size must be at least 1")
    }

    @JvmOverloads
    constructor(maxSize: Int, items: Collection<T>,
                comparator: Comparator<in T>? = null) : this(maxSize, comparator) {
        items.forEach { offer(it) }
    }

    override fun offer(e: T): Boolean {
        if (size >= maxSize) {
            val first = peek()

            if (compare(e, first) > 0) {
                poll()
                return super.offer(e)
            }

            return false
        }

        return super.offer(e)
    }

}
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

import de.mrapp.util.Condition.ensureNotNull


/**
 * An utility class that provides methods that allows to handle [Iterator]s and [Iterable]s.
 *
 * @author Michael Rapp
 * @since 2.2.0
 */
object IteratorUtil {

    /**
     * Creates and returns an [Iterable] that maps the items that are traversed by another
     * [iterable] using a specific [mapper].
     *
     * @param I The type of the items that are traversed by the given [Iterable]
     * @param O The type, the items are mapped to
     */
    fun <I, O> createMappedIterable(iterable: Iterable<I>, mapper: (I) -> O): Iterable<O> {
        ensureNotNull(iterable, "The iterable may not be null")
        return Iterable { createMappedIterator(iterable.iterator(), mapper) }
    }

    /**
     * Creates and returns an [Iterator] that maps the items that are traversed by another
     * [iterator] using a specific [mapper].
     *
     * @param I The type of the items that are traversed by the given [Iterator]
     * @param O The type, the items are mapped to
     */
    fun <I, O> createMappedIterator(iterator: Iterator<I>, mapper: (I) -> O): Iterator<O> {
        ensureNotNull(iterator, "The iterator may not be null")
        ensureNotNull(mapper, "The mapper may not be null")
        return object : Iterator<O> {

            override fun hasNext() = iterator.hasNext()

            override fun next() = mapper.invoke(iterator.next())

        }
    }

    /**
     * Creates and returns an [Iterable] that allows to traverse the items that are traversed by two
     * other [Iterable]s, [first] and [second].
     *
     * @param T The type of the items that are traversed by the given [Iterable]s
     */
    fun <T> createConcatenatedIterable(first: Iterable<T>, second: Iterable<T>): Iterable<T> {
        ensureNotNull(first, "The first iterable may not be null")
        ensureNotNull(second, "The second iterable may not be null")
        return Iterable { createConcatenatedIterator(first.iterator(), second.iterator()) }
    }

    /**
     * Creates and returns an [Iterator] that allows to traverse the items that are traversed by two
     * other [Iterator]s, [first] and [second].
     *
     * @param T The type of the items that are traversed by the given [Iterator]s
     */
    fun <T> createConcatenatedIterator(first: Iterator<T>, second: Iterator<T>): Iterator<T> {
        ensureNotNull(first, "The first iterator may not be null")
        ensureNotNull(second, "The second iterator may not be null")
        return object : Iterator<T> {

            override fun hasNext() = first.hasNext() || second.hasNext()

            override fun next() = try {
                first.next()
            } catch (e: NoSuchElementException) {
                second.next()
            }

        }
    }

    /**
     * Creates and returns an [Iterable] that allows to traverse the items that are traversed by
     * another [iterable] except for the items for which a [filter] returns false.
     *
     * @param T The type of the items that are traversed by the given [Iterable]
     */
    fun <T> createFilteredIterable(iterable: Iterable<T>, filter: (T) -> Boolean): Iterable<T> {
        return Iterable { createFilteredIterator(iterable.iterator(), filter) }
    }

    /**
     * Creates and returns an [Iterator] that allows to traverse the items that are traversed by
     * another [iterator] except for the items for which a [filter] returns false.
     */
    fun <T> createFilteredIterator(iterator: Iterator<T>, filter: (T) -> Boolean): Iterator<T> {
        return object : Iterator<T> {

            private var next = computeNext()

            private fun computeNext(): T? {
                var result: T? = null

                while (result == null && iterator.hasNext()) {
                    val next = iterator.next()

                    if (filter.invoke(next)) {
                        result = next
                    }
                }

                return result
            }

            override fun hasNext() = next != null

            override fun next() = next?.let {
                next = computeNext()
                it
            } ?: throw NoSuchElementException()

        }
    }

}
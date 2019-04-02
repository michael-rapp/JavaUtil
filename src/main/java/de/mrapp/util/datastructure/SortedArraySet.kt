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
import java.io.Serializable
import java.util.*
import kotlin.NoSuchElementException
import kotlin.collections.HashSet

/**
 * A set, whose items are sorted based on a specific [Comparator] or according to their
 * implementation of the interface [Comparable]. In contrast to a [TreeSet], two items are
 * considered as duplicates, if their hash codes are equal, instead of evaluating the result of
 * the [Comparator] or [Comparable#compareTo(Object)] method.
 *
 * The set internally uses a [SortedArrayList] to keep the items in a sorted order whenever
 * updated. In addition the hash codes of all items, which are contained by the set, are maintained
 * in a [HashSet] to prevent duplicates from being added to the set.
 *
 * @param T The type of the items, which are contained by the set
 * @author Michael Rapp
 * @since 1.1.0
 */
open class SortedArraySet<T>(initialCapacity: Int, comparator: Comparator<in T>?) : SortedSet<T>,
        NavigableSet<T>, Serializable {

    private val sortedArrayList = SortedArrayList(initialCapacity, comparator)

    private val hashCodes = HashSet<Int>(initialCapacity)

    constructor() : this(0, null)

    constructor(items: Collection<T>) : this(0, null) {
        addAll(items)
    }

    constructor(initialCapacity: Int) : this(initialCapacity, null)

    constructor(comparator: Comparator<in T>?) : this(0, comparator)

    constructor(items: Collection<T>, comparator: Comparator<in T>?) : this(0, comparator) {
        addAll(items)
    }

    override fun comparator(): Comparator<in T>? = sortedArrayList.comparator()

    override fun first(): T {
        Condition.ensureFalse(isEmpty(), "Set is empty", NoSuchElementException::class.java)
        return sortedArrayList[0]
    }

    override fun last(): T {
        Condition.ensureFalse(isEmpty(), "Set is empty", NoSuchElementException::class.java)
        return sortedArrayList[sortedArrayList.size - 1]
    }

    override val size: Int
        get() = sortedArrayList.size

    override fun isEmpty(): Boolean = sortedArrayList.isEmpty()

    override fun contains(element: T): Boolean = hashCodes.contains(element.hashCode())

    override fun iterator(): MutableIterator<T> = sortedArrayList.iterator()

    override fun add(element: T): Boolean {
        val hashCode = element.hashCode()

        if (hashCodes.add(hashCode)) {
            sortedArrayList.add(element)
            return true
        }

        return false
    }

    override fun remove(element: T): Boolean {
        val hashCode = element.hashCode()

        if (hashCodes.remove(hashCode)) {
            sortedArrayList.remove(element)
            return true
        }

        return false
    }

    override fun containsAll(elements: Collection<T>): Boolean =
            elements.map { contains(it) }.reduce { acc, b -> acc && b }

    override fun addAll(elements: Collection<T>): Boolean =
            !elements.isEmpty() && elements.map { add(it) }.reduce { acc, b -> acc && b }

    override fun retainAll(elements: Collection<T>): Boolean {
        if (elements.isNotEmpty()) {
            var result = false

            for (i in size - 1 downTo 0) {
                val item = sortedArrayList[i]

                if (item != null && !elements.contains(item)) {
                    val hashCode = item.hashCode()
                    sortedArrayList.removeAt(i)
                    hashCodes.remove(hashCode)
                    result = true
                }
            }

            return result
        }

        return false
    }

    override fun removeAll(elements: Collection<T>): Boolean =
            !elements.isEmpty() && elements.map { remove(it) }.reduce { acc, b -> acc && b }

    override fun clear() {
        hashCodes.clear()
        sortedArrayList.clear()
    }

    override fun subSet(fromElement: T, toElement: T) = subSet(fromElement, true, toElement, false)

    override fun subSet(fromElement: T, fromInclusive: Boolean, toElement: T,
                        toInclusive: Boolean): NavigableSet<T> {
        throw UnsupportedOperationException()
    }

    override fun headSet(toElement: T) = headSet(toElement, false)

    override fun headSet(toElement: T, inclusive: Boolean): NavigableSet<T> {
        throw UnsupportedOperationException()
    }

    override fun tailSet(fromElement: T) = tailSet(fromElement, true)

    override fun tailSet(fromElement: T, inclusive: Boolean): NavigableSet<T> {
        throw UnsupportedOperationException()
    }

    override fun lower(e: T): T {
        throw UnsupportedOperationException()
    }

    override fun floor(e: T): T {
        throw UnsupportedOperationException()
    }

    override fun higher(e: T): T {
        throw UnsupportedOperationException()
    }

    override fun ceiling(e: T): T {
        throw UnsupportedOperationException()
    }

    override fun descendingSet(): NavigableSet<T> {
        throw UnsupportedOperationException()
    }

    override fun pollFirst(): T {
        if (isNotEmpty()) {
            val result = sortedArrayList.first()
            remove(result)
            return result
        }

        throw NoSuchElementException()
    }

    override fun pollLast(): T {
        if (isNotEmpty()) {
            val result = sortedArrayList.last()
            remove(result)
            return result
        }

        throw NoSuchElementException()
    }

    override fun descendingIterator(): MutableIterator<T> {
        throw UnsupportedOperationException()
    }

    override fun toString(): String = sortedArrayList.toString()

    override fun hashCode(): Int {
        val prime = 31
        var result = 1
        result = prime * result + sortedArrayList.hashCode()
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other)
            return true
        if (other == null)
            return false
        if (javaClass != other.javaClass)
            return false
        val another = other as SortedArraySet<*>
        return sortedArrayList == another.sortedArrayList
    }

}
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
import de.mrapp.util.Condition.ensureFalse
import de.mrapp.util.Condition.ensureNotEqual
import de.mrapp.util.Condition.ensureNotNull
import java.util.*
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
 * @author Michael Rapp
 * @since 1.1.0
 */
open class SortedArraySet<T>(initialCapacity: Int,
                        comparator: Comparator<in T>?) : SortedSet<T> {

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

    override fun subSet(fromElement: T, toElement: T): SortedSet<T> {
        val start = sortedArrayList.indexOf(fromElement)
        ensureNotEqual(start, -1, "fromElement not contained by set",
                NoSuchElementException::class.java)
        val end = sortedArrayList.indexOf(toElement)
        ensureNotEqual(end, -1, "toElement not contained by set",
                NoSuchElementException::class.java)
        ensureFalse(start > end, "fromElement greater than toElement")
        val subSet = SortedArraySet<T>(end - start + 1)

        for (i in start..end) {
            subSet.add(sortedArrayList[i])
        }

        return subSet
    }

    override fun headSet(toElement: T): SortedSet<T> {
        val end = sortedArrayList.indexOf(toElement)
        ensureNotEqual(end, -1, "toElement not contained by set",
                NoSuchElementException::class.java)
        val headSet = SortedArraySet<T>(end + 1)

        for (i in 0..end) {
            headSet.add(sortedArrayList[i])
        }

        return headSet
    }

    override fun tailSet(fromElement: T): SortedSet<T> {
        val start = sortedArrayList.indexOf(fromElement)
        ensureNotEqual(start, -1, "fromElement not contained by set",
                NoSuchElementException::class.java)
        val tailSet = SortedArraySet<T>(size - start)

        for (i in start until size) {
            tailSet.add(sortedArrayList[i])
        }

        return tailSet
    }

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

    override fun contains(element: T): Boolean = sortedArrayList.contains(element)

    override fun iterator(): MutableIterator<T> = sortedArrayList.iterator()

    override fun add(element: T): Boolean {
        ensureNotNull(element, "The item may not be null")
        val hashCode = element!!.hashCode()

        if (!hashCodes.contains(hashCode)) {
            sortedArrayList.add(element)
            hashCodes.add(hashCode)
            return true
        }

        return false
    }

    override fun remove(element: T): Boolean {
        ensureNotNull(element, "The item may not be null")
        val hashCode = element!!.hashCode()

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
        if (!elements.isEmpty()) {
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

    override fun toString(): String = sortedArrayList.toString()

    override fun hashCode(): Int {
        val prime = 31
        var result = 1
        result = prime * result + hashCodes.hashCode()
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
        return hashCodes == another.hashCodes
    }

}
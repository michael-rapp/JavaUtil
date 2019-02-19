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

import java.util.*

/**
 * An extension of the class [ArrayList] that automatically keeps its items in a sorted order,
 * whenever new items are added. Such a list does not allow to add items at specific indices.
 * Therefore an [UnsupportedOperationException] is thrown by the [SortedArrayList.add] and
 * [SortedArrayList.addAll] methods that allow to specify an index.
 *
 * @param T The type of the items, which are contained by the list or null, if the item's
 * implementation of the interface [Comparable] is used instead
 * @property initialCapacity The initial capacity of the list
 * @property comparator The comparator that is used to sort the list's items
 * @author Michael Rapp
 * @since 1.1.0
 */
open class SortedArrayList<T>(private val initialCapacity: Int,
                              private var comparator: Comparator<in T>?) :
        ArrayList<T>(initialCapacity) {

    constructor() : this(0, null)

    constructor(items: Collection<T>) : this(0, null) {
        addAll(items)
    }

    constructor(initialCapacity: Int) : this(initialCapacity, null)

    constructor(comparator: Comparator<in T>?) : this(0, comparator)

    constructor(items: Collection<T>, comparator: Comparator<in T>?) : this(0, comparator) {
        addAll(items)
    }

    fun comparator(): Comparator<in T>? = comparator

    override fun add(element: T): Boolean {
        var index = Collections.binarySearch(this, element, comparator)

        if (index < 0) {
            index = index.inv()
        }

        super.add(index, element)
        return true
    }

    override fun add(index: Int, element: T) {
        throw UnsupportedOperationException()
    }

    override fun addAll(elements: Collection<T>): Boolean {
        if (elements.isNotEmpty()) {
            elements.forEach { add(it) }
            return true
        }

        return false
    }

    override fun addAll(index: Int, elements: Collection<T>): Boolean {
        throw UnsupportedOperationException()
    }

    override fun indexOf(element: T): Int {
        val index = lastIndexOf(element)

        if (index != -1) {
            for (i in index - 1 downTo 0) {
                if (get(i) != element) {
                    return i + 1
                }
            }
        }

        return index
    }

    override fun lastIndexOf(element: T): Int {
        val index = Collections.binarySearch(this, element, comparator)
        return if (index >= 0) index else -1
    }

    override fun contains(element: T) = lastIndexOf(element) != -1

    override fun remove(element: T): Boolean {
        val index = indexOf(element)

        if (index != -1) {
            removeAt(index)
            return true
        }

        return false
    }

    override fun sort(c: Comparator<in T>?) {
        this.comparator = c
        super.sort(c)
    }

}

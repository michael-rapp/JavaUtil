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
package de.mrapp.util.datastructure

import de.mrapp.util.Condition
import org.omg.CORBA.Object
import java.util.*

/**
 * A list that is meant to be used for managing event listeners. The list ensures, that no
 * duplicates or null elements can be added. For comparing listeners either the [Object.equals]
 * method or the identity operator (==) can be used. Furthermore, this class is thread-safe and
 * no [ConcurrentModificationException]s are thrown when adding or removing listeners while
 * iterating the list.
 *
 * This is list is neither serializable, nor does it override the [Object.equals] or
 * [Object.hashCode] method.
 *
 * @author Michael Rapp
 * @since 1.2.0
 */
class ListenerList<T>(val compareMethod: CompareMethod) : Iterable<T> {

    /**
     * Contains all possible methods for comparing listeners with each other.
     */
    enum class CompareMethod {

        /**
         * If listeners should be compared using the [Object.equals] method.
         */
        EQUALITY,

        /**
         * If listeners should be compared using the identity (==) operator.
         */
        IDENTITY

    }

    private val lock = Any()

    private var listeners: List<T> = emptyList()

    private fun contains(iterable: Iterable<T>, listener: T): Boolean {
        return iterable.find { equals(it, listener) } != null
    }

    private fun equals(listener1: T?, listener2: T?): Boolean {
        return when (listener1) {
            null -> listener2 == null
            else -> listener2 != null && when (compareMethod) {
                CompareMethod.EQUALITY -> listener1 == listener2
                CompareMethod.IDENTITY -> listener1 === listener2
            }
        }
    }

    constructor() : this(CompareMethod.EQUALITY)

    /**
     * Returns, whether the list is empty, or not.
     */
    fun isEmpty(): Boolean {
        synchronized(lock) {
            return listeners.isEmpty()
        }
    }

    /**
     * Returns the number of listeners that are contained by the list.
     */
    fun size(): Int {
        synchronized(lock) {
            return listeners.size
        }
    }

    /**
     * Adds a new [listener] to the list.
     *
     * @return True, if the listener has been added, false, if it was already contained by the list
     */
    fun add(listener: T): Boolean {
        synchronized(lock) {
            if (!contains(listeners, listener)) {
                val newList = LinkedList(listeners)
                newList.add(listener)
                listeners = newList
                return true
            }
        }

        return false
    }

    /**
     * Adds all listeners that are contained by a specific [iterable] to the list.
     */
    fun addAll(iterable: Iterable<T>) {
        synchronized(lock) {
            var newList: MutableList<T>? = null

            for (listener in iterable) {
                Condition.ensureNotNull(listener, "The listener may not be null")
                val contains = if (newList == null) contains(listeners, listener)
                else contains(newList, listener)

                if (!contains) {
                    if (newList == null) {
                        newList = LinkedList(listeners)
                    }

                    newList.add(listener)
                }
            }

            if (newList != null) {
                listeners = newList
            }
        }
    }

    /**
     * Removes a specific [listener] from the list.
     *
     * @return True, if the listener has been removed, or false, if the listener is not contained by
     * the list
     */
    fun remove(listener: T): Boolean {
        synchronized(lock) {
            if (contains(listeners, listener)) {
                val newList = LinkedList<T>()
                listeners.filter { !equals(it, listener) }.forEach { newList.add(it) }
                listeners = newList
                return true
            }

            return false
        }
    }

    /**
     * Removes all listeners that are contained by a specific [iterable] from the list.
     */
    fun removeAll(iterable: Iterable<T>) {
        synchronized(lock) {
            var newList: MutableList<T>? = null

            for (listener in listeners) {
                if (!contains(iterable, listener)) {
                    if (newList == null) {
                        newList = LinkedList()
                    }

                    newList.add(listener)
                }
            }

            if (newList != null) {
                listeners = newList
            }
        }
    }

    /**
     * Removes all listeners from the list.
     */
    fun clear() {
        synchronized(lock) {
            this.listeners = emptyList()
        }
    }

    /**
     * Returns a collection that contains all listeners, which are currently contained by the
     * list. The returned collection is a snapshot of the current state and is unmodifiable.
     */
    fun getAll(): Collection<T> {
        synchronized(lock) {
            return if (isEmpty()) emptyList()
            else Collections.unmodifiableCollection(LinkedList(this.listeners))
        }
    }

    override fun iterator(): Iterator<T> {
        synchronized(lock) {
            return listeners.iterator()
        }
    }

}

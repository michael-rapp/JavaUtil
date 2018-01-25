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
package de.mrapp.util.datastructure;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static de.mrapp.util.Condition.ensureNotNull;

/**
 * @author Michael Rapp
 * @since 1.2.0
 */
public class ListenerList<T> implements Iterable<T> {

    public enum CompareMethod {

        /**
         * If listeners should be compared using the method {@link Object#equals(Object)}.
         */
        EQUALITY,

        /**
         * If listeners should be compared using the identity (==) operator.
         */
        IDENTITY

    }

    private final Object lock = new Object();

    private final CompareMethod compareMethod;

    private List<T> listeners;

    private boolean contains(@NotNull final Iterable<? extends T> iterable, @NotNull final T listener) {
        for (T t : iterable) {
            if (equals(t, listener)) {
                return true;
            }
        }

        return false;
    }

    private boolean equals(@Nullable final T listener1, @Nullable final T listener2) {
        if (listener1 == null) {
            return listener2 == null;
        } else
            return listener2 != null &&
                    (compareMethod == CompareMethod.EQUALITY ? listener1.equals(listener2) : listener1 == listener2);
    }

    public ListenerList() {
        this(CompareMethod.EQUALITY);
    }

    public ListenerList(@NotNull final CompareMethod compareMethod) {
        ensureNotNull(compareMethod, "The compare method may not be null");
        this.compareMethod = compareMethod;
        clear();
    }

    public final CompareMethod getCompareMethod() {
        return compareMethod;
    }

    public final boolean isEmpty() {
        synchronized (lock) {
            return listeners.isEmpty();
        }
    }

    public final int getSize() {
        synchronized (lock) {
            return listeners.size();
        }
    }

    public final boolean add(@NotNull final T listener) {
        ensureNotNull(listener, "The listener may not be null");

        synchronized (lock) {
            if (!contains(this.listeners, listener)) {
                List<T> newList = new LinkedList<>(this.listeners);
                newList.add(listener);
                this.listeners = newList;
                return true;
            }

            return false;
        }
    }

    public final void addAll(@NotNull final Iterable<? extends T> iterable) {
        ensureNotNull(iterable, "The iterable may not be null");

        synchronized (lock) {
            List<T> newList = null;

            for (T listener : iterable) {
                ensureNotNull(listener, "The listener may not be null");

                if (newList == null ? !contains(this.listeners, listener) : !contains(newList, listener)) {
                    if (newList == null) {
                        newList = new LinkedList<>(this.listeners);
                    }

                    newList.add(listener);
                }
            }

            if (newList != null) {
                this.listeners = newList;
            }
        }
    }

    public final boolean remove(@NotNull final T listener) {
        ensureNotNull(listener, "The listener may not be null");

        synchronized (lock) {
            if (contains(this.listeners, listener)) {
                List<T> newList = new LinkedList<>();
                this.listeners.stream().filter(x -> !equals(x, listener)).forEach(newList::add);
                this.listeners = newList;
                return true;
            }

            return false;
        }
    }

    public final void removeAll(@NotNull final Iterable<? extends T> iterable) {
        ensureNotNull(iterable, "The iterable may not be null");

        synchronized (lock) {
            List<T> newList = null;

            for (T listener : this.listeners) {
                if (!contains(iterable, listener)) {
                    if (newList == null) {
                        newList = new LinkedList<>();
                    }

                    newList.add(listener);
                }
            }

            if (newList != null) {
                this.listeners = newList;
            }
        }
    }

    public final void clear() {
        synchronized (lock) {
            this.listeners = Collections.emptyList();
        }
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return listeners.iterator();
    }

}
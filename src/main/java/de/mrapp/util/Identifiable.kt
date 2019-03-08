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
 * Defines the interface, a class that is associated with an unique id, must implement.
 *
 * @param T The type of the id
 * @author Michael Rapp
 * @since 2.2.0
 */
interface Identifiable<T> {

    /**
     * The id, the instance is associated with.
     */
    val id: T?

}

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
 * An utility class that provides methods that allow to handle texts.
 *
 * @author Michael Rapp
 * @since 2.2.0
 */
object TextUtil {

    /**
     * Returns whether a specific [text] is null or empty.
     */
    fun isEmpty(text: CharSequence?): Boolean = text?.isEmpty() ?: true

    /**
     * Returns whether a specific [text] is neither null or empty.
     */
    fun isNotEmpty(text: CharSequence?): Boolean = !isEmpty(text)

    /**
     * Returns whether a specific [text] is null, empty or contains only whitespace.
     */
    fun hasNoText(text: CharSequence?): Boolean =
            text?.let { it.isEmpty() || it.matches("\\s+".toRegex())} ?: true

    /**
     * Returns whether a specific [text] is neither null, empty or contains only whitespace.
     */
    fun hasText(text: CharSequence?): Boolean = !hasNoText(text)

}

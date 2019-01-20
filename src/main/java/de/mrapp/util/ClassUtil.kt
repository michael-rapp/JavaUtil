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
 * An utility class, which provides methods for handling classes.
 *
 * @author Michael Rapp
 * @since 1.0.0
 */
object ClassUtil {

    /**
     * Returns a truncated version of a specific class' full qualified name. For example, if the
     * full qualified name of the class is 'com.xyz.Abc', the result will be 'c.x.Abc'.
     *
     * @param clazz The class, whose full qualified name should be turned into a truncated version
     * @return The truncated version of the given class' full qualified name
     */
    fun getTruncatedName(clazz: Class<*>): String {
        ensureNotNull(clazz, "The class may not be null")
        val fullQualifiedName = clazz.name
        val qualifiers = fullQualifiedName.split("\\.".toRegex())
        val stringBuilder = StringBuilder()

        for (i in 0 until qualifiers.size) {
            if (i != qualifiers.size - 1) {
                stringBuilder.append(qualifiers[i].substring(0, 1))
                stringBuilder.append(".")
            } else {
                stringBuilder.append(qualifiers[i])
            }
        }

        return stringBuilder.toString()
    }

}
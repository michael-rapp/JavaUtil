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

import org.mockito.Mockito.*
import java.io.Closeable
import java.io.IOException
import kotlin.test.Test

/**
 * Tests the functionality of the class [StreamUtil].
 *
 * @author Michael Rapp
 */
class StreamUtilTest {

    @Test
    @Throws(IOException::class)
    fun testClose() {
        StreamUtil.close(null)
        val closeable = mock(Closeable::class.java)
        StreamUtil.close(closeable)
        verify(closeable, times(1)).close()
    }

}
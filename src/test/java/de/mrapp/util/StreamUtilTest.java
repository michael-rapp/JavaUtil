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
package de.mrapp.util;

import org.junit.Test;

import java.io.Closeable;
import java.io.IOException;

import static org.mockito.Mockito.*;

/**
 * Tests the functionality of the class {@link StreamUtil}.
 *
 * @author Michael Rapp
 */
public class StreamUtilTest {

    @Test
    public final void testClose() throws IOException {
        StreamUtil.close(null);
        Closeable closeable = mock(Closeable.class);
        StreamUtil.close(closeable);
        verify(closeable, times(1)).close();
    }

}
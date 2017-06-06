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

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

/**
 * An abstract base class for all tests, which access test files.
 *
 * @author Michael Rapp
 */
public abstract class AbstractFileTest {

    /**
     * Returns the file, which can be used for test purposes.
     *
     * @return The file, which can be used for test purposes, as an instance of the class {@link
     * File}. The file may not be null
     */
    @NotNull
    protected final File getTestFile() {
        try {
            URL url = getClass().getClassLoader().getResource("test.txt");

            if (url != null) {
                return Paths.get(url.toURI()).toFile();
            }

            throw new RuntimeException("Failed to retrieve path of file");
        } catch (URISyntaxException e) {
            throw new RuntimeException("Failed to read file", e);
        }
    }

}
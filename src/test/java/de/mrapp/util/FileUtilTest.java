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

import java.io.File;
import java.io.IOException;

import static junit.framework.TestCase.*;

/**
 * Tests the functionality of the class {@link FileUtil}.
 *
 * @author Michael Rapp
 */
public class FileUtilTest extends AbstractFileTest {

    @Test
    public final void testMkdir() {
        File dir = new File(getTestFile().getParent(), "dir");

        try {
            FileUtil.mkdir(dir);
        } catch (IOException e) {
            fail(e.getMessage());
        } finally {
            try {
                FileUtil.delete(dir);
            } catch (IOException e) {
                // No need to handle
            }
        }
    }

    @Test
    public final void testMkdirs() {
        File dir = new File(getTestFile().getParent(), "dir" + File.separator + "subdir");

        try {
            FileUtil.mkdirs(dir);
        } catch (IOException e) {
            fail(e.getMessage());
        } finally {
            try {
                FileUtil.deleteRecursively(dir.getParentFile());
            } catch (IOException e) {
                // No need to handle
            }
        }
    }

    @Test
    public final void testDelete() {
        File dir = new File(getTestFile().getParent(), "dir");
        boolean created = dir.mkdir();
        assertTrue(created);

        try {
            FileUtil.delete(dir);
        } catch (IOException e) {
            fail(e.getMessage());
        }

        assertFalse(dir.exists());
    }

    @Test
    public final void testDeleteRecursively() {
        File dir = new File(getTestFile().getParent(), "dir" + File.separator + "subdir");
        boolean created = dir.mkdirs();
        assertTrue(created);

        try {
            FileUtil.deleteRecursively(dir.getParentFile());
        } catch (IOException e) {
            fail(e.getMessage());
        }

        assertFalse(dir.getParentFile().exists());
    }

    @Test
    public final void testCreateNewFile() {
        File file = new File(getTestFile().getParent(), "file");

        try {
            FileUtil.createNewFile(file);
            assertTrue(file.isFile());

            try {
                FileUtil.createNewFile(file, false);
                fail();
            } catch (IOException e) {
                // No need to handle
            }
        } catch (IOException e) {
            fail(e.getMessage());
        } finally {
            try {
                FileUtil.delete(file);
            } catch (IOException e) {
                // No need to handle
            }
        }
    }

    @Test
    public final void testCreateNewFileOverride() {
        File file = new File(getTestFile().getParent(), "file");

        try {
            FileUtil.createNewFile(file);
            assertTrue(file.isFile());
            FileUtil.createNewFile(file, true);
            assertTrue(file.isFile());
        } catch (IOException e) {
            fail(e.getMessage());
        } finally {
            try {
                FileUtil.delete(file);
            } catch (IOException e) {
                // No need to handle
            }
        }
    }

}
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

import de.mrapp.util.Condition.ensureFileIsDirectory
import de.mrapp.util.Condition.ensureFileIsNoDirectory
import de.mrapp.util.Condition.ensureNotEmpty
import de.mrapp.util.FileUtil.mkdirs
import java.io.*


/**
 * An utility class that provides methods for handling files.
 *
 * @author Michael Rapp
 * @since 2.5.0
 */
object FileUtil {

    /**
     * The symbol(s) that separate a file's name from its suffix as a string.
     */
    const val SUFFIX_SEPARATOR: String = "."

    /**
     * The symbol(s) that separate a file's name from its suffix as a char.
     */
    const val SUFFIX_SEPARATOR_CHAR: Char = '.'

    /**
     * Creates the directory, a specific [file] refers to, if it does not already exist. Optionally,
     * all parent directory are created, if [createParents] is true.
     */
    @Throws(IOException::class)
    private fun mkdir(file: File, createParents: Boolean) {
        val result = if (createParents) file.mkdirs() else file.mkdir()

        if (!result && !file.exists()) {
            throw IOException("Failed to create directory \"$file\"")
        }
    }

    /**
     * Returns the index at which the suffix of a specific [fileName] starts. If [firstOccurrence]
     * is true, the suffix is considered at the first occurrence of the [separator], otherwise it is
     * considered to start at the last occurrence.
     */
    private fun getStartOfSuffix(fileName: String, separator: String, firstOccurrence: Boolean) =
            if (firstOccurrence) fileName.indexOf(separator) else fileName.lastIndexOf(separator)

    /**
     * Creates the directory, a specific [file] refers to. It is assumed, that the parent
     * directories already exist. If the parent directories should be created as well, use the
     * method [mkdirs] instead.
     */
    @Throws(IOException::class)
    fun mkdir(file: File) {
        mkdir(file, false)
    }

    /**
     * Creates the directory, a specific [file] refers to, if it does not already exist. If
     * necessary, the parent directories are created as well. If no parent directories should be
     * created, use the method [mkdir] instead.
     */
    @Throws(IOException::class)
    fun mkdirs(file: File) {
        mkdir(file, true)
    }

    /**
     * Deletes a specific [file]. If a directory and its contents should be deleted, use the method
     * [deleteRecursively] instead.
     */
    @Throws(IOException::class)
    fun delete(file: File) {
        val result = file.delete()

        if (!result && file.exists()) {
            if (file.isDirectory) {
                throw IOException(
                        "Failed to delete directory \"$file\". Make sure that is is empty")
            } else {
                throw IOException("Failed to deleted file \"$file\"")
            }
        }
    }

    /**
     * Deletes a specific [file]. If the file is a directory, all contained files and subdirectories
     * are deleted recursively.
     */
    @Throws(IOException::class)
    fun deleteRecursively(file: File) {
        if (file.isDirectory) {
            file.listFiles()?.forEach { deleteRecursively(it) }
        }

        delete(file)
    }

    /**
     * Returns the suffix of a specific [file] or null, if the file does not have a suffix. If
     * [firstOccurrence] is true, the suffix is considered to start at the first occurrence of the
     * [separator], otherwise it is considered to start at the last occurrence. The [separator] is
     * not included in the returned suffix.
     */
    @JvmOverloads
    fun getSuffix(file: File, separator: String = SUFFIX_SEPARATOR,
                  firstOccurrence: Boolean = true): String? =
            getSuffix(file.name, separator, firstOccurrence)

    /**
     * Returns the suffix of a file with a specific [fileName] or null, if the file does not have a
     * suffix. If [firstOccurrence] is true, the suffix is considered to start at the first
     * occurrence of the [separator], otherwise it is considered to start at the last occurrence.
     * The [separator] is not included in the returned suffix.
     */
    @JvmOverloads
    fun getSuffix(fileName: String, separator: String = SUFFIX_SEPARATOR,
                  firstOccurrence: Boolean = true): String? {
        val index = getStartOfSuffix(fileName, separator, firstOccurrence)
        return if (index != -1) fileName.substring(index + separator.length) else null
    }

    /**
     * Returns a specific [file] with its suffix removed. If the file does not have a suffix, the
     * name is returned as it is. If [firstOccurrence] is true, the suffix is considered to start at
     * the first occurrence of the [separator], otherwise it is considered to start at the last
     * occurrence.
     */
    @JvmOverloads
    fun removeSuffix(file: File, separator: String = SUFFIX_SEPARATOR,
                     firstOccurrence: Boolean = true): File =
            File(file.parent, removeSuffix(file.name, separator, firstOccurrence))

    /**
     * Returns a specific [fileName] with its suffix removed. If the file does not have a suffix,
     * the name is returned as it is. If [firstOccurrence] is true, the suffix is considered to
     * start at the first occurrence of the [separator], otherwise it is considered to start at the
     * last occurrence.
     */
    @JvmOverloads
    fun removeSuffix(fileName: String, separator: String = SUFFIX_SEPARATOR,
                     firstOccurrence: Boolean = true): String {
        val index = getStartOfSuffix(fileName, separator, firstOccurrence)
        return if (index != -1) fileName.substring(0, index) else fileName
    }

    /**
     * Returns a specific [file] with a [suffix] appended. Any suffix the file may already have is
     * retained. It can be removed by using the method [removeSuffix] beforehand. If the given
     * suffix does not already start with the [separator], or the file name does not end with it, it
     * is added automatically.
     */
    @JvmOverloads
    fun appendSuffix(file: File, suffix: String, separator: String = SUFFIX_SEPARATOR): File =
            File(file.parent, appendSuffix(file.name, suffix, separator))

    /**
     * Returns a specific [fileName] with a [suffix] appended. Any suffix the file may already have
     * is retained. It can be removed by using the method [removeSuffix] beforehand. If the given
     * suffix does not already start with the [separator], or the file name does not end with it, it
     * is added automatically.
     */
    @JvmOverloads
    fun appendSuffix(fileName: String, suffix: String,
                     separator: String = SUFFIX_SEPARATOR): String {
        return if (fileName.endsWith(separator) || suffix.startsWith(separator)) {
            fileName + suffix
        } else {
            fileName + separator + suffix
        }
    }

    /**
     * Returns a variant of a specific [file] which is unique within its parent directory. If the
     * name of the given file is not unique, a number will be appended. Optionally, a [suffix] may
     * be added to the name of the given file.
     */
    @JvmOverloads
    fun getUniqueFile(file: File, separator: String = SUFFIX_SEPARATOR,
                      suffix: String? = null): File =
            File(file.parent, getUniqueFileName(File(file.parent), file.name, separator, suffix))


    /**
     * Returns a variant of a specific [fileName] which is unique within a [directory]. If the given
     * file name is not unique, a number will be appended. Optionally, a [suffix] may be added to
     * the given file name.
     */
    @JvmOverloads
    fun getUniqueFileName(directory: File, fileName: String, separator: String = SUFFIX_SEPARATOR,
                          suffix: String? = null): String {
        ensureFileIsDirectory(directory, directory.absolutePath + " is not a directory")
        ensureNotEmpty(fileName, "The file name may not be empty")
        var currentFileName = fileName
        var currentFileNameWithSuffix = suffix?.let {
            appendSuffix(currentFileName, suffix, separator)
        } ?: currentFileName
        var counter = 2
        while (directory.list()?.find { name -> name == currentFileNameWithSuffix } != null) {
            currentFileName = fileName + counter
            currentFileNameWithSuffix = suffix?.let {
                appendSuffix(currentFileName, suffix, separator)
            } ?: currentFileName
            counter++
        }

        return currentFileNameWithSuffix
    }

    /**
     * Creates a new, empty [file]. If [overwrite] is true, the file is overwritten, if it does
     * already exist.
     */
    @JvmOverloads
    @Throws(IOException::class)
    fun create(file: File, overwrite: Boolean = false) {
        val result = file.createNewFile()

        if (!result) {
            if (overwrite) {
                try {
                    delete(file)
                    create(file, false)
                } catch (e: IOException) {
                    throw IOException("Failed to overwrite file \"$file\"")
                }
            } else {
                throw IOException("Failed to create file \"$file\"")
            }
        }
    }

    /**
     * Writes the [data] in a byte array to a specific [destination]. If [overwrite] is true, the
     * file is overwritten if it does already exist.
     */
    @JvmOverloads
    @Throws(IOException::class)
    fun writeTo(data: ByteArray, destination: File, overwrite: Boolean = false) {
        create(destination, overwrite)
        BufferedOutputStream(FileOutputStream(destination)).use { stream ->
            stream.write(data)
            stream.flush()
        }
    }

    /**
     * Copies a [source] file to a specific [destination]. To create the parent directories of the
     * destination, the method [mkdirs] may be used beforehand. If [overwrite] is true, the file is
     * overwritten if it does already exist.
     */
    @JvmOverloads
    @Throws(IOException::class)
    fun copy(source: File, destination: File, overwrite: Boolean = false) {
        ensureFileIsNoDirectory(source, "The source must exist and must not be a directory")
        create(destination, overwrite)
        FileInputStream(source).use { inputStream ->
            FileOutputStream(destination).use { outputStream ->
                val inChannel = inputStream.channel
                val outChannel = outputStream.channel
                inChannel.transferTo(0, inChannel.size(), outChannel)
            }
        }
    }

}

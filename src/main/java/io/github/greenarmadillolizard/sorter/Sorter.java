package io.github.greenarmadillolizard.sorter;

import java.io.File;
import java.nio.file.Path;

/**
 * The user of this interface has control over the determination on how a file should be moved.
 *
 * @author Alexander Krischer
 * @version 0.0.2
 */
public interface Sorter {

    /**
     * Returns a folder path where the file should get moved to, usually dependent on the file extension.
     * @param file file whose destination is going to be determined
     * @return a folder path where the file should get moved to
     */
    Path sortFile(File file);

    /**
     * Returns the extension af a file (i.e., txt for readme.txt). If there is none, it will return an empty string.
     * @param file  file whose extension is going to be determined
     * @return the extension af a file
     */
    default String getFileExtension(File file) {

        if (!file.isFile())
            throw new IllegalArgumentException("The file parameter must be a File.");

        String fileName = file.getName();

        int startIndex = fileName.lastIndexOf('.');

        if (startIndex == -1 || startIndex + 1 == fileName.length())
            return "";

        return fileName.substring(startIndex+1);
    }
}

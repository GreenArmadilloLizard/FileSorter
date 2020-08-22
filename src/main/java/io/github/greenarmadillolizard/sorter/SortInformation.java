package io.github.greenarmadillolizard.sorter;

import java.io.File;

/**
 * A class for holding the information set by the user about which files are going to get sorted
 * and where the folders will be created.
 *
 * @author Alexander Krischer
 * @version 0.0.1
 */
public class SortInformation {
    private File[] targetFiles;
    private File destinationFolder;

    public SortInformation(File[] targetFiles, File destinationFolder) {
        this.targetFiles = targetFiles;
        this.destinationFolder = destinationFolder;
    }

    public File[] getTargetFiles() {
        return targetFiles;
    }

    public void setTargetFiles(File[] targetFiles) {
        this.targetFiles = targetFiles;
    }

    public File getDestinationFolder() {
        return destinationFolder;
    }

    public void setDestinationFolder(File destinationFolder) {
        this.destinationFolder = destinationFolder;
    }
}

package io.github.greenarmadillolizard.sorter;

import java.io.File;

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

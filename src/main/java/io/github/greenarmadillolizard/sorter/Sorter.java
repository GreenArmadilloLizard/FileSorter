package io.github.greenarmadillolizard.sorter;

import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;

public interface Sorter {

    public Path sortFile(File file);

    default String getFileExtension(File file){

        if(!file.isFile())
            throw new IllegalArgumentException("The file parameter must be a File.");

        String fileName = file.getName();

        int startIndex = fileName.lastIndexOf('.');

        if(startIndex == -1 || startIndex + 1 == fileName.length())
            return "";

        return fileName.substring(startIndex+1);
    }
}

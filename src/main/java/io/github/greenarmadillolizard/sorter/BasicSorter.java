package io.github.greenarmadillolizard.sorter;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This Class implements the Sorter interface and sorts all files with the same extension into the same Folder
 * (i.e., readme.txt gets moved to a folder called txt-Files).
 *
 * @author Alexander Krischer
 * @version 0.0.2
 */
@Component
@Qualifier("backup")
public class BasicSorter implements Sorter {

    @Override
    public Path sortFile(File file) {
        return Paths.get(getFileExtension(file) + "-Files");
    }
}

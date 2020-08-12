package io.github.greenarmadillolizard.sorter;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
@Qualifier("backup")
public class BasicSorter implements Sorter {

    @Override
    public Path sortFile(File file) {
        return Paths.get(getFileExtension(file) + "-Dateien");
    }
}

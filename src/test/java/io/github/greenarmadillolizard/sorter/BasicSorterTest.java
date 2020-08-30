package io.github.greenarmadillolizard.sorter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BasicSorter.class})
public class BasicSorterTest {

    @Autowired
    private BasicSorter basicSorter;

    @Test
    public void defaultFolderNames() {
        Path path = basicSorter.sortFile(new File("readme.txt"));
        assertThat(path).isEqualTo(Paths.get("txt-Files"));

        path = basicSorter.sortFile(new File("picture.png"));
        assertThat(path).isEqualTo(Paths.get("png-Files"));
    }

    @Test
    public void noExtensionFolderNames() {
        Path path = basicSorter.sortFile(new File("readme"));
        assertThat(path).isEqualTo(Paths.get("unknown-Files"));
    }
}

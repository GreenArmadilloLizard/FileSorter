package io.github.greenarmadillolizard.sorter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {GroupSorter.class, BasicSorter.class})
public class GroupSorterTest {

    @SpyBean
    private GroupSorter groupSorter;

    @Before
    public void init() throws IOException {
        Map<String, String> testingRules = new HashMap<String, String>();
        testingRules.put("txt", "Text Files");
        testingRules.put("txt2", "Text Files");
        testingRules.put("png", "Image Files");

        doNothing().when(groupSorter).setRules();
        doReturn(testingRules).when(groupSorter).getSortRules();
    }

    @Test
    public void specifiedFolderNames() {
        Path path = groupSorter.sortFile(new File("readme.txt"));

        assertThat(path).isEqualTo(Paths.get("Text Files"));
    }

}

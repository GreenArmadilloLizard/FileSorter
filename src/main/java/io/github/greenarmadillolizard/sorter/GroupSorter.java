package io.github.greenarmadillolizard.sorter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Component
@Qualifier("default")
public class GroupSorter implements Sorter {
    private final String rulePath = "src/main/resources/NamingRules.JSON";

    Map<String, String> sortRules = new HashMap<String, String>();

    @Autowired
    @Qualifier("backup")
    private Sorter backupSorter;

    public GroupSorter() {
        try {
            setRules();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setRules() throws IOException {
        byte[] jsonText = Files.readAllBytes(Paths.get(rulePath));
        ObjectMapper objectMapper = new ObjectMapper();

        sortRules = objectMapper.readValue(jsonText, HashMap.class);
    }

    @Override
    public Path sortFile(File file) {
        String fileExtension = getFileExtension(file);

        String folderName = sortRules.getOrDefault(fileExtension, "");

        if (folderName.isEmpty())
            return backupSorter.sortFile(file);
        else
            return Paths.get(folderName);
    }
}

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

/**
 * This Class implements the Sorter interface and sorts all files depending on the definition in NamingRules.JSON
 * (i.e., readme.txt gets moved to a folder called Documents by default).
 * If no definition is present, the backupSorter is used instead.
 *
 * @author Alexander Krischer
 * @version 0.0.4
 */
@Component
@Qualifier("default")
public class GroupSorter implements Sorter {

    private final String rulePath = "src/main/resources/NamingRules.JSON";

    private Map<String, String> sortRules = new HashMap<String, String>();

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

    protected void setRules() throws IOException {
        byte[] jsonText = Files.readAllBytes(Paths.get(rulePath));
        ObjectMapper objectMapper = new ObjectMapper();

        setSortRules(objectMapper.readValue(jsonText, HashMap.class));
    }

    @Override
    public Path sortFile(File file) {
        String fileExtension = getFileExtension(file);

        String folderName = getSortRules().getOrDefault(fileExtension, "");

        if (folderName.isEmpty())
            return backupSorter.sortFile(file);
        else
            return Paths.get(folderName);
    }

    public Map<String, String> getSortRules() {
        return sortRules;
    }

    public void setSortRules(Map<String, String> sortRules) {
        this.sortRules = sortRules;
    }
}

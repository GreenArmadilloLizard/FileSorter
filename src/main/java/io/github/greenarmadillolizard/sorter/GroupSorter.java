package io.github.greenarmadillolizard.sorter;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
@Qualifier("default")
public class GroupSorter implements Sorter {
    private final String rulePath = "src/main/resources/NamingRules.JSON";

    @Autowired
    @Qualifier("backup")
    private Sorter backupSorter;

    JSONObject rules;

    public GroupSorter(){
        setRules();
    }

    private void setRules(){
        StringBuilder jsonString = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(rulePath));
            for(String line : lines){
                jsonString.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        rules = new JSONObject(jsonString.toString());
    }

    @Override
    public Path sortFile(File file) {
        String folderName = "";
        String fileExtension = getFileExtension(file);

        try{
            folderName = rules.getString(fileExtension);
        }
        catch(JSONException e){
            e.printStackTrace();
        }

        if(folderName.isEmpty())
            return backupSorter.sortFile(file);
        else
            return Paths.get(folderName);
    }
}

package io.github.greenarmadillolizard.sorter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@SpringBootApplication
public class FileSorterApplication {

	private static final Logger log = LoggerFactory.getLogger(FileSorterApplication.class);

	public static void main(String... args) {
		new SpringApplicationBuilder(FileSorterApplication.class).headless(false).run(args);
	}

	@Bean
	public CommandLineRunner run(@Qualifier("default") Sorter sorter){
		return (args) -> {

			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				log.info("Theme was set.");
			} catch (Exception e) {
				e.printStackTrace();
			}

			SortInformation sortInfo = beginDialog();

			sortFiles(sortInfo, sorter);
		};
	}

	public SortInformation beginDialog(){
		JOptionPane.showMessageDialog(null, "Wähle die Dateien aus, die sortiert werden sollen.");
		File[] selectedFiles = askForFiles();

		log.info(selectedFiles.length + " files selected.");

		if(selectedFiles.length == 0)
			System.exit(1);

		JOptionPane.showMessageDialog(null, "Wähle nun den Zielordner aus.");
		File folder = askForFolder();

		if(folder == null)
			System.exit(1);

		return new SortInformation(selectedFiles, folder);
	}

	public void sortFiles(SortInformation sortInfo, Sorter sorter){
		for(File file : sortInfo.getTargetFiles()){
			Path newPath = sortInfo.getDestinationFolder().toPath().resolve(sorter.sortFile(file));

			try {
				Files.createDirectory(newPath);
			} catch (IOException e) {
				if(!(e instanceof FileAlreadyExistsException))
					e.printStackTrace();
			}

			try {
				Files.copy(file.toPath(), newPath.resolve(file.getName()), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private File askForFolder() {
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Wähle den Zielort");
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fc.showDialog(null,null);

		return fc.getSelectedFile();
	}

	private File[] askForFiles(){
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Wähle die Dateien");
		fc.setMultiSelectionEnabled(true);
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.showDialog(null,null);

		return fc.getSelectedFiles();
	}
}

package com.circuitrcay.push.commands;

import com.circuitrcay.push.parser.Command;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Directory extends Command {
    public Directory() {
        this.name = "directory";
        this.aliases = new String[]{"dir", "ls"};
        this.description = "Gets the current directory.";
    }

    @Override
    public void execute(String[] args) {
        System.out.println("Files in " + System.getProperty("user.dir"));

        File folder = new File(System.getProperty("user.dir"));
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                System.out.println(file.getName());
            } else if (file.isDirectory()) {
                System.out.println(file.getName()+"/");
            }
        }
    }
}

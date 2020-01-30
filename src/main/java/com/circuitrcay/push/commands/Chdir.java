package com.circuitrcay.push.commands;

import com.circuitrcay.push.parser.Command;

import java.io.File;

public class Chdir extends Command {
    public Chdir() {
        this.name = "cd";
        this.description = "Changes the directory.";
    }

    @Override
    public void execute(String[] args) {
        boolean result = false;  // Boolean indicating whether directory was set
        File directory;       // Desired current working directory

        if(args[0] == null) {
            return;
        } else {
            directory = new File(args[0]).getAbsoluteFile();
            if (directory.exists()) {
                result = (System.setProperty("user.dir", directory.getAbsolutePath()) != null);
            }
        }
    }
}

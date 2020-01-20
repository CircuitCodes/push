package com.circuitrcay.push.commands;

import com.circuitrcay.push.parser.Command;

public class Exit extends Command {

    public Exit() {
        this.name = "exit";
        this.description = "Exits the shell";
        this.aliases = new String[]{"ex", "leave"};
    }

    @Override
    public void execute(String[] args) {
        System.exit(0);
    }
}

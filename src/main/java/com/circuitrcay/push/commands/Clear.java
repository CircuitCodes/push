package com.circuitrcay.push.commands;

import com.circuitrcay.push.parser.Command;

public class Clear extends Command {
    public Clear() {
        this.name = "clear";
        this.description = "Clears the console output";
        this.aliases = new String[]{"cls"};
    }
    @Override
    public void execute(String[] args) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

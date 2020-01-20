package com.circuitrcay.push.parser;

public abstract class Command {
    public String name;

    public String description = "No description";

    public String[] aliases = {};

    public abstract void execute(String[] args);
}

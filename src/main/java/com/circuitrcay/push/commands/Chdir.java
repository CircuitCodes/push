package com.circuitrcay.push.commands;

import com.circuitrcay.push.parser.Command;

import java.io.File;
import java.util.ArrayDeque;
import java.util.Deque;

public class Chdir extends Command {
    public Chdir() {
        this.name = "cd";
        this.description = "Changes the directory.";
    }

    private static final String BACK = "..";
    private static final String SLASH = "/";

    @Override
    public void execute(String[] args) {
        String result = cd(System.getProperty("user.dir"), args[0]);
        System.setProperty("user.dir", result);
    }

    private static String cd(String current, String commands) {
        Deque stack = new ArrayDeque();
        String[] split = current.split(SLASH);
        for (String cmd:split) {
            stack.push(cmd);
        }


        split = commands.split(SLASH);
        for (String cmd:split) {
            if (BACK.equals(cmd)) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(cmd);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            String cmd = (String) stack.pop();
            sb.insert(0,cmd);
            sb.insert(0,SLASH);
        }
        return sb.toString();
    }
}

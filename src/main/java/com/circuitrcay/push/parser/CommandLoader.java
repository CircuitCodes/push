package com.circuitrcay.push.parser;

import com.circuitrcay.push.commands.*;
import com.circuitrcay.push.exceptions.InvalidCommandException;
import org.buildobjects.process.ExternalProcessFailureException;
import org.buildobjects.process.ProcBuilder;
import org.buildobjects.process.StartupException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

public class CommandLoader {
    public static Map<String, Command> commandMap = new HashMap<>();

    public static void init() {
        commandMap.put("version", new Version());
        commandMap.put("exit", new Exit());
        commandMap.put("directory", new Directory());
        commandMap.put("cd", new Chdir());
        commandMap.put("clear", new Clear());
    }

    public static void executor(String input) throws Exception {
        String prefix = "";
        String command = input.substring(prefix.length(), input.length()).split("\\s+")[0];
        if(command.isEmpty()) return;
        String[] tempArgs = {};
        if(input.contains(" ")) {
            tempArgs = input.substring(prefix.length() + command.length() + 1, input.length()).split("\\s+");
        }

        Command result = null;
        String[] args = tempArgs;
        for(Map.Entry<String, Command> entry: commandMap.entrySet()) {
            if(entry.getValue().name.equals(command)) {
                result = entry.getValue();
                break;
            }
            if (Arrays.stream(entry.getValue().aliases).anyMatch(alias -> alias.equalsIgnoreCase(command))) {
                result = entry.getValue();
                break;
            }
        }
        if(result == null) {
            try {
                String output = ProcBuilder.run(command, args);
                System.out.println(output);
            } catch (StartupException e) {
                System.err.println("Command does not exist, or PUSH cannot find it.");
                return;
            } catch (ExternalProcessFailureException e) {
                System.err.print(e.getStderr());
                return;
            } catch (Exception e) {
                System.err.print(e.getMessage());
                return;
            }
        } else {
            result.execute(args);
        }
    }

}
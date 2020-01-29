package com.circuitrcay.push.parser;

import com.circuitrcay.push.commands.Version;
import com.circuitrcay.push.exceptions.InvalidCommandException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandLoader {
    public static Map<String, Command> commandMap = new HashMap<>();

    public static void init() {
        commandMap.put("version", new Version());
    }

    public static void executor(String input) throws Exception {
        String prefix = ".";
        if(input.startsWith(prefix)) {
            String command = input.substring(prefix.length(), input.length()).split("\\s+")[0];
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
                throw new InvalidCommandException("Unknown command.");
            } else {
                result.execute(args);
            }
        }
    }
}
package com.circuitrcay.push.parser;

import com.circuitrcay.push.commands.Exit;
import com.circuitrcay.push.commands.Version;
import com.circuitrcay.push.exceptions.InvalidCommandException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandLoader {
    public static Map<String, Command> commandMap = new HashMap<>();

    public static void init() {
        commandMap.put("version", new Version());
        commandMap.put("exit", new Exit());
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
        } else {
            try {
                String[] array = input.split("\\s+");


                String s = null;
                Process p = Runtime.getRuntime().exec(array);

                BufferedReader stdInput = new BufferedReader(new
                        InputStreamReader(p.getInputStream()));

                BufferedReader stdError = new BufferedReader(new
                        InputStreamReader(p.getErrorStream()));

                // read the output from the command
                System.out.println("Here is the standard output of the command:\n");
                while ((s = stdInput.readLine()) != null) {
                    System.out.println(s);
                }

                // read any errors from the attempted command
                System.out.println("Here is the standard error of the command (if any):\n");
                while ((s = stdError.readLine()) != null) {
                    System.out.println(s);
                }

            }
            catch (IOException e) {
                throw new Exception(e);
            }
        }
    }
}
package com.circuitrcay.push.parser;

import com.circuitrcay.push.commands.Chdir;
import com.circuitrcay.push.commands.Directory;
import com.circuitrcay.push.commands.Exit;
import com.circuitrcay.push.commands.Version;
import com.circuitrcay.push.exceptions.InvalidCommandException;
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
    }

    public static void executor(String input) throws Exception {
        String prefix = "";
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
            try {
                String output = ProcBuilder.run(command, args);
                System.out.print(output);
            } catch (StartupException e) {
                System.err.print("Command does not exist, or PUSH cannot find it.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            result.execute(args);
        }
    }

}
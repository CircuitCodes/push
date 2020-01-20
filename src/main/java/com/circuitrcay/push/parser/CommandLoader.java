package com.circuitrcay.push.parser;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandLoader {
    public static Map<String, Command> commands = new HashMap<>();

    public static void scan() {
        long start = System.currentTimeMillis();
        try(ScanResult result = new ClassGraph()
            .whitelistPackages("com.circuitrcay.push.commands")
            .scan()) {
            for(ClassInfo classInfo : result.getSubclasses(Command.class.getCanonicalName())) {
                try {
                    Command command = (Command) classInfo.loadClass().getDeclaredConstructor().newInstance();
                    commands.put(command.name, command);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    result.close();
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("Loaded " + commands.size() + " commands in " + String.format("%s", end - start) + "ms");
    }

    public static void run(String name, String[] args) {
        commands.forEach((n, c) -> {
            List<String> aliases = Arrays.asList(c.aliases);
            if (!name.equals(n) || !aliases.contains(name)) {
                return;
            } else {
                c.execute(args);
            }
        });
    }
}
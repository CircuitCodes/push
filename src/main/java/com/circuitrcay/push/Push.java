package com.circuitrcay.push;


import com.circuitrcay.push.parser.CommandLoader;

import java.util.Scanner;

public class Push {
    public static void main(String[] args) {
        CommandLoader.scan();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String command = scanner.next();
            CommandLoader.run(command, args);
        }
    }
}

package com.circuitrcay.push;


import com.circuitrcay.push.parser.CommandLoader;

import java.util.Scanner;

public class Push {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        CommandLoader.init();
        while (true) {
            System.out.print("> ");
            String command = scanner.nextLine();
            CommandLoader.executor(command);
        }
    }
}

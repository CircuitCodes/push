package com.circuitrcay.push.commands;

import com.circuitrcay.push.PushVersion;
import com.circuitrcay.push.parser.Command;

public class Version extends Command {

    public Version() {
        this.name = "version";
        this.description = "PUSH shell version";
    }

    @Override
    public void execute(String[] args) {
        System.out.println(PushVersion.VERSION);
    }
}

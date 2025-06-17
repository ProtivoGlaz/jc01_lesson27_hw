package com.example.jc.controller;

import com.example.jc.controller.impl.CommandProvider;

public class CourseController {
    private static final char DELIM = '\n';
    private final CommandProvider provider = new CommandProvider();

    public String doAction(String request) {
        Command cmd;
        if (request == null || !request.contains(String.valueOf(DELIM))) {
            cmd = provider.getCommand("WRONG_REQUEST");
        } else {
            String name = request.substring(0, request.indexOf(DELIM)).toUpperCase();
            cmd = provider.getCommand(name);
        }

        try {
            return cmd.execute(request);
        } catch (CourseControllerException e) {
            return "ERROR";
        }
    }
}

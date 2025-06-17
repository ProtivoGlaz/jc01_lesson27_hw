package com.example.jc.controller.impl;

import com.example.jc.controller.Command;

public class NoSuchCommand implements Command {
    @Override
    public String execute(String request) {
        String cmd = request.split("\n")[0];
        return "Unknown command: " + cmd;
    }
}

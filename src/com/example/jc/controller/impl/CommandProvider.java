package com.example.jc.controller.impl;

import com.example.jc.controller.Command;
import com.example.jc.controller.CommandName;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private final Map<CommandName, Command> repository = new HashMap<>();

    public CommandProvider() {
        repository.put(CommandName.CREATE_COURSE, new CreateCourseCommand());
        repository.put(CommandName.ADD_PARTICIPANT, new AddParticipantCommand());
        repository.put(CommandName.SHOW_ALL_COURSES, new ShowAllCoursesCommand());

        repository.put(CommandName.WRONG_REQUEST, new NoSuchCommand());
    }

    public Command getCommand(String name) {
        CommandName commandName = null;
        Command command = null;

        try{
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        } catch (IllegalArgumentException | NullPointerException e) {
            command = repository.get(CommandName.WRONG_REQUEST);
        }

        return command;
    }
}

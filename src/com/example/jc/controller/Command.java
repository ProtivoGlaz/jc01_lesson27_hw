package com.example.jc.controller;

public interface Command {
    String execute(String request) throws CourseControllerException;
}

package com.example.jc.controller;

public class CourseControllerException extends Exception {
    private static final long serialVersionUID = 1L;

    public CourseControllerException(){
        super();
    }

    public CourseControllerException(String message){
        super(message);
    }

    public CourseControllerException(Exception e){
        super(e);
    }

    public CourseControllerException(String message, Exception e){
        super(message, e);
    }
}

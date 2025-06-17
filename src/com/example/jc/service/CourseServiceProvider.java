package com.example.jc.service;

public class CourseServiceProvider {
    private static final CourseServiceProvider instance = new CourseServiceProvider();

    private final CourseService service = new com.example.jc.service.impl.CourseService();

    private CourseServiceProvider() {}

    public CourseService getService() {
        return service;
    }

    public static CourseServiceProvider getInstance() {
        return instance;
    }
}

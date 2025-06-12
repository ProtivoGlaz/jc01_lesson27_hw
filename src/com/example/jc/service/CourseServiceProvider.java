package com.example.jc.service;

import com.example.jc.service.impl.CourseService;

public class CourseServiceProvider {
    private static final CourseServiceProvider instance = new CourseServiceProvider();

    private final Service service = new CourseService();

    private CourseServiceProvider() {}

    public Service getService() {
        return service;
    }

    public static CourseServiceProvider getInstance() {
        return instance;
    }
}

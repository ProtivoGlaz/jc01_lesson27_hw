package com.example.jc.controller.impl;

import com.example.jc.controller.Command;
import com.example.jc.controller.ControllerException;
import com.example.jc.model.Course;
import com.example.jc.service.CourseServiceProvider;
import com.example.jc.service.Service;
import com.example.jc.service.ServiceException;

public class CreateCourseCommand implements Command {

    private final CourseServiceProvider provider = CourseServiceProvider.getInstance();
    private final Service courseService = provider.getService();

    @Override
    public String execute(String request) throws ControllerException {
        String response;
        try {
            String[] params = request.split("\n");
            if (courseService.courseExists(params[1])) {
                response = "Course already exists: " + params[1];
                return response;
            }

            Course course = new Course(params[1]);
            courseService.createCourse(course);
            response = "Course created successfully:\n" + params[1];

            return response;

        } catch (ServiceException e) {
            throw new ControllerException("Failed to create course: " + e);
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid input: " + e);
        }
        return null;
    }
}

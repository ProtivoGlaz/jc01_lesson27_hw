package com.example.jc.controller.impl;

import com.example.jc.controller.Command;
import com.example.jc.controller.ControllerException;
import com.example.jc.model.Course;
import com.example.jc.service.CourseServiceProvider;
import com.example.jc.service.Service;
import com.example.jc.service.ServiceException;

import java.util.List;

public class ShowAllCoursesCommand implements Command {
    private final Service service = CourseServiceProvider.getInstance().getService();

    @Override
    public String execute(String request) throws ControllerException {
        try {
            List<Course> list = service.getAllCourses();
            if (list.isEmpty()) {
                return "No courses available";
            }
            StringBuilder sb = new StringBuilder();
            for (Course c : list) {
                sb.append(c).append("\n");
            }
            return sb.toString();
        } catch (ServiceException e) {
            throw new ControllerException("Failed to list courses", e);
        }
    }
}

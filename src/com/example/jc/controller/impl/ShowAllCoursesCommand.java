package com.example.jc.controller.impl;

import com.example.jc.controller.Command;
import com.example.jc.controller.CourseControllerException;
import com.example.jc.model.Course;
import com.example.jc.service.CourseServiceProvider;
import com.example.jc.service.CourseService;
import com.example.jc.service.ServiceException;

import java.util.List;

public class ShowAllCoursesCommand implements Command {
    private final CourseService service = CourseServiceProvider.getInstance().getService();

    @Override
    public String execute(String request) throws CourseControllerException {
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
            throw new CourseControllerException("Failed to list courses", e);
        }
    }
}

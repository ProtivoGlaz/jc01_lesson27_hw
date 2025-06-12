package com.example.jc.controller.impl;

import com.example.jc.controller.Command;
import com.example.jc.controller.ControllerException;
import com.example.jc.model.*;
import com.example.jc.service.CourseServiceProvider;
import com.example.jc.service.Service;
import com.example.jc.service.ServiceException;

import java.util.List;
import java.util.Optional;

public class AddParticipantCommand implements Command {
    private final Service service = CourseServiceProvider.getInstance().getService();

    @Override
    public String execute(String request) throws ControllerException {
        String[] params = request.split("\n");
        if (params.length < 4) {
            return "ERROR: insufficient parameters";
        }

        try {
            List<Course> courses = service.getAllCourses();
            Optional<Course> opt = courses.stream()
                    .filter(c -> c.getName().equals(params[1]))
                    .findFirst();
            if (opt.isEmpty()) {
                return "Course not found: " + params[1];
            }
            Course course = opt.get();

            Person person;
            String role = params[2].toUpperCase();
            switch (role) {
                case "STUDENT" -> {
                    if (params.length < 7) {
                        return "ERROR: missing student parameters";
                    }
                    person = new Student(
                            params[3], params[4], params[5],
                            Double.parseDouble(params[6])
                    );
                }
                case "TEACHER" -> {
                    if (params.length < 6) {
                        return "ERROR: missing teacher parameters";
                    }
                    person = new Teacher(params[3], params[4], params[5]);
                }
                case "ADMIN" -> {
                    if (params.length < 6) {
                        return "ERROR: missing admin parameters";
                    }
                    person = new Administrator(params[3], params[4], params[5]);
                }
                default -> throw new ControllerException("Unknown role: " + role);
            }

            boolean added = course.addParticipant(person);
            if (!added) {
                return "Participant with email already exists: " + person.getEmail();
            }
            service.updateCourse(course);
            return role + " added successfully";

        } catch (ServiceException e) {
            throw new ControllerException("Failed to add participant", e);
        } catch (NumberFormatException nfe) {
            return "ERROR: invalid number format for grade";
        }
    }
}

package com.example.jc.service;

import com.example.jc.model.Course;

import java.util.List;

public interface CourseService {
    void createCourse(Course course) throws ServiceException;
    boolean courseExists(String courseName) throws ServiceException;
    List<Course> getAllCourses() throws ServiceException;
    void updateCourse(Course course) throws ServiceException;

}

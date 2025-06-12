package com.example.jc.service.impl;

import com.example.jc.model.Course;
import com.example.jc.repository.DaoException;
import com.example.jc.repository.CourseRepositoryProvider;
import com.example.jc.repository.Repository;
import com.example.jc.service.Service;
import com.example.jc.service.ServiceException;

import java.util.List;

public class CourseService implements Service {
    private final Repository repo = CourseRepositoryProvider.getInstance().getRepository();

    @Override
    public void createCourse(Course course) throws ServiceException {
        try {
            repo.saveCourse(course);
        } catch (DaoException e) {
            throw new ServiceException("Failed to create course: " + course.getName(), e);
        }
    }

    @Override
    public void updateCourse(Course course) throws ServiceException {
        try {
            repo.update(course);
        } catch (DaoException e) {
            throw new ServiceException("Failed to update course: " + course.getName(), e);
        }
    }

    @Override
    public boolean courseExists(String courseName) throws ServiceException {
        try {
            return repo.exists(courseName);
        } catch (DaoException e) {
            throw new ServiceException("Failed to check existence of: " + courseName, e);
        }
    }

    @Override
    public List<Course> getAllCourses() throws ServiceException {
        try {
            return repo.getAllCourses();
        } catch (DaoException e) {
            throw new ServiceException("Failed to fetch all courses", e);
        }
    }
}

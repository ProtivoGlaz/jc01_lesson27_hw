package com.example.jc.service.impl;

import com.example.jc.model.Course;
import com.example.jc.repository.RepositoryException;
import com.example.jc.repository.CourseRepositoryProvider;
import com.example.jc.repository.CourseRepository;
import com.example.jc.service.ServiceException;

import java.util.List;

public class CourseService implements com.example.jc.service.CourseService {
    private final CourseRepository repo = CourseRepositoryProvider.getInstance().getRepository();

    @Override
    public void createCourse(Course course) throws ServiceException {
        try {
            repo.saveCourse(course);
        } catch (RepositoryException e) {
            throw new ServiceException("Failed to create course: " + course.getName(), e);
        }
    }

    @Override
    public void updateCourse(Course course) throws ServiceException {
        try {
            repo.update(course);
        } catch (RepositoryException e) {
            throw new ServiceException("Failed to update course: " + course.getName(), e);
        }
    }

    @Override
    public boolean courseExists(String courseName) throws ServiceException {
        try {
            return repo.exists(courseName);
        } catch (RepositoryException e) {
            throw new ServiceException("Failed to check existence of: " + courseName, e);
        }
    }

    @Override
    public List<Course> getAllCourses() throws ServiceException {
        try {
            return repo.getAllCourses();
        } catch (RepositoryException e) {
            throw new ServiceException("Failed to fetch all courses", e);
        }
    }
}

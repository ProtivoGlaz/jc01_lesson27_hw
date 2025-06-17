package com.example.jc.repository;

import com.example.jc.model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {
    void saveCourse(Course course) throws RepositoryException;
    void update(Course course) throws RepositoryException;
    void delete(String courseName) throws RepositoryException;
    Optional<Course> findByName(String name) throws RepositoryException;
    List<Course> getAllCourses() throws RepositoryException;
    boolean exists(String courseName) throws RepositoryException;
    void obfuscateStudentData() throws RepositoryException;

}

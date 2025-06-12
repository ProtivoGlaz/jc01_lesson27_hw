package com.example.jc.repository;

import com.example.jc.model.Course;

import java.util.List;
import java.util.Optional;

public interface Repository {
    void saveCourse(Course course) throws DaoException;
    void update(Course course) throws DaoException;
    void delete(String courseName) throws DaoException;
    Optional<Course> findByName(String name) throws DaoException;
    List<Course> getAllCourses() throws DaoException;
    boolean exists(String courseName) throws DaoException;
    void obfuscateStudentData() throws DaoException;

}

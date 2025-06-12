package com.example.jc.repository.impl;

import com.example.jc.model.Course;
import com.example.jc.repository.DaoException;
import com.example.jc.repository.Repository;
import com.example.jc.util.CourseFileManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FileCourseRepository implements Repository {
    private final List<Course> cache = new ArrayList<>();
    private final CourseFileManager fm = new CourseFileManager("courses.txt");

    @Override
    public void saveCourse(Course course) throws DaoException {
        if (exists(course.getName())) {
            throw new DaoException("Course exists: " + course.getName());
        }
        cache.add(course);
        fm.writeCourses(cache, false);
    }

    @Override
    public void update(Course course) throws DaoException {
        delete(course.getName());
        cache.add(course);
        fm.writeCourses(cache, false);
    }

    @Override
    public void delete(String courseName) throws DaoException {
        cache.removeIf(c -> c.getName().equals(courseName));
        fm.writeCourses(cache, false);
    }

    @Override
    public Optional<Course> findByName(String name) {
        return cache.stream().filter(c -> c.getName().equals(name)).findFirst();
    }

    @Override
    public List<Course> getAllCourses() {
        return new ArrayList<>(cache);
    }

    @Override
    public boolean exists(String courseName) {
        return cache.stream().anyMatch(c -> c.getName().equals(courseName));
    }

    @Override
    public void obfuscateStudentData() throws DaoException {
        fm.writeCourses(cache, true);
    }
}

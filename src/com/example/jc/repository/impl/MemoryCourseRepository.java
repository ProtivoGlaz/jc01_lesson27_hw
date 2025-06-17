package com.example.jc.repository.impl;

import com.example.jc.model.Course;
import com.example.jc.model.Person;
import com.example.jc.model.Student;
import com.example.jc.repository.RepositoryException;
import com.example.jc.repository.CourseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryCourseRepository implements CourseRepository {
    private final Map<String, Course> storage = new ConcurrentHashMap<>();

    @Override
    public void saveCourse(Course course) throws RepositoryException {
        if (storage.containsKey(course.getName())) {
            throw new RepositoryException("Course exists: " + course.getName());
        }
        storage.put(course.getName(), course);
    }

    @Override
    public void update(Course course) throws RepositoryException {
        if (!storage.containsKey(course.getName())) {
            throw new RepositoryException("No such course: " + course.getName());
        }
        storage.put(course.getName(), course);
    }

    @Override
    public void delete(String courseName) throws RepositoryException {
        storage.remove(courseName);
    }

    @Override
    public List<Course> getAllCourses() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public java.util.Optional<Course> findByName(String name) {
        return java.util.Optional.ofNullable(storage.get(name));
    }

    @Override
    public boolean exists(String courseName) {
        return storage.containsKey(courseName);
    }

    @Override
    public void obfuscateStudentData() {
        for (Course c : storage.values()) {
            List<Person> part = c.getParticipants();
            for (int i = 0; i < part.size(); i++) {
                if (part.get(i) instanceof Student s) {
                    part.set(i, new Student(
                            s.getName(), "", s.getGroup(), s.getAverageGrade()
                    ));
                }
            }
        }
    }
}

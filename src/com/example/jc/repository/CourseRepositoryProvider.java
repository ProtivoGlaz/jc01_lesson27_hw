package com.example.jc.repository;

import com.example.jc.repository.impl.FileCourseRepository;
import com.example.jc.repository.impl.MemoryCourseRepository;

public class CourseRepositoryProvider {
    private static final CourseRepositoryProvider instance = new CourseRepositoryProvider();

    private final Repository repository = new FileCourseRepository();
    //private final Repository repository = new MemoryCourseRepository();

    private CourseRepositoryProvider() {
    }

    public Repository getRepository() {
        return repository;
    }

    public static CourseRepositoryProvider getInstance() {
        return instance;
    }
}

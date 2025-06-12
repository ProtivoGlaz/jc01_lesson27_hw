package com.example.jc.util;

import com.example.jc.model.Course;
import com.example.jc.model.Person;
import com.example.jc.model.Student;
import com.example.jc.repository.DaoException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CourseFileManager {
    private final Path filePath;

    public CourseFileManager(String fileName) {
        try {
            URL url = CourseFileManager.class.getClassLoader().getResource("");
            Path base = (url != null)
                    ? Paths.get(url.toURI())
                    : Paths.get("").toAbsolutePath();
            this.filePath = base.resolve(fileName);

            if (Files.notExists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (URISyntaxException | IOException e) {
            throw new IllegalStateException("Cannot initialize file: " + fileName, e);
        }
    }

    public void writeCourses(List<Course> courses, boolean obfuscateEmails) throws DaoException {
        try (BufferedWriter bw = Files.newBufferedWriter(filePath);
             PrintWriter pw = new PrintWriter(bw)) {
            for (Course course : courses) {
                pw.println("Course: " + course.getName());
                for (Person person : course.getParticipants()) {
                    if (person instanceof Student student) {
                        String email = obfuscateEmails ? "" : student.getEmail();
                        pw.printf("  Name: %s, Group: %s, Grade: %.2f, Email: %s%n",
                                student.getName(),
                                student.getGroup(),
                                student.getAverageGrade(),
                                email);
                    }
                }
                pw.println("-----------------------------------------------------");
            }
        } catch (IOException e) {
            throw new DaoException("Failed to write courses", e);
        }
    }

    public List<String> readCourseLines() throws DaoException {
        try {
            return Files.readAllLines(filePath);
        } catch (IOException e) {
            throw new DaoException("Failed to read courses", e);
        }
    }
}


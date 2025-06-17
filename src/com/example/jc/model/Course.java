package com.example.jc.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class Course {
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    private final String name;
    private final List<Person> participants = new ArrayList<>();

    public Course(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Course name cannot be null or blank");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean addParticipant(Person person) {
        String email = person.getEmail();
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format: " + email);
        }

        if (hasDuplicateEmail(email)) {
            return false;
        }
        return participants.add(person);
    }

    private boolean hasDuplicateEmail(String email) {
        return participants.stream()
                .map(Person::getEmail)
                .anyMatch(email::equals);
    }

    private static boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public void conductLesson() {
        for (Person p : participants) {
            p.performRole();
        }
    }

    public double calculateAverageGrade() {
        int count = 0;
        double sum = 0.0;

        for (Person p : participants) {
            if (p instanceof Student s) {
                sum += s.getAverageGrade();
                count++;
            }
        }

        return count > 0 ? sum / count : 0.0;
    }

    public List<Person> getParticipants() {
        return this.participants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Course course = (Course) o;
        return Objects.equals(name, course.name) &&
                Objects.equals(participants, course.participants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, participants);
    }

    @Override
    public String toString() {
        return String.format("Course{name='%s', participants=%s}\n", name, participants);
    }
}

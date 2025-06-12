package com.example.jc.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Course {
    private final String name;
    private final List<Person> participants = new ArrayList<>();

    public Course(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean addParticipant(Person person) {
        for (Person p : participants) {
            if (p.getEmail().equals(person.getEmail())) return false;
        }
        return participants.add(person);
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
        if (o == null || getClass() != o.getClass()) return false;
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

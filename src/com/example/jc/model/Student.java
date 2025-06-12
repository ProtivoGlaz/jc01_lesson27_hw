package com.example.jc.model;

public class Student extends Person {
    private final String group;
    private final double averageGrade;

    public Student(String name, String email, String group, double averageGrade) {
        super(name, email);
        this.group = group;
        this.averageGrade = averageGrade;
    }

    public String getGroup() {
        return this.group;
    }

    public double getAverageGrade() {
        return this.averageGrade;
    }

    @Override
    public void performRole() {
        study();
    }

    private void study() {
        System.out.println(getName() + " is attending the class.");
    }

    @Override
    public String getRoleDescription() {
        return String.format("Student from group %s with average grade: %.2f", group, averageGrade);
    }

    @Override
    public String toString() {
        return String.format("Student{name='%s', email='%s', group='%s', averageGrade=%.2f}\n",
                getName(), getEmail(), group, averageGrade);
    }
}

package com.example.jc.model;

public class Teacher extends Person {
    private final String subject;

    public Teacher(String name, String email, String subject) {
        super(name, email);
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    @Override
    public void performRole() {
        teach();
    }

    private void teach() {
        System.out.println(getName() + " is explaining " + subject + ".");
    }

    @Override
    public String getRoleDescription() {
        return String.format("Teacher of subject: %s", subject);
    }

    @Override
    public String toString() {
        return String.format("Teacher{name='%s', email='%s', subject='%s'}\n",
                getName(), getEmail(), subject);
    }
}

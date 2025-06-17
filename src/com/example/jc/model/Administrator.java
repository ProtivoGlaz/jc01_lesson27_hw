package com.example.jc.model;


public class Administrator extends Person {
    private final String department;

    public Administrator(String name, String email, String department) {
        super(name, email);
        this.department = department;
    }

    public String getDepartment() {
        return this.department;
    }

    @Override
    public void performRole() {
        organizeProcess();
    }

    private void organizeProcess() {
        System.out.println(getName() + " from " + getDepartment() + " is organizing the classroom.");
    }

    @Override
    public String getRoleDescription() {
        return String.format("Administrator of department: %s", department);
    }

    @Override
    public String toString() {
        return String.format("Administrator{name='%s', email='%s', department='%s'}\n",
                getName(), getEmail(), department);
    }
}

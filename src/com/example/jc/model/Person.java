package com.example.jc.model;

public abstract class Person {
    private final String name;
    private final String email;

    protected Person(String name, String email) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or blank");
        }
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public abstract void performRole();

    public abstract String getRoleDescription();

    @Override
    public String toString() {
        return String.format("%s{name='%s', email='%s'}",
                this.getClass().getSimpleName(),
                name,
                email);
    }
}

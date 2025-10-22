package com.sama.SpringBootWeb1.model;

import org.springframework.stereotype.Component;

@Component
public class Student {

    private int id;
    private String name;
    private String email;
    private boolean active;

    // Constructors
    public Student() {}
    public Student(int id, String name, String email, boolean active) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.active = active;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }


}
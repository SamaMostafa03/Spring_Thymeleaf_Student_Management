package com.sama.Spring_Thymleaf_Student_Management.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String teacher;
    private boolean available;

    @ManyToMany(mappedBy = "assignedCourses")
    private List<Student> students;

    public Course() {
    }

    public Course(String name, String teacher, boolean available, List<Student> students) {
        this.name = name;
        this.teacher = teacher;
        this.available = available;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}

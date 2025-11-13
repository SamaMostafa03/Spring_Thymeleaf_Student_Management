package com.sama.Spring_Thymleaf_Student_Management.model;

import jakarta.persistence.*;

@Table(name = "users")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String password;
    @Column(name = "is_demo", nullable = false)
    private boolean isDemo = false;

    private String role; // "ROLE_ADMIN" or "ROLE_STUDENT"

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public User(String email, String password, boolean isDemo, String role, Student student) {
        this.email = email;
        this.password = password;
        this.isDemo = isDemo;
        this.role = role;
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }
    public boolean isDemo() {
        return isDemo;
    }

    public void setDemo(boolean demo) {
        isDemo = demo;
    }
    public void setStudent(Student student) {
        this.student = student;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}

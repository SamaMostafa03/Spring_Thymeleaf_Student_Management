package com.sama.Spring_Thymleaf_Student_Management.service;

import com.sama.Spring_Thymleaf_Student_Management.model.Student;
import com.sama.Spring_Thymleaf_Student_Management.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepo repo;
    public List<Student> viewAllStudents(){

        return repo.findAllByOrderByIdAsc();
    }

    public void addStudent(Student student) {
        repo.save(student);
    }

    public int getStudentsSize()
    {
        return repo.findAll().size();
    }

    public Student findStudent(int id){ return repo.findById(id).orElse(new Student());}

    public void  deleteStudent(int id) {
        repo.deleteById(id);
    }

    public void updateStudent(Student student) {
        repo.save(student);
    }

    public List<Student> search(String keyword) {
        return repo.findByNameContainingOrEmailContaining(keyword,keyword);
    }
}

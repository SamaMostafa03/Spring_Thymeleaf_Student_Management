package com.sama.SpringBootWeb1.service;

import com.sama.SpringBootWeb1.model.Student;
import com.sama.SpringBootWeb1.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepo repo;

    public List<Student> viewAllStudents(){
        return repo.viewAllStudents();
    }

    public void addStudent(Student student) {
        repo.addStudent(student);
    }

    public int getStudentsSize()
    {
        return repo.getStudentsSize();
    }

}

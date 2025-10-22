package com.sama.SpringBootWeb1.repo;


import com.sama.SpringBootWeb1.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class StudentRepo {

    private List<Student> students = new ArrayList<>(Arrays.asList(
        new Student(1, "Sara Ali", "sara@example.com", true) ,
        new Student(2, "Omar Hany", "omar@example.com", false))
    );

    public List<Student> viewAllStudents(){
        return students;
    }

    public void addStudent(Student student)
    {
        students.add(student);
    }

    public int getStudentsSize()
    {
        return students.size();
    }

}

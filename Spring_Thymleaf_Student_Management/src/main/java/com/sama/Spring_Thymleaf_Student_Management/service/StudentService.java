package com.sama.Spring_Thymleaf_Student_Management.service;

import com.sama.Spring_Thymleaf_Student_Management.model.Course;
import com.sama.Spring_Thymleaf_Student_Management.model.Student;
import com.sama.Spring_Thymleaf_Student_Management.model.User;
import com.sama.Spring_Thymleaf_Student_Management.repo.StudentRepo;
import com.sama.Spring_Thymleaf_Student_Management.repo.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepo repo;
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Student> viewAllStudents(){

        return repo.findAllByOrderByIdAsc();
    }
    public long generateStudentId() {
        int year = java.time.Year.now().getValue();
        long count = repo.count() + 1;
        return Long.parseLong(year + String.format("%04d", count)); // e.g. 20250001
    }
    public void addStudent(Student student) {
        addStudent(student, false);  // default value
    }
    public void addStudent(Student student, boolean isDemo) {
        long generateStudentId = generateStudentId();
        student.setStudentId(generateStudentId);
        repo.save(student);
        User user = new User();
        user.setEmail(student.getEmail());
        user.setPassword(passwordEncoder.encode(String.valueOf(generateStudentId)));
        user.setRole("ROLE_STUDENT");
        user.setDemo(isDemo);
        user.setStudent(student);
        userRepo.save(user);
    }

    public int getStudentsSize()
    {
        return repo.findAll().size();
    }

    public Student findStudent(int id){ return repo.findById(id).orElse(new Student());}

    @Transactional
    public void deleteStudent(int id) {
        Student student = repo.findById(id).orElse(null);
        if (student != null) {
            if (student.getAssignedCourses() != null) {
                student.getAssignedCourses().clear();
            }
            repo.save(student);
            repo.deleteById(id);
        }
    }


    public void updateStudent(Student student) {
        repo.save(student);
    }

    public List<Student> search(String keyword) {
        return repo.findByNameContainingOrEmailContaining(keyword,keyword);
    }

    public void removeCourseFromStudent(int studentId, int courseId) {
        Student student = findStudent(studentId);
        if(student!=null){
            Course course = courseService.findCourse(courseId);
            if(course != null && student.getAssignedCourses().contains(course)){
                student.getAssignedCourses().remove(course);
                updateStudent(student);
            }
        }
    }

    public void addCourseToStudent(int studentId, int courseId) {
        Student student = findStudent(studentId);
        if(student!=null) {
            Course course = courseService.findCourse(courseId);
            if (course != null && !student.getAssignedCourses().contains(course)) {
                student.getAssignedCourses().add(course);
                updateStudent(student);
            }
        }
    }



}

package com.sama.Spring_Thymleaf_Student_Management.service;

import com.sama.Spring_Thymleaf_Student_Management.model.Course;
import com.sama.Spring_Thymleaf_Student_Management.model.Student;
import com.sama.Spring_Thymleaf_Student_Management.repo.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepo courseRepo;


    public List<Course> viewCourses() {
        return courseRepo.findAll();
    }

    public void addCourse(Course course) {
        courseRepo.save(course);
    }

    public void deleteCourse(int id) {
        courseRepo.deleteById(id);
    }

    public Course findCourse(int courseId) {
        return courseRepo.findById(courseId).orElse(new Course());
    }

    public List<Course> search(String keyword) {
        return courseRepo.findByNameContainingOrTeacherContaining(keyword,keyword);
    }

    public void updateCourse(Course course) {

        courseRepo.save(course);
    }
}

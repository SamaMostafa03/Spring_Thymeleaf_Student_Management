package com.sama.Spring_Thymleaf_Student_Management.repo;

import com.sama.Spring_Thymleaf_Student_Management.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepo extends JpaRepository<Course,Integer> {
    List<Course> findByNameContainingOrTeacherContaining(String name, String teacher);

}

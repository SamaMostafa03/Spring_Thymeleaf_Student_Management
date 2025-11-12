package com.sama.Spring_Thymleaf_Student_Management.repo;


import com.sama.Spring_Thymleaf_Student_Management.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student,Integer> {
    List<Student> findByNameContainingOrEmailContaining(String name , String email);
    List<Student> findAllByOrderByIdAsc();

}
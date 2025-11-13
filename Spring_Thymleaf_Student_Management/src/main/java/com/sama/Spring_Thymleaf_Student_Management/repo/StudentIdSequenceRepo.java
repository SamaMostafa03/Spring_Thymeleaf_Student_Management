package com.sama.Spring_Thymleaf_Student_Management.repo;

import com.sama.Spring_Thymleaf_Student_Management.model.StudentIdSequence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentIdSequenceRepo extends JpaRepository<StudentIdSequence, Integer> {
}

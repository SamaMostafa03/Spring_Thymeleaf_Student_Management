package com.sama.Spring_Thymleaf_Student_Management.repo;

import com.sama.Spring_Thymleaf_Student_Management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    Boolean existsByEmail(String email);
    User findByEmail(String email);
}

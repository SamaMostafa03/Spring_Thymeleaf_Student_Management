package com.sama.Spring_Thymleaf_Student_Management.service;

import com.sama.Spring_Thymleaf_Student_Management.model.Student;
import com.sama.Spring_Thymleaf_Student_Management.model.User;
import com.sama.Spring_Thymleaf_Student_Management.model.UserPrincipal;
import com.sama.Spring_Thymleaf_Student_Management.repo.StudentRepo;
import com.sama.Spring_Thymleaf_Student_Management.repo.UserRepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class DemoService {

    private final UserRepo userRepo;

    private final StudentService studentService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;

    public DemoService(UserRepo userRepo, StudentService studentService,
                       PasswordEncoder passwordEncoder, AuthenticationManager authManager) {
        this.userRepo = userRepo;
        this.studentService = studentService;
        this.passwordEncoder = passwordEncoder;
        this.authManager = authManager;
    }
    private static String randomAlphabetic(int length) {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(letters.charAt(random.nextInt(letters.length())));
        }
        return sb.toString();
    }

    public int createAndLoginDemoUser(String role, HttpServletRequest request) {
        String randomEmail = "demo_" + UUID.randomUUID().toString().substring(0, 6) + "@edu.eg";
        String rawPassword = UUID.randomUUID().toString().substring(0, 8);
        int id=1;

        if (role.equals("ROLE_STUDENT")) {
            Student student = new Student();
            String randomName = randomAlphabetic(5) + " " + randomAlphabetic(7);
            student.setName(randomName);
            student.setEmail(randomEmail);
            student.setDepartment("CS");
            student.setGender("Female");
            float gpa = 2.0f + new Random().nextFloat() * 2.0f;
            student.setGpa(Math.round(gpa * 10f) / 10f);
            studentService.addStudent(student,true);
            id = student.getId();
            rawPassword = student.getStudentId().toString();
        }
        else{
            User user = new User();
            user.setEmail(randomEmail);
            user.setPassword(passwordEncoder.encode(rawPassword));
            user.setRole(role);
            user.setDemo(true);
            userRepo.save(user);
        }

        // auto login
        User user = userRepo.findByEmail(randomEmail);
        UserPrincipal principal = new UserPrincipal(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal, null, principal.getAuthorities());

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);

        // Persist the context to session
        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", context);

        System.out.println("Demo login successful for " + randomEmail);

        return id;
    }
}

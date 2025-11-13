package com.sama.Spring_Thymleaf_Student_Management.controller;

import com.sama.Spring_Thymleaf_Student_Management.model.UserPrincipal;
import com.sama.Spring_Thymleaf_Student_Management.service.DemoService;
import com.sama.Spring_Thymleaf_Student_Management.service.UserPrincipleService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;

@Controller
public class UserController {
    @Autowired
    private UserPrincipleService userPrincipleService;
    @Autowired
    private DemoService demoService;

    @GetMapping("/")
    public String home(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/redirectByRole";
        }
        return "redirect:/login";
    }


    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/redirectByRole")
    public String redirectByRole(Authentication authentication) {
        UserPrincipal userPrinciple = (UserPrincipal) authentication.getPrincipal();
        String role = userPrinciple.getUser().getRole();

        if (role.equals("ROLE_ADMIN")) {
            return "redirect:/admin/viewStudents";
        } else if (role.equals("ROLE_STUDENT")) {
            int studentId = userPrinciple.getUser().getStudent().getId();
            return "redirect:/student/" + studentId;
        }
        return "redirect:/login?error";
    }


    @GetMapping("/demo/admin")
    public String demoAdmin(HttpServletRequest request) {
        int id = demoService.createAndLoginDemoUser("ROLE_ADMIN",request);
        return "redirect:/admin/viewStudents";
    }

    @GetMapping("/demo/student")
    public String demoStudent(HttpServletRequest request){
        int id = demoService.createAndLoginDemoUser("ROLE_STUDENT",request);
        return "redirect:/student/" + id;
    }

}

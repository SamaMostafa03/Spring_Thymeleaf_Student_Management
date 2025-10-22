package com.sama.SpringBootWeb1.controller;
import com.sama.SpringBootWeb1.model.Student;
import com.sama.SpringBootWeb1.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping({"/" , "home"})
    public String homePage(Model model) {
        model.addAttribute("pageTitle", "Student List");
        model.addAttribute("students", studentService.viewAllStudents());
        return "index";
    }

    @GetMapping("/addStudent")
    public String addStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "add-student";
    }

    @PostMapping("/addStudent")
    public String saveStudent(@ModelAttribute("student") Student student) {
        student.setId(studentService.getStudentsSize()+ 1);
        studentService.addStudent(student);
        return "redirect:/";
    }

}
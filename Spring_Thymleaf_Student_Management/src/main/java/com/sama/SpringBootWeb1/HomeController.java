package com.sama.SpringBootWeb1;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    private List<Student> students = new ArrayList<>();

    public HomeController() {
        students.add(new Student(1, "Sara Ali", "sara@example.com", true));
        students.add(new Student(2, "Omar Hany", "omar@example.com", false));
    }

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("pageTitle", "Student List");
        model.addAttribute("students", students);
        return "index";
    }

    @GetMapping("/addStudent")
    public String addStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "add-student";
    }

    @PostMapping("/addStudent")
    public String saveStudent(@ModelAttribute("student") Student student) {
        student.setId(students.size() + 1);
        students.add(student);
        return "redirect:/";
    }

    @ModelAttribute("course")
    public String courseName() {
        return "Java";
    }

    @RequestMapping("/calc")
    public String calculator() {
        return "calculator";
    }

    @RequestMapping("/addition")
    public ModelAndView add(@RequestParam("num1") int num1, @RequestParam("num2") int num2, ModelAndView mv) {
        int output = num1 + num2;
        mv.addObject("output",output);
        mv.setViewName("result");
        return mv;
    }

}
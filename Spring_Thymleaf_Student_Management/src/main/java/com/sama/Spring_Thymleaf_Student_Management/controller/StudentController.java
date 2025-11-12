package com.sama.Spring_Thymleaf_Student_Management.controller;

import com.sama.Spring_Thymleaf_Student_Management.model.Course;
import com.sama.Spring_Thymleaf_Student_Management.model.Student;
import com.sama.Spring_Thymleaf_Student_Management.service.CourseService;
import com.sama.Spring_Thymleaf_Student_Management.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;
    @GetMapping({"/" , "home"})
    public String viewAllStudents(Model model){
        model.addAttribute("pageTitle", "Students List");
        model.addAttribute("students",studentService.viewAllStudents());
        return "index";
    }

    @GetMapping("/addStudent")
    public String addStudentForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("courses", courseService.viewCourses());
        return "add-student";
    }

    @PostMapping("/addStudent")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.addStudent(student);
        return "redirect:/";
    }

    @GetMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable int id){
        studentService.deleteStudent(id);
        return "redirect:/";
    }

    @GetMapping("/editStudent/{id}")
    public String editStudentForm(@PathVariable("id") int id, Model model) {
        model.addAttribute("student", studentService.findStudent(id));
        model.addAttribute("pageTitle", "Edit Student");
        List<Course> courses = courseService.viewCourses();
        model.addAttribute("courses", courses);
        return "edit-student";
    }

    @PostMapping("/editStudent")
    public String updateStudent(@ModelAttribute("student") Student student) {
        studentService.updateStudent(student);
        return "redirect:/";
    }

    @GetMapping("/search")
    public String search(@RequestParam String keyword, Model model){
        model.addAttribute("pageTitle", "Search Results");
        model.addAttribute("students" , studentService.search(keyword));
        return "index";
    }

    @GetMapping("/findStudent/{studentId}")
    public Student findStudent(@PathVariable("studentId") int studentId){
        return studentService.findStudent(studentId);
    }
}

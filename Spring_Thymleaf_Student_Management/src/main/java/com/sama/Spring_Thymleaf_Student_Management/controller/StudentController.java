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
    @GetMapping({"/admin/viewStudents"})
    public String viewAllStudents(Model model){
        model.addAttribute("pageTitle", "Students List");
        model.addAttribute("students",studentService.viewAllStudents());
        return "index";
    }

    @GetMapping("/admin/addStudent")
    public String addStudentForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("courses", courseService.viewCourses());
        return "add-student";
    }

    @PostMapping("/admin/addStudent")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.addStudent(student);
        return "redirect:/admin/viewStudents";
    }

    @GetMapping("/admin/deleteStudent/{id}")
    public String deleteStudent(@PathVariable int id){
        studentService.deleteStudent(id);
        return "redirect:/admin/viewStudents";
    }

    @GetMapping("/admin/editStudent/{id}")
    public String editStudentForm(@PathVariable("id") int id, Model model) {
        model.addAttribute("student", studentService.findStudent(id));
        model.addAttribute("pageTitle", "Edit Student");
        List<Course> courses = courseService.viewCourses();
        model.addAttribute("courses", courses);
        return "edit-student";
    }

    @PostMapping("/admin/editStudent")
    public String updateStudent(@ModelAttribute("student") Student student) {
        studentService.updateStudent(student);
        return "redirect:/admin/viewStudents";
    }

    @GetMapping("/admin/search")
    public String search(@RequestParam String keyword, Model model){
        model.addAttribute("pageTitle", "Search Results");
        model.addAttribute("students" , studentService.search(keyword));
        return "index";
    }

    @GetMapping("/admin/findStudent/{studentId}")
    public Student findStudent(@PathVariable("studentId") int studentId){
        return studentService.findStudent(studentId);
    }

    @GetMapping("/student/{id}")
    public String viewStudentProfile(@PathVariable int id, Model model) {
        Student student = studentService.findStudent(id);
        model.addAttribute("student", student);
        return "student-dashboard";
    }

    @GetMapping("/student/{id}/courses")
    public String viewStudentCourses(@PathVariable int id, Model model) {
        Student student = studentService.findStudent(id);
        List<Course> allCourses = courseService.viewCourses();
        List<Course> assignedCourses = student.getAssignedCourses();
        allCourses.removeAll(assignedCourses);
        allCourses.removeIf(course -> !course.isAvailable());
        model.addAttribute("student", student);
        model.addAttribute("allCourses", allCourses);
        return "student-courses-management";
    }

    @GetMapping("/student/{studentId}/removeCourse/{courseId}")
    public String removeStudentCourse(@PathVariable int studentId, @PathVariable int courseId) {
        studentService.removeCourseFromStudent(studentId, courseId);
        return "redirect:/student/" + studentId + "/courses";
    }

   @GetMapping("/student/{studentId}/addCourse/{courseId}")
    public String addStudentCourse(@PathVariable int studentId, @PathVariable int courseId){
        studentService.addCourseToStudent(studentId,courseId);
        return "redirect:/student/" + studentId + "/courses";
   }

}

package com.sama.Spring_Thymleaf_Student_Management.controller;

import com.sama.Spring_Thymleaf_Student_Management.model.Course;
import com.sama.Spring_Thymleaf_Student_Management.model.Student;
import com.sama.Spring_Thymleaf_Student_Management.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("/courses")
    public String viewCourses(Model model){
        model.addAttribute("pageTitle", "Courses List");
        model.addAttribute("courses",courseService.viewCourses());
        return "courses-view";
    }

    @GetMapping("/addCourse")
    public String addCourseForm(Model model) {
        model.addAttribute("course", new Course());
        return "add-course";
    }

    @PostMapping("/addCourse")
    public String saveCourse(@ModelAttribute("course") Course course) {
        courseService.addCourse(course);
        return "redirect:/courses";
    }

    @GetMapping("/deleteCourse/{id}")
    public String deleteCourse(@PathVariable int id){
        courseService.deleteCourse(id);
        return "redirect:/courses";
    }


    @GetMapping("/editCourse/{id}")
    public String editCourseForm(@PathVariable("id") int id, Model model) {
        model.addAttribute("course", courseService.findCourse(id));
        model.addAttribute("pageTitle", "Edit Course");
        return "edit-course";
    }

    @PostMapping("/editCourse")
    public String updateCourse(@ModelAttribute("course") Course course) {
        courseService.updateCourse(course);
        return "redirect:/courses";
    }

    @GetMapping("/searchCourse")
    public String searchCourse(@RequestParam String keyword, Model model){
        model.addAttribute("pageTitle", "Search Results");
        model.addAttribute("courses" , courseService.search(keyword));
        return "courses-view";
    }

    @GetMapping("/findCourse/{courseId}")
    public Course findCourse(@PathVariable("courseId") int courseId){
        return courseService.findCourse(courseId);
    }
}
package com.example.demo.controller;

import com.example.demo.models.Course;
import com.example.demo.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("course", new Course());
        return "create";
    }

    @PostMapping("/create")
    public String create(Course newCourse, Model model){
        courseService.add(newCourse);
        return "redirect:/home";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model){
        Course course = courseService.getById(id);
        if (course != null) {
            model.addAttribute("course", course);
            return "edit";
        }
        return "redirect:/home";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable int id, Course updatedCourse, Model model){
        updatedCourse.setId(id);
        courseService.update(updatedCourse);
        return "redirect:/home";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        courseService.delete(id);
        return "redirect:/home";
    }
}

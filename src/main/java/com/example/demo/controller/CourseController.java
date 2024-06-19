package com.example.demo.controller;

import com.example.demo.models.Course;
import com.example.demo.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/search")
    public String search(@RequestParam(name = "place") String place,
                         @RequestParam(name = "lectureName") String lectureName,
                         Model model){
        List<Course> courses = courseService.searchByPlaceAndLectureName(place, lectureName);
        model.addAttribute("listcourse", courses);
        return "home";
    }
    @GetMapping("/searchByPlace")
    public String searchByPlace(@RequestParam(name = "place") String place, Model model){
        List<Course> courses = courseService.searchByPlace(place);
        model.addAttribute("listcourse", courses);
        return "home";
    }

    @GetMapping("/searchByLectureName")
    public String searchByLectureName(@RequestParam(name = "lectureName") String lectureName, Model model){
        List<Course> courses = courseService.searchByLectureName(lectureName);
        model.addAttribute("listcourse", courses);
        return "home";
    }
}
package com.demo.controller;

import com.demo.model.Student;
import com.demo.model.StudentDto;
import com.demo.service.FileService;
import com.demo.service.StudentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private FileService service;

    @GetMapping
    public String getHome(Model model) {

//        studentService.create(new Student());
        ArrayList<StudentDto> list = studentService.read();
        model.addAttribute("student", list);
        return "/student/students";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("student", new Student());
        return "/student/register";
    }

    @PostMapping("/save")
    @Transactional
    public String save(@RequestParam("file") MultipartFile file, @ModelAttribute @Validated Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "student/register";
        }
        Student student1 = studentService.save(student);
        service.saveFile(file, student1.getSid());
        return "redirect:/student";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable int id) {
        Student student = studentService.findByID(id);
        model.addAttribute("student", student);
        return "/student/register";
    }

    @GetMapping("/profile/{id}")
    public String getProfile(Model model, @PathVariable int id) {
        Student student = studentService.findByID(id);
        model.addAttribute("student", student);
        return "/student/profile";
    }

}

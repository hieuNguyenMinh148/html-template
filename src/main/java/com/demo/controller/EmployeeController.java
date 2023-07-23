package com.demo.controller;

import com.demo.model.Customer;
import com.demo.model.Employee;
import com.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    private String getAll(Model model, @SessionAttribute("username-ss") String username){
        System.out.println(username);
        List<Employee> list = employeeService.getAll();
        model.addAttribute("employee",list);
        return "/employee/list";
    }

//    @GetMapping("/register")
//    private String save(Model model){
//        model.addAttribute("employee",new Employee());
//        return "/employee/register";
//    }

    @PostMapping("save")
    private String create(@ModelAttribute Employee employee){
        employeeService.save(employee);
        return "redirect:/employee";
    }

//    @GetMapping("/update/{id}")
//    private String update(Model model,@PathVariable int id){
//        Optional<Employee> employee = employeeService.findByID(id);
//        model.addAttribute("employee", employee);
//        return "employee/update";
//    }
}

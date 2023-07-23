package com.demo.controller;

import com.demo.model.Customer;
import com.demo.service.CustomerService;
import com.demo.service.dao.CustomerDao;
import com.demo.test.ClassA;
import com.demo.test.ClassB;
import com.demo.test.ClassC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    private ClassB b;

    public void setB(ClassB b) {
        this.b = b;
    }

    private final ClassC c;

    public CustomerController(CustomerDao customerDao, ClassC c) {
        this.customerService = customerDao;
        this.c = c;
    }

    CustomerService dao = new CustomerDao();

    @GetMapping
    public String getHome(Model model) {
//        System.out.println(hieutest);
//        System.out.println(username);
        ArrayList<Customer> customerArrayList = dao.read();
        model.addAttribute("list", customerArrayList);
        return "customer/home";//return theo địa chỉ file
    }

    @GetMapping("/create")
    public String create(Model model) {
        Customer customer = new Customer();
        model.addAttribute("cus", customer);
        return "customer/create";
    }

    @PostMapping("/save")
    public String postCreate(@ModelAttribute Customer customer) {
        dao.create(customer);
        return "redirect:/customer";// return theo link
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable int id) {
        Customer customer = customerService.findByID(id);
        model.addAttribute("customer", customer);
        return "customer/update";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable int id){
        customerService.delete(id);
        String message = "hello customers";
        model.addAttribute("mess", message);
        Customer customer = new Customer(1, "Hieu", "14/08", "123", 1);
        model.addAttribute("cus", customer);
        ArrayList<Customer> customerArrayList = customerService.read();
        model.addAttribute("list", customerArrayList);
        return "customer/home";
    }

    @Autowired
    ClassA classA;
    @GetMapping("/test")
    public String test(Model model) {
        classA.getHello();
        System.out.println(classA);
        ClassA a = classA;
        ClassA a2 = a;
        System.out.println(a);
        System.out.println(a2);
        return "customer/home";//return theo địa chỉ file
    }
}

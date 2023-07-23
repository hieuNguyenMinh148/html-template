package com.demo.controller;

import com.demo.repository.ProductRepository;
import com.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository repository;

//    public String home(){
//        repository.insertProduct(100,11,"jad");
//        repository.insertProduct(100,1455,"jad");
//        repository.insertProduct(100,11123,"jad");
//        return "home";
//    }

}

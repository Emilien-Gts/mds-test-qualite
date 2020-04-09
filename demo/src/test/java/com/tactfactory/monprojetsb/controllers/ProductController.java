package com.tactfactory.monprojetsb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tactfactory.monprojetsb.repository.ProductRepository;

@Controller
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    public ProductController(ProductRepository productRepository) {
        this.repository = productRepository;
    }

}


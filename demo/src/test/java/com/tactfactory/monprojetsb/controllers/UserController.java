package com.tactfactory.monprojetsb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tactfactory.monprojetsb.repository.UserRepository;

@Controller
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository repository;

    public UserController(UserRepository userRepository) {
        this.repository = userRepository;
    }
}


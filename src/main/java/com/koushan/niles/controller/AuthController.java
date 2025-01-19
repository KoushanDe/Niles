package com.koushan.niles.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AuthController {

    @RequestMapping("/")
    public ModelAndView homepage() {
    	return new ModelAndView("home");
    }
    
    @GetMapping("/login")
    public ModelAndView loginForm() {
    	return new ModelAndView("login");
    }
    
    @GetMapping("/home")
    public ModelAndView home() {
    	return new ModelAndView("temphome");
    }

    @GetMapping("/login-error")
    public ModelAndView loginError() {
    	return new ModelAndView("loginerror");
    }

    @GetMapping("/forbidden")
    public ModelAndView forbidden() {
    	return new ModelAndView("forbidden");
    }
}

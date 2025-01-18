package com.koushan.niles.controller;

import com.koushan.niles.dto.UserDto;
import com.koushan.niles.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("user")
    UserDto userDto()
    {
        return new UserDto();
    }
    
    @RequestMapping("/")
    public ModelAndView homepage() {
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("home");
    	return modelAndView;
    }
    
    @GetMapping("/login")
    public ModelAndView loginForm() {
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("login");
    	return modelAndView;
    }   
    
    @GetMapping("/loginadmin")
    public ModelAndView loginFormAdmin() {
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("loginadmin");
    	return modelAndView;
    }

    @GetMapping("/signup")
    public ModelAndView signupForm() {
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("signup");
    	return modelAndView;
    }
    
    @GetMapping("/home")
    public ModelAndView home() {
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("temphome");
    	return modelAndView;
    }

    @PostMapping("/signup")
    public ModelAndView signup(@ModelAttribute("user") UserDto dto) {
        ModelAndView modelAndView = new ModelAndView();
        
    	if(userService.isRegistered(dto)) {
    		modelAndView.setViewName("redirect:/signup?error");
    		return modelAndView;
    	}
        userService.save(dto);
        modelAndView.setViewName("redirect:/signup?success");
		return modelAndView;
    }
}

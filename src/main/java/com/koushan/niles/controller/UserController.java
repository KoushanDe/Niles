package com.koushan.niles.controller;

import com.koushan.niles.dto.UserDto;
import com.koushan.niles.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("user")
    UserDto userDto()
    {
        return new UserDto();
    }

    @PostMapping("/signup")
    public ModelAndView signup(@ModelAttribute("user") UserDto dto) {
        if(userService.isRegistered(dto)) {
            return new ModelAndView("signuperror");
        }
        userService.save(dto);
        return new ModelAndView("signupsuccess.html");
    }

    @GetMapping("/signup")
    public ModelAndView signupForm() {
        return new ModelAndView("signup");
    }
}

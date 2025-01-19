package com.koushan.niles.controller;

import com.koushan.niles.dto.ApparelDto;
import com.koushan.niles.dto.DealDto;
import com.koushan.niles.dto.UserDto;
import com.koushan.niles.entity.Apparel;
import com.koushan.niles.service.AdminService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @ModelAttribute("user")
    UserDto userDto() {
        return new UserDto();
    }

    @ModelAttribute("apparel")
    ApparelDto apparelDto() {
        return new ApparelDto();
    }

    @ModelAttribute("deal")
    DealDto dealDto() {
        return new DealDto();
    }

    @GetMapping("/signup")
    public ModelAndView signupForm() {
        return new ModelAndView("signup");
    }

    @PostMapping("/signup")
    public ModelAndView signup(@ModelAttribute("user") UserDto dto) {
        ModelAndView modelAndView = new ModelAndView();

        if (adminService.isUserRegistered(dto)) {
            modelAndView.setViewName("signuperror");
        } else {
            adminService.saveAdmin(dto);
            modelAndView.setViewName("signupsuccess.html");
        }

        return modelAndView;
    }

    @GetMapping("")
    public ModelAndView adminPanel(@RequestParam(required = false) String queryString, Model model) {
        List<Apparel> apparelList = this.adminService.getFilteredApparel(queryString);
        model.addAttribute("apparelList", apparelList);

        return new ModelAndView("admin");
    }
}
package com.koushan.niles.controller;

import com.koushan.niles.dto.ApparelDto;
import com.koushan.niles.dto.DealDto;
import com.koushan.niles.dto.UserDto;
import com.koushan.niles.entity.Apparel;
import com.koushan.niles.service.ApparelService;
import com.koushan.niles.service.DealService;
import com.koushan.niles.service.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("admin")
public class AdminController {
    private final UserService userService;
    private final ApparelService apparelService;
    private final DealService dealService;

    public AdminController(UserService userService, ApparelService apparelService, DealService dealService) {
        this.userService = userService;
        this.apparelService = apparelService;
        this.dealService = dealService;
    }

    @ModelAttribute("user")
    UserDto userDto() {
        return new UserDto();
    }

    @ModelAttribute("apparel")
    ApparelDto apparelDto()
    {
        return new ApparelDto();
    }

    @ModelAttribute("deal")
    DealDto dealDto()
    {
        return new DealDto();
    }

    @GetMapping("/signup")
    public ModelAndView signupForm() {
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("signup");
    	return modelAndView;
    }

    @PostMapping("/signup")
    public ModelAndView signup(@ModelAttribute("user") UserDto dto) {
        
    	ModelAndView modelAndView = new ModelAndView();
    	if (userService.isRegistered(dto)) {
    		modelAndView.setViewName("redirect:/admin/signup?error");
    		return modelAndView;
    	}
        userService.saveAdmin(dto);
        modelAndView.setViewName("redirect:/admin/signup?success");
        return modelAndView;
    }

    @GetMapping("")
    public ModelAndView adminPanel(@RequestParam(required = false) String queryString, Model model) {
      
    	if (queryString == null || queryString.isEmpty()) {
        	List<Apparel> apparelList = new ArrayList<>();
        	
        	for (Apparel apparel : apparelService.listApparel()) {
        		ApparelDto apparelDto = new ApparelDto (apparel);
        		
        		apparelDto.setDiscountedPrice(dealService.getDiscountedPrice(apparelDto.getId()));
        		apparel.setPrice(apparelDto.getDiscountedPrice());
        		
        		apparelList.add(apparel);
        	}
        	model.addAttribute("apparelList", apparelList);
        }
        else {
        	List<Apparel> apparelList = new ArrayList<>();
        	
        	for (Apparel apparel : apparelService.listApparel()) {
        		if (apparel.getGenericName().toLowerCase(Locale.ROOT).startsWith(queryString.toLowerCase(Locale.ROOT))) {
	        		ApparelDto apparelDto = new ApparelDto (apparel);
	        		
	        		apparelDto.setDiscountedPrice(dealService.getDiscountedPrice(apparelDto.getId()));
	        		apparel.setPrice(apparelDto.getDiscountedPrice());
	        		
	        		apparelList.add(apparel);
        		}
        	}
        	model.addAttribute("apparelList", apparelList);        }
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("admin");
        return modelAndView;
    }

}

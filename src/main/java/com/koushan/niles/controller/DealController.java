package com.koushan.niles.controller;

import com.koushan.niles.dto.DealDto;
import com.koushan.niles.entity.Deal;
import com.koushan.niles.service.DealService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/admin/deal")
public class DealController {
    private final DealService dealService;


    public DealController(DealService dealService) {
        this.dealService = dealService;
    }

    @PostMapping("")
    ModelAndView createDeal(@ModelAttribute("deal") DealDto dto)
    {
        ModelAndView modelAndView = new ModelAndView();
    	if(dto.getValidFrom() == null || dto.getValidFrom().isEmpty())
        {
            dto.setValidFrom(LocalDateTime.now().toString());
        }

        Deal deal = dealService.save(dto);
        if(deal == null)
        {
        	modelAndView.setViewName("redirect:/admin?dealError");
    		return modelAndView;
        }
        modelAndView.setViewName("redirect:/admin?dealSuccess");
		return modelAndView;
    }


}

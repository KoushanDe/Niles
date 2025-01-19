package com.koushan.niles.controller;

import com.koushan.niles.dto.ApparelDto;
import com.koushan.niles.service.*;
import com.koushan.niles.utils.Statistics;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RestController
public class MainController {

    private final ApparelFilterService apparelFilterService;
    private final StatisticsService statisticsService;

    public MainController(ApparelFilterService apparelFilterService, StatisticsService statisticsService) {
        this.apparelFilterService = apparelFilterService;
        this.statisticsService = statisticsService;
    }

    @GetMapping("/user")
    public ModelAndView home(@RequestParam(required = false) String query, Authentication auth, Model model, HttpSession session) {
        User user = (User) auth.getPrincipal();
        String username = user.getUsername();

        Statistics statistics = statisticsService.initializeStatistics(session, username);

        List<ApparelDto> apparelList = apparelFilterService.getFilteredAndSortedApparel(username, query, statistics);

        model.addAttribute("apparelList", apparelList);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
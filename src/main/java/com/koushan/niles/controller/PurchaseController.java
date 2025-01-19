package com.koushan.niles.controller;

import com.koushan.niles.dto.PurchaseDto;
import com.koushan.niles.entity.Purchase;
import com.koushan.niles.service.PurchaseService;
import com.koushan.niles.service.StatisticsService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@Slf4j
public class PurchaseController {
    private final PurchaseService purchaseService;
    private final StatisticsService statisticsService;


    PurchaseController(PurchaseService purchaseService, StatisticsService statisticsService) {
        this.purchaseService = purchaseService;
        this.statisticsService = statisticsService;
    }

    @GetMapping("/user/purchases")
    public ModelAndView purchaseList(@RequestParam(required = false) String q, Authentication auth) {
        String username = ((User) auth.getPrincipal()).getUsername();

        List<PurchaseDto> purchases = purchaseService.getFilteredPurchases(username, q);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("purchases", purchases);
        modelAndView.setViewName("purchases");
        return modelAndView;
    }

    @PostMapping("/user/purchase")
    public ModelAndView purchaseItem(@RequestParam Long apparelId, HttpSession session, Authentication auth) {
        String username = ((User) auth.getPrincipal()).getUsername();

        if (apparelId == null) {
            log.error("Purchase attempt with null apparelId");
            throw new IllegalArgumentException("Apparel ID cannot be null.");
        }

        Purchase purchase = purchaseService.purchase(apparelId, username);
        statisticsService.updateStatistics(session, purchase.getApparel().getPrice());

        return new ModelAndView("redirect:/user/purchases");
    }

}

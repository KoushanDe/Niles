package com.koushan.niles.service.impl;

import com.koushan.niles.entity.Apparel;
import com.koushan.niles.entity.Purchase;
import com.koushan.niles.service.StatisticsService;
import com.koushan.niles.service.UserService;
import com.koushan.niles.utils.Statistics;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class StatisticsServiceImpl implements StatisticsService {
    private final UserService userService;

    public StatisticsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Statistics initializeStatistics(HttpSession session, String username) {
        Statistics statistics = (Statistics) session.getAttribute("statistics");
        if (statistics == null) {
            statistics = new Statistics();
            userService.getPurchases(username)
                    .stream()
                    .map(Purchase::getApparel)
                    .mapToDouble(Apparel::getPrice)
                    .forEach(statistics::update);

            session.setAttribute("statistics", statistics); // Store in session
        }
        return statistics;
    }

    @Override
    public void updateStatistics(HttpSession session, double price) {
        Statistics statistics = (Statistics) session.getAttribute("statistics");
        if (statistics == null) {
            statistics = new Statistics();
            session.setAttribute("statistics", statistics);
        }

        statistics.update(price);
    }


}

package com.koushan.niles.service;

import com.koushan.niles.utils.Statistics;
import jakarta.servlet.http.HttpSession;

public interface StatisticsService {
    Statistics initializeStatistics(HttpSession session, String username);
    void updateStatistics(HttpSession session, double price);
}

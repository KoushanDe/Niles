package com.koushan.niles.service;


import com.koushan.niles.dto.DealDto;
import com.koushan.niles.entity.Deal;


public interface DealService {
    double getDiscountedPrice(Long apparelId);
    Deal save(DealDto dealDto);
}

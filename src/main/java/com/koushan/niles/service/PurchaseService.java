package com.koushan.niles.service;


import com.koushan.niles.dto.PurchaseDto;
import com.koushan.niles.entity.Purchase;

import java.util.List;


public interface PurchaseService {
    Purchase purchase(Long id, String username);
    List<PurchaseDto> getFilteredPurchases(String username, String query);
}

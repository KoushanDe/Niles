package com.koushan.niles.service;


import com.koushan.niles.entity.Purchase;


public interface PurchaseService {
    Purchase purchase(Long id, String username);
}

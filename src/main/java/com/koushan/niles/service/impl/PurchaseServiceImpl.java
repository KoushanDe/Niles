package com.koushan.niles.service.impl;

import com.koushan.niles.entity.Purchase;
import com.koushan.niles.repository.ApparelRepository;
import com.koushan.niles.repository.PurchaseRepository;
import com.koushan.niles.repository.UserRepository;
import com.koushan.niles.service.PurchaseService;
import org.springframework.stereotype.Service;


@Service
public class PurchaseServiceImpl implements PurchaseService {
    private final ApparelRepository apparelRepository;
    private final UserRepository userRepository;
    private final PurchaseRepository purchaseRepository;

    public PurchaseServiceImpl(ApparelRepository apparelRepository, UserRepository userRepository, PurchaseRepository purchaseRepository) {
        this.apparelRepository = apparelRepository;
        this.userRepository = userRepository;
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public Purchase purchase(Long apparelId, String username) {
        Purchase purchase = new Purchase();
        purchase.setApparel(apparelRepository.findById(apparelId).orElse(null));
        purchase.setUser(userRepository.findByEmail(username).orElse(null));
        return purchaseRepository.save(purchase);
    }
}

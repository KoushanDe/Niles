package com.koushan.niles.service.impl;

import com.koushan.niles.dto.PurchaseDto;
import com.koushan.niles.entity.Apparel;
import com.koushan.niles.entity.Purchase;
import com.koushan.niles.entity.User;
import com.koushan.niles.repository.ApparelRepository;
import com.koushan.niles.repository.PurchaseRepository;
import com.koushan.niles.repository.UserRepository;
import com.koushan.niles.service.DealService;
import com.koushan.niles.service.PurchaseService;
import com.koushan.niles.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;


@Service
public class PurchaseServiceImpl implements PurchaseService {
    private final ApparelRepository apparelRepository;
    private final UserRepository userRepository;
    private final PurchaseRepository purchaseRepository;
    private final UserService userService;
    private final DealService dealService;

    public PurchaseServiceImpl(ApparelRepository apparelRepository,
                               UserRepository userRepository,
                               PurchaseRepository purchaseRepository,
                               UserService userService,
                               DealService dealService) {
        this.apparelRepository = apparelRepository;
        this.userRepository = userRepository;
        this.purchaseRepository = purchaseRepository;
        this.userService = userService;
        this.dealService = dealService;
    }

    @Override
    public List<PurchaseDto> getFilteredPurchases(String username, String query) {
        List<PurchaseDto> purchases = userService.getPurchases(username)
                .stream()
                .map(PurchaseDto::new)
                .collect(Collectors.toList());

        if (query != null && !query.isEmpty()) {
            String lowerCaseQuery = query.toLowerCase(Locale.ROOT);
            purchases = purchases.stream()
                    .filter(p -> p.getApparel().getGenericName().toLowerCase(Locale.ROOT).startsWith(lowerCaseQuery))
                    .collect(Collectors.toList());
        }

        return purchases;
    }


    @Override
    public Purchase purchase(Long apparelId, String username) {
        if (apparelId == null) {
            throw new IllegalArgumentException("Apparel ID cannot be null.");
        }

        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty.");
        }

        Apparel apparel = apparelRepository.findById(apparelId)
                .orElseThrow(() -> new IllegalArgumentException("Apparel with ID " + apparelId + " not found."));

        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new IllegalArgumentException("User with email " + username + " not found."));

        Purchase purchase = new Purchase();
        purchase.setApparel(apparel);
        purchase.setUser(user);
        purchase.setBuyPrice(dealService.getDiscountedPrice(apparelId));

        return purchaseRepository.save(purchase);

    }
}

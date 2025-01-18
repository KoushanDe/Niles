package com.koushan.niles.service.impl;

import com.koushan.niles.dto.DealDto;
import com.koushan.niles.entity.Apparel;
import com.koushan.niles.entity.Deal;
import com.koushan.niles.repository.ApparelRepository;
import com.koushan.niles.repository.DealRepository;
import com.koushan.niles.service.DealService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class DealServiceImpl implements DealService {
    private final DealRepository dealRepository;
    private final ApparelRepository apparelRepository;

    public DealServiceImpl(DealRepository dealRepository, ApparelRepository apparelRepository) {
        this.dealRepository = dealRepository;
        this.apparelRepository = apparelRepository;
    }

    @Override
    public double getDiscountedPrice(Long apparelId) {

        double percentage = Optional.ofNullable(dealRepository.findByApparel_Id(apparelId))
                .map(deal -> {
                    if (deal.getValidUpto().isBefore(LocalDateTime.now())) {
                        dealRepository.delete(deal);
                        return 0.0;
                    } else if (deal.getValidFrom().isAfter(LocalDateTime.now())) {
                        return 0.0;
                    }
                    return deal.getPercentage();
                }).orElse(0.0);
        Apparel apparel = apparelRepository.findById(apparelId).orElse(null);
        assert apparel != null;
        return Math.round(apparel.getPrice() * (1 - percentage / 100));
    }

    @Override
    public Deal save(DealDto dealDto) {
        if(dealDto.getValidFrom().isEmpty())
            dealDto.setValidFrom(LocalDateTime.now().toString());       
        Deal deal = new Deal(apparelRepository.findById(dealDto.getApparelId()).orElse(null),
                dealDto.getPercentage(),
                LocalDateTime.parse(dealDto.getValidFrom()),
                LocalDateTime.parse(dealDto.getValidUpto())
        );
        return dealRepository.save(deal);
    }
}

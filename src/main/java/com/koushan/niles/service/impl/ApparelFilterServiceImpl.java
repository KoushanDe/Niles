package com.koushan.niles.service.impl;

import com.koushan.niles.dto.ApparelDto;
import com.koushan.niles.entity.Apparel;
import com.koushan.niles.service.ApparelFilterService;
import com.koushan.niles.service.ApparelService;
import com.koushan.niles.service.DealService;
import com.koushan.niles.utils.Statistics;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class ApparelFilterServiceImpl implements ApparelFilterService {

    private final ApparelService apparelService;
    private final DealService dealService;

    public ApparelFilterServiceImpl(ApparelService apparelService, DealService dealService) {
        this.apparelService = apparelService;
        this.dealService = dealService;
    }

    @Override
    public List<ApparelDto> getFilteredAndSortedApparel(String username, String query, Statistics statistics) {
        List<Apparel> userSpecificApparel = apparelService.listApparel(username);

        List<ApparelDto> apparelDtos = mapToApparelDtos(userSpecificApparel);

        if (query != null && !query.isEmpty()) {
            apparelDtos = filterByQuery(apparelDtos, query);
        }

        return sortAccordingToMetric(apparelDtos, statistics);
    }

    private List<ApparelDto> mapToApparelDtos(List<Apparel> apparels) {
        List<ApparelDto> apparelDtos = new ArrayList<>();
        for (Apparel apparel : apparels) {
            ApparelDto dto = new ApparelDto(apparel);
            dto.setDiscountedPrice(dealService.getDiscountedPrice(apparel.getId()));
            apparelDtos.add(dto);
        }
        return apparelDtos;
    }

    private List<ApparelDto> filterByQuery(List<ApparelDto> apparelDtos, String query) {
        String lowerCaseQuery = query.toLowerCase(Locale.ROOT);
        List<ApparelDto> filteredApparel = new ArrayList<>();
        for (ApparelDto dto : apparelDtos) {
            if (dto.getGenericName().toLowerCase(Locale.ROOT).startsWith(lowerCaseQuery)) {
                filteredApparel.add(dto);
            }
        }
        return filteredApparel;
    }

    private List<ApparelDto> sortAccordingToMetric(List<ApparelDto> apparels, Statistics statistics) {
        apparels.sort((apparel1, apparel2) -> {
            double left = statistics.getMean() - statistics.getStandardDeviation();
            double right = statistics.getMean() + statistics.getStandardDeviation();
            if (apparel1.getPrice() >= left && apparel1.getPrice() <= right && apparel2.getPrice() >= left && apparel2.getPrice() <= right) {
                return Double.compare(apparel1.getPrice(), apparel2.getPrice());
            } else if (apparel1.getPrice() >= left && apparel1.getPrice() <= right) {
                return -1;
            } else if (apparel2.getPrice() >= left && apparel2.getPrice() <= right) {
                return 1;
            } else {
                return Double.compare(Math.abs(apparel1.getPrice() - statistics.getMean()), Math.abs(apparel2.getPrice() - statistics.getMean()));
            }
        });
        return apparels;
    }
}

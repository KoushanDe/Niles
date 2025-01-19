package com.koushan.niles.service;

import com.koushan.niles.dto.ApparelDto;
import com.koushan.niles.utils.Statistics;

import java.util.List;

public interface ApparelFilterService {
    List<ApparelDto> getFilteredAndSortedApparel(String username, String query, Statistics statistics);
}

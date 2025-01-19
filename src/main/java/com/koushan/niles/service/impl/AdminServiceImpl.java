package com.koushan.niles.service.impl;

import com.koushan.niles.dto.ApparelDto;
import com.koushan.niles.dto.UserDto;
import com.koushan.niles.entity.Apparel;
import com.koushan.niles.service.AdminService;
import com.koushan.niles.service.ApparelService;
import com.koushan.niles.service.DealService;
import com.koushan.niles.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class AdminServiceImpl implements AdminService {
    private final UserService userService;
    private final ApparelService apparelService;
    private final DealService dealService;

    public AdminServiceImpl(UserService userService, ApparelService apparelService, DealService dealService) {
        this.userService = userService;
        this.apparelService = apparelService;
        this.dealService = dealService;
    }

    public boolean isUserRegistered(UserDto dto) {
        return userService.isRegistered(dto);
    }

    public void saveAdmin(UserDto dto) {
        userService.saveAdmin(dto);
    }

    public List<Apparel> getFilteredApparel(String queryString) {
        List<Apparel> apparelList = new ArrayList<>();

        for (Apparel apparel : apparelService.listApparel()) {
            if (queryString == null
                    || queryString.isEmpty()
                    || apparel.getGenericName().toLowerCase(Locale.ROOT).startsWith(queryString.toLowerCase(Locale.ROOT))) {

                ApparelDto apparelDto = new ApparelDto(apparel);
                apparelDto.setDiscountedPrice(dealService.getDiscountedPrice(apparelDto.getId()));
                apparel.setPrice(apparelDto.getDiscountedPrice());
                apparelList.add(apparel);
            }
        }

        return apparelList;
    }

}

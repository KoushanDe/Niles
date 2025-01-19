package com.koushan.niles.service;

import com.koushan.niles.dto.UserDto;
import com.koushan.niles.entity.Apparel;

import java.util.List;

public interface AdminService {
    boolean isUserRegistered(UserDto userDto);
    void saveAdmin(UserDto userDto);
    List<Apparel> getFilteredApparel(String queryString);
}

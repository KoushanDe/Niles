package com.koushan.niles.service;

import com.koushan.niles.dto.UserDto;
import com.koushan.niles.entity.Purchase;
import com.koushan.niles.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User save(UserDto dto);
    User saveAdmin(UserDto dto);
    boolean isRegistered(UserDto dto);
    List<Purchase> getPurchases(String email);
}

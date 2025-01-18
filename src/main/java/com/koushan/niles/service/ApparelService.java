package com.koushan.niles.service;

import com.koushan.niles.dto.ApparelDto;
import com.koushan.niles.entity.Apparel;

import java.util.List;

public interface ApparelService {
    Apparel save(ApparelDto dto);
    List<Apparel> listApparel(String email);
    List<Apparel> listApparel();
}

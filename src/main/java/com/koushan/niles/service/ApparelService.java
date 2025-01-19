package com.koushan.niles.service;

import com.koushan.niles.dto.ApparelDto;
import com.koushan.niles.entity.Apparel;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface ApparelService {
    Apparel save(ApparelDto dto);
    List<Apparel> listApparel(String email);
    List<Apparel> listApparel();
    ModelAndView processApparelCreation(ApparelDto dto, MultipartFile file);
}

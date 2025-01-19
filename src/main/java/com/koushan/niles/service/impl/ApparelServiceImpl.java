package com.koushan.niles.service.impl;

import com.cloudinary.utils.ObjectUtils;
import com.koushan.niles.dto.ApparelDto;
import com.koushan.niles.entity.Apparel;

import com.koushan.niles.entity.User;
import com.koushan.niles.model.Preference;
import com.koushan.niles.model.Sex;
import com.koushan.niles.repository.ApparelRepository;
import com.koushan.niles.repository.UserRepository;
import com.koushan.niles.service.ApparelService;
import com.koushan.niles.service.CloudinaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ApparelServiceImpl implements ApparelService {
    private final ApparelRepository repository;
    private final UserRepository userRepository;
    private final CloudinaryService cloudinaryService;


    public ApparelServiceImpl(ApparelRepository repository, UserRepository userRepository, CloudinaryService cloudinaryService) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public ModelAndView processApparelCreation(ApparelDto dto, MultipartFile file) {
        ModelAndView modelAndView = new ModelAndView();

        if (file.isEmpty()) {
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }

        try {
            Map uploadResult = cloudinaryService.upload(file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
            dto.setImageUrl(uploadResult.get("url").toString());

            Apparel apparel = save(dto);
            if (apparel == null) {
                modelAndView.setViewName("redirect:/admin?apparelError");
                return modelAndView;
            }

            log.info("Apparel successfully created with image URL: {}", apparel.getImageUrl());
            modelAndView.setViewName("redirect:/admin?apparelSuccess");
            return modelAndView;

        } catch (IOException e) {
            log.error("Error while uploading file", e);
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }
    }


    @Override
    public Apparel save(ApparelDto dto) {
        Apparel apparel = new Apparel(dto);
        return repository.save(apparel);
    }

    @Override
    public List<Apparel> listApparel(String email) {
        User user = userRepository.findByEmail(email).get();
        System.out.println(user);
        return repository.findAll()
                .stream()
                .filter(apparel -> apparel.getGender() == Sex.OTHERS || apparel.getGender() == user.getSex())
                .filter(apparel -> {
                    if(user.getPreference() == Preference.NEW_ARRIVAL)
                    {
                        return Duration.between(apparel.getCreatedAt().toLocalDate().atStartOfDay(), LocalDate.now().atStartOfDay()).toDays() < 30;
                    }

                    return apparel.getSeason().getMonths().contains(LocalDate.now().getMonthValue());
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Apparel> listApparel() {
        return repository.findAll();
    }
}

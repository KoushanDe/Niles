package com.koushan.niles.service.impl;

import com.koushan.niles.dto.ApparelDto;
import com.koushan.niles.entity.Apparel;

import com.koushan.niles.entity.User;
import com.koushan.niles.model.Preference;
import com.koushan.niles.model.Sex;
import com.koushan.niles.repository.ApparelRepository;
import com.koushan.niles.repository.UserRepository;
import com.koushan.niles.service.ApparelService;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApparelServiceImpl implements ApparelService {
    private final ApparelRepository repository;
    private final UserRepository userRepository;

    public ApparelServiceImpl(ApparelRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
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

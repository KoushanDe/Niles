package com.koushan.niles.controller;

import com.koushan.niles.dto.ApparelDto;
import com.koushan.niles.service.ApparelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/admin/apparel")
public class ApparelController {

    private final ApparelService apparelService;

    @Autowired
    public ApparelController(ApparelService apparelService) {
        this.apparelService = apparelService;
    }

    @PostMapping("")
    public ModelAndView createApparel(@ModelAttribute("apparel") ApparelDto dto, @RequestParam("file") MultipartFile file) {
        return apparelService.processApparelCreation(dto, file);
    }
}

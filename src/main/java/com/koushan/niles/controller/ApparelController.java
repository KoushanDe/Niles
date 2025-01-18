package com.koushan.niles.controller;

import com.cloudinary.utils.ObjectUtils;
import com.koushan.niles.dto.ApparelDto;
import com.koushan.niles.entity.Apparel;
import com.koushan.niles.service.ApparelService;
import com.koushan.niles.service.CloudinaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/admin/apparel")
@Slf4j
public class ApparelController {

    private final ApparelService apparelService;
    
    @Autowired
    CloudinaryService cloudinaryService;
    
    public ApparelController(ApparelService apparelService) {
        this.apparelService = apparelService;
    }

    @PostMapping("")
    ModelAndView createApparel(@ModelAttribute("apparel") ApparelDto dto, @RequestParam("file")MultipartFile file)
    {
    	ModelAndView modelAndView = new ModelAndView();
    	if (file.isEmpty()) {
    		modelAndView.setViewName("redirect:/");
    		return modelAndView;
    	}
        try {
            Map uploadResult = cloudinaryService.upload(file.getBytes(),
                    ObjectUtils.asMap("resourcetype", "auto"));
              
              dto.setImageUrl(uploadResult.get("url").toString());
              
              Apparel apparel = apparelService.save(dto);
              
              if(apparel == null)
              {
            	  modelAndView.setViewName("redirect:/admin?apparelError");
            	  return modelAndView;
              }
            log.info("hi {}", apparel.getImageUrl());

              modelAndView.setViewName("redirect:/admin?apparelSuccess");
        	  return modelAndView;
        } catch (IOException e) {
            log.error("Error while uploading file", e);
            modelAndView.setViewName("redirect:/");
    		return modelAndView;
        }
    }

}

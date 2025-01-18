package com.koushan.niles.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.koushan.niles.service.CloudinaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
@Slf4j
public class CloudinaryServiceImpl implements CloudinaryService {
    @Autowired
	private Cloudinary cloudinary;

    @Override
    public Map upload(Object file, Map options) {
        try {
            return cloudinary.uploader().upload(file, options);
        }
        catch (IOException e) {
            log.error("Error uploading file to cloudinary", e);
            return null;
        }
    }

    @Override
    public String createUrl(String name, int width, int height, String action) {
        return cloudinary.url()
                .transformation(new Transformation()
                .width(width).height(height)
                .border("2px _solid _black").crop(action))
                .imageTag(name);
    }
}

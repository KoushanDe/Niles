package com.koushan.niles.config;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryBean {
    @Value("${cloudinary.cloud-name}") String cloudName;
    @Value("${cloudinary.api-key}") String apiKey;
    @Value("${cloudinary.api-secret}") String apiSecret;

    @Bean
    public Cloudinary cloudinary() {
        Cloudinary cloudinary = new Cloudinary();
        cloudinary.config.cloudName = cloudName;
        cloudinary.config.apiKey = apiKey;
        cloudinary.config.apiSecret = apiSecret;
        return cloudinary;
    }
}

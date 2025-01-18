package com.koushan.niles.service;

import java.util.Map;

public interface CloudinaryService {
    Map upload(Object file, Map options);
    String createUrl(String name, int width, int height, String action);
}

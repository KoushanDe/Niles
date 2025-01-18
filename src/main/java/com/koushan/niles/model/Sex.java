package com.koushan.niles.model;

import lombok.Getter;

@Getter
public enum Sex {
    MALE("Male"),
    FEMALE("Female"),
    OTHERS("Others");

    private final String displayName;

    Sex(String displayName)
    {
        this.displayName = displayName;
    }

}

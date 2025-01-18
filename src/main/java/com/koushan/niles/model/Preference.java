package com.koushan.niles.model;

import lombok.Getter;

@Getter
public enum Preference {
    SEASONAL("Seasonal"),
    NEW_ARRIVAL("New Arrival");

    private final String displayName;

    Preference(String displayName)
    {
        this.displayName = displayName;
    }

}

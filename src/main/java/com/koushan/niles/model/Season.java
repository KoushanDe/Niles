package com.koushan.niles.model;

import lombok.Getter;

import java.util.List;

@Getter
public enum Season {
    SUMMER("Summer", List.of(4, 5, 6, 7, 8, 9)),
    WINTER("Winter", List.of(10, 11, 12, 1, 2, 3));

    private final String displayName;
    private final List<Integer> months;

    Season(String displayName, List<Integer> months)
    {
        this.displayName = displayName;
        this.months = months;
    }

}

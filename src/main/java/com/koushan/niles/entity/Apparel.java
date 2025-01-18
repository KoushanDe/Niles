package com.koushan.niles.entity;

import com.koushan.niles.dto.ApparelDto;
import com.koushan.niles.model.Season;
import com.koushan.niles.model.Sex;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "apparel")
public class Apparel extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Sex gender;

    @Column(nullable = false)
    private String brandName;

    @Column(nullable = false)
    private String genericName;

    @Column(nullable = false)
    private Double price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Season season;
    
    @Column(nullable = false)
    private String imageUrl;
    
    public Apparel() {
    }

    public Apparel(ApparelDto dto) {
        this.brandName = dto.getBrandName();
        this.gender = dto.getGender();
        this.genericName = dto.getGenericName();
        this.price = dto.getPrice();
        this.season = dto.getSeason();
        this.imageUrl = dto.getImageUrl();
    }

}
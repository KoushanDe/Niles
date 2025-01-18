package com.koushan.niles.dto;

import com.koushan.niles.entity.Apparel;
import com.koushan.niles.model.Season;
import com.koushan.niles.model.Sex;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
public class ApparelDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private Long id;
    private Sex gender;
    private String brandName;
    private String genericName;
    private Double price;
    private Double discountedPrice;
    private Season season;
    private String imageUrl;

    public ApparelDto() {
    }

    public ApparelDto(Apparel apparel) {
        this.id = apparel.getId();
        this.brandName = apparel.getBrandName();
        this.genericName = apparel.getGenericName();
        this.gender = apparel.getGender();
        this.price = apparel.getPrice();
        this.season = apparel.getSeason();
        this.imageUrl = apparel.getImageUrl();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApparelDto entity = (ApparelDto) o;
        return Objects.equals(this.gender, entity.gender) &&
                Objects.equals(this.brandName, entity.brandName) &&
                Objects.equals(this.genericName, entity.genericName) &&
                Objects.equals(this.price, entity.price) &&
                Objects.equals(this.season, entity.season) &&
                Objects.equals(this.imageUrl, entity.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gender, brandName, genericName, price, season, imageUrl);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "gender = " + gender + ", " +
                "brandName = " + brandName + ", " +
                "genericName = " + genericName + ", " +
                "price = " + price + ", " +
                "season = " + season + ")";
    }
}
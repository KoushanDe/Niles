package com.koushan.niles.dto;


import com.koushan.niles.entity.Purchase;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
public class PurchaseDto implements Serializable {
    /**
	 * 
	 */
	@Serial
    private static final long serialVersionUID = 1L;
	private final Long id;
    private final ApparelDto apparel;
    private final LocalDate createdAt;
    private final Double buyPrice;

    public PurchaseDto(Long id, ApparelDto apparel, LocalDate createdAt, Double buyPrice) {
        this.id = id;
        this.apparel = apparel;
        this.createdAt = createdAt;
        this.buyPrice = buyPrice;
    }

    public PurchaseDto(Purchase purchase)
    {
        this.id = purchase.getId();
        this.apparel = new ApparelDto(purchase.getApparel());
        this.createdAt = LocalDate.from(purchase.getCreatedAt());
        this.buyPrice = purchase.getBuyPrice();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseDto entity = (PurchaseDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.apparel, entity.apparel) &&
                Objects.equals(this.createdAt, entity.createdAt)&&
                Objects.equals(this.buyPrice, entity.buyPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, apparel, createdAt, buyPrice);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "apparel = " + apparel + ", " +
                "createdAt = " + createdAt + ", " +
                "buyPrice = " + buyPrice + ")";
    }
}

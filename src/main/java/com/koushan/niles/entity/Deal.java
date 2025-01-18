package com.koushan.niles.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "deal")
public class Deal extends BaseEntity {

    @OneToOne
    private Apparel apparel;

    @Column(nullable = false)
    private Double percentage;

    @Column(nullable = false)
    private LocalDateTime validFrom;

    @Column(nullable = false)
    private LocalDateTime validUpto;

    public Deal() {
    }

    public Deal(Apparel apparel, double percentage, LocalDateTime validFrom, LocalDateTime validUpto) {
        this.apparel = apparel;
        this.percentage = percentage;
        this.validFrom = validFrom;
        this.validUpto = validUpto;
    }

}

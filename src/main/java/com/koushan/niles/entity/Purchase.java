package com.koushan.niles.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "purchase")
public class Purchase extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "apparel_id", nullable = false, referencedColumnName = "id")
    private Apparel apparel;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "fk_user"))
    private User user;

    @Column(name = "buy_price", nullable = false)
    private Double buyPrice;
}

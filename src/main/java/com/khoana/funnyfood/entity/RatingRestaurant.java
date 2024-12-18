package com.khoana.funnyfood.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "RatingRestaurant")
public class RatingRestaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "resid")
    private Restaurant restaurant;

    @Column(name = "content")
    private String content;

    @Column(name = "ratepoint")
    private Integer ratePoint;
}

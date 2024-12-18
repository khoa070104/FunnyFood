package com.khoana.funnyfood.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "RatingFood")
public class RatingFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    @ManyToOne
    @JoinColumn(name = "foodid")
    private Food food;

    @Column(name = "content")
    private String content;

    @Column(name = "rate_point")
    private Integer ratePoint;
}

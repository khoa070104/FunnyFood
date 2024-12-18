package com.khoana.funnyfood.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    @ManyToOne
    @JoinColumn(name = "resid")
    private Restaurant restaurant;

    @Column(name = "createdate")
    private LocalDateTime createDate;

    @Column(name = "price")
    private BigDecimal price;
}

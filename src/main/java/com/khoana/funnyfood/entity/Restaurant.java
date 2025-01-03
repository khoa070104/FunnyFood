package com.khoana.funnyfood.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Entity(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "subtitle")
    private String subtitle;

    @Column(name = "descript")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "isfreeship")
    private Boolean isFreeShip;

    @Column(name = "address")
    private String address;

    @Column(name = "opendate")
    private Date openDate;

    @OneToMany(mappedBy = "restaurant")
    private List<RatingRestaurant> ratingRestaurants;

    @OneToMany(mappedBy = "restaurant")
    private List<Order> orders;

    @OneToMany(mappedBy = "restaurant")
    private List<MenuRestaurant> menus;

}

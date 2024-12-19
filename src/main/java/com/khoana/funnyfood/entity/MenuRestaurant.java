package com.khoana.funnyfood.entity;

import jakarta.persistence.*;
import lombok.Data;
import com.khoana.funnyfood.entity.key.MenuRestaurantKey;
import java.time.LocalDateTime;

@Data
@Entity(name = "menurestaurant")
public class MenuRestaurant {
    @EmbeddedId
    private MenuRestaurantKey id;

    @ManyToOne
    @MapsId("cateId")
    @JoinColumn(name = "cateid")
    private Category category;

    @ManyToOne
    @MapsId("resId")
    @JoinColumn(name = "resid")
    private Restaurant restaurant;

    @Column(name = "createdate")
    private LocalDateTime createDate;
}

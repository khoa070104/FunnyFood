package com.khoana.funnyfood.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RestaurantDTO {
    String image;
    String title;
    boolean isFreeShip;
    double ratting;
    String subtitle;
    List<CategoryDTO> categories;

}

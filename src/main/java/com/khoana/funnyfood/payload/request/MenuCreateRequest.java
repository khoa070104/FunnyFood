package com.khoana.funnyfood.payload.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RequestParam;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MenuCreateRequest {
    String title;
    String timeShip;
    String address;
    String dateOpen;
    String image;
    double price;
    int categoryId;

}

package com.khoana.funnyfood.payload.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RequestParam;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RestaurantCreateRequest {
    String title ;
    String subtitle;
    String descript;
    String image;
    boolean isfreeship;
    String address;
    String dateOpen;
}

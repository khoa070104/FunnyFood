package com.khoana.funnyfood.payload;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class DataResponse {
    String message;
    Object data;
    Integer status = 200;
    boolean isScuccess = true;
}

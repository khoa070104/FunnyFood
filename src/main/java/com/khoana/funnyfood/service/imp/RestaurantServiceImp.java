package com.khoana.funnyfood.service.imp;

import com.khoana.funnyfood.payload.request.RestaurantCreateRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


public interface RestaurantServiceImp {
    boolean insertRestaurant(RestaurantCreateRequest restaurant, MultipartFile file);
}

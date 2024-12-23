package com.khoana.funnyfood.service.imp;

import com.khoana.funnyfood.dto.RestaurantDTO;
import com.khoana.funnyfood.payload.request.RestaurantCreateRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface RestaurantServiceImp {
    boolean insertRestaurant(RestaurantCreateRequest restaurant, MultipartFile file);
    List<RestaurantDTO> getHomePageRestaurant();
    RestaurantDTO getDetailRestaurant(int id);
}

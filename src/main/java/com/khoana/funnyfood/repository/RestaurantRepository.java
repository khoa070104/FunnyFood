package com.khoana.funnyfood.repository;

import com.khoana.funnyfood.dto.RestaurantDTO;
import com.khoana.funnyfood.entity.RatingRestaurant;
import com.khoana.funnyfood.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant,Integer> {

}

package com.khoana.funnyfood.repository;

import com.khoana.funnyfood.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant,Integer> {
    // No need to define insertRestaurant - use the built-in save() method
}

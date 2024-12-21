package com.khoana.funnyfood.repository;

import com.khoana.funnyfood.entity.RatingRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRestaurantRepository extends JpaRepository<RatingRestaurant,Integer> {
    List<RatingRestaurant> findByRestaurantId(int id);
}

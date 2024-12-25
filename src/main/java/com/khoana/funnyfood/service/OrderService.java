package com.khoana.funnyfood.service;

import com.khoana.funnyfood.entity.*;
import com.khoana.funnyfood.entity.key.OrderDetailKey;
import com.khoana.funnyfood.payload.request.OrderRequest;
import com.khoana.funnyfood.repository.OrderDetailRepository;
import com.khoana.funnyfood.repository.OrderRepository;
import com.khoana.funnyfood.service.imp.OrderServiceImp;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderService implements OrderServiceImp {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public boolean insertOrder(OrderRequest orderRequest) {
        try {
            Order order = new Order();
            User user = new User();
            Restaurant res = new Restaurant();
            res.setId(orderRequest.getRestaurantId());
            user.setId(orderRequest.getUserId());
            order.setUser(user);
            order.setRestaurant(res);
            orderRepository.save(order);
            List<OrderDetail> items = new ArrayList<>();
            for (int idFood : orderRequest.getFood()) {
                OrderDetail orderDetail = new OrderDetail();
                Food food = new Food();
                OrderDetailKey keyOrderDetail = new OrderDetailKey(order.getId(), idFood);
                food.setId(idFood);
                orderDetail.setId(keyOrderDetail);
                orderDetail.setFood(food);
                items.add(orderDetail);
            }
            orderDetailRepository.saveAll(items);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}

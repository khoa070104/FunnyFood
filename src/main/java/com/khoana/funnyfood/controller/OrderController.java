package com.khoana.funnyfood.controller;

import com.khoana.funnyfood.payload.request.OrderRequest;
import com.khoana.funnyfood.service.imp.OrderServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderServiceImp orderService;

    @PostMapping()
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest){
        return new ResponseEntity<>(orderService.insertOrder(orderRequest), HttpStatus.OK);
    }
}

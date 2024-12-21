package com.khoana.funnyfood.controller;

import com.khoana.funnyfood.payload.DataResponse;
import com.khoana.funnyfood.payload.request.MenuCreateRequest;
import com.khoana.funnyfood.payload.request.RestaurantCreateRequest;
import com.khoana.funnyfood.service.imp.MenuServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("*") // Setup for Client to access this API
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    MenuServiceImp menuService;
    @PostMapping()
    public ResponseEntity<?> createMenu(@RequestParam MultipartFile file,
                                              @RequestParam String title ,
                                              @RequestParam String timeShip,
                                              @RequestParam String address,
                                              @RequestParam String dateOpen,
                                              @RequestParam double price,
                                              @RequestParam int cateId  ){
        DataResponse data = new DataResponse();
        MenuCreateRequest request = MenuCreateRequest.builder()
                .title(title)
                .timeShip(timeShip)
                .address(address)
                .dateOpen(dateOpen)
                .price(price)
                .image(file.getOriginalFilename())
                .categoryId(cateId)
                .build();
        boolean check = menuService.createMenu(request, file);
        data.setData(check);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}

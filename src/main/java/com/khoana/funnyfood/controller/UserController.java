package com.khoana.funnyfood.controller;

import com.khoana.funnyfood.service.imp.UserServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class UserController {
    UserServiceImp userService;

    @GetMapping()
    public ResponseEntity<?> getAllUser(){
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }


}

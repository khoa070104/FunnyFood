package com.khoana.funnyfood.controller;

import com.khoana.funnyfood.dto.UserDTO;
import com.khoana.funnyfood.payload.DataResponse;
import com.khoana.funnyfood.payload.request.SignUpRequest;
import com.khoana.funnyfood.service.LoginService;
import com.khoana.funnyfood.service.imp.LoginServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/login")
public class LoginController {
    @Autowired
    LoginServiceImp loginService;

    @PostMapping()
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO){
        DataResponse dataResponse = new DataResponse();
        boolean check = loginService.checkLogin(userDTO.getUsername(), userDTO.getPassword());
        dataResponse.setData(check);
        dataResponse.setMessage(check ? "Login success" : "Login failed");
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest request){
        DataResponse dataResponse = new DataResponse();
        var check = loginService.signUp(request);
        dataResponse.setData(check);
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }

}

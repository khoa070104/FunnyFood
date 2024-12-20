package com.khoana.funnyfood.controller;

import com.khoana.funnyfood.dto.UserDTO;
import com.khoana.funnyfood.payload.DataResponse;
import com.khoana.funnyfood.payload.request.SignUpRequest;
import com.khoana.funnyfood.service.LoginService;
import com.khoana.funnyfood.service.imp.LoginServiceImp;
import com.khoana.funnyfood.util.JwtUtilHelper;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.util.Base64;

@RestController
@CrossOrigin("*")
@RequestMapping("/login")
public class LoginController {
    @Autowired
    LoginServiceImp loginService;
    @Autowired
    JwtUtilHelper jwtUtilHelper;

    @PostMapping()
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO){
        DataResponse dataResponse = new DataResponse();
//        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Tạo key
//
//        String enryptedKey = Encoders.BASE64.encode(secretKey.getEncoded()); // Mã hoá key
//        System.out.println("Key: " + enryptedKey);
        boolean check = loginService.checkLogin(userDTO.getUsername(), userDTO.getPassword());
        if(check){
            String token = jwtUtilHelper.generateToken(userDTO.getUsername());
            dataResponse.setData(token);
        }
        else{
            dataResponse.setData(null);
            dataResponse.setScuccess(false);
        }
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

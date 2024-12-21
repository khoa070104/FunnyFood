package com.khoana.funnyfood.util;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

// Đưa len bean , ko lquan server .repo thif dung component
@Component
public class JwtUtilHelper {
    // Lấy secret key
    @Value("${jwt.secret}")
    private String secret;


    public String generateToken(String data){
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        String jws = Jwts.builder().subject(data).signWith(key).compact();
        return jws;
    }

    public  boolean verifyToken(String token){
        try{
            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
            Jwts.parser().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}

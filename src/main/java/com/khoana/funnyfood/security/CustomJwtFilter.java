package com.khoana.funnyfood.security;

import com.khoana.funnyfood.util.JwtUtilHelper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class CustomJwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtilHelper jwtUtilHelper;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getTokenFromHeader(request);
        if(token != null){
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken("","", new ArrayList<>());
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(usernamePasswordAuthenticationToken);
        }
        filterChain.doFilter(request,response);
    }
    private String getTokenFromHeader(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        String token = null;
        if(StringUtils.hasText(header)  && header.startsWith("Bearer ")){
            token = header.replace("Bearer ","");
        }
        return token;
    }


}

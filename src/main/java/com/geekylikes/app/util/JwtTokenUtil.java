package com.geekylikes.app.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class JwtTokenUtil implements Serializable {

    @Value("${jwt.secret}")
    private String secret;

//    public String getUserIdFromToken(String token) {
//        return getClaimFromToken()
//    }
}
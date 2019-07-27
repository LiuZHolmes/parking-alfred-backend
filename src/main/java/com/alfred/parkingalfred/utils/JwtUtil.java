package com.alfred.parkingalfred.utils;

import io.jsonwebtoken.Claims;
import com.alfred.parkingalfred.config.JwtConfig;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private static JwtConfig jwtConfig;
    
    private JwtUtil() {}

    public static Claims getTokenBody(String token){
        try {
            return Jwts.parser()
                    .setSigningKey(jwtConfig.getSecret())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    @Autowired
    public void setJwtConfig(JwtConfig jwtConfig) {
        JwtUtil.jwtConfig = jwtConfig;
    }
}

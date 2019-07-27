package com.alfred.parkingalfred.controller;

import com.alfred.parkingalfred.config.JwtConfig;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class AuthController {

    private JwtConfig jwtConfig;

    public AuthController(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @GetMapping(value = "/login")
    @ResponseBody
    public ResponseEntity login(String username, String password) {
        if (username == null || password == null) {
            return ResponseEntity.badRequest().body("auth is invalid");
        }
        String token = Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpire() * 1000))
                .claim("username", username)
                .signWith(SignatureAlgorithm.HS256, jwtConfig.getSecret())
                .compact();
        return ResponseEntity.ok(token);
    }
}

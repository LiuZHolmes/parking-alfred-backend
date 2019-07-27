package com.alfred.parkingalfred.utils;

import com.alfred.parkingalfred.entity.Employee;
import com.alfred.parkingalfred.enums.RoleEnum;
import io.jsonwebtoken.Claims;
import com.alfred.parkingalfred.config.JwtConfig;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String ID = "id";
    private static final String ROLE = "role";

    private static JwtConfig jwtConfig;

    private JwtUtil() {
    }

    public static Claims getTokenBody(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(jwtConfig.getSecret())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception ex) {
            ex.printStackTrace();
            return new DefaultClaims();
        }
    }

    public static String generateToken(Employee employee) {
        return Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpire() * 1000))
                .claim(ID, employee.getId())
                .claim(ROLE, employee.getRole())
                .signWith(SignatureAlgorithm.HS256, jwtConfig.getSecret())
                .compact();
    }

    public static Long getEmployeeId() {
        Claims claims = getTokenBody(getToken());
        Integer id = (Integer) claims.get(ID);
        return id.longValue();
    }

    public static RoleEnum getRole() {
        Claims claims = getTokenBody(getToken());
        return EnumUtil.getByCode(Integer.parseInt(claims.get(ROLE).toString()), RoleEnum.class);
    }

    private static String getToken() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getHeader("Authorization").replace("Bearer ", "");
    }

    @Autowired
    public void setJwtConfig(JwtConfig jwtConfig) {
        JwtUtil.jwtConfig = jwtConfig;
    }
}

package com.alfred.parkingalfred.interceptor;

import com.alfred.parkingalfred.annotation.Authorize;
import com.alfred.parkingalfred.enums.Role;
import com.alfred.parkingalfred.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        if (!(handler instanceof HandlerMethod)) return true;
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        Authorize authorize = handlerMethod.getMethod().getAnnotation(Authorize.class);

        if (authorize == null) {
            authorize = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Authorize.class);
        }

        if (authorize == null) return true;

        String token = request.getHeader("Authentication");
        if (checkAuthorize(authorize, token)) return true;

        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        response.setStatus(401);
        response.getWriter().write("401 Unauthorized");
        return false;
    }

    private boolean checkAuthorize(Authorize authorize, String token) {
        if (token == null) return false;
        
        token = token.replace("Bearer ", "");
        Claims claims = JwtUtil.getTokenBody(token);
        if (claims == null) return false;

        Role role = Role.valueOf(claims.get("role").toString());
        for (Role r : authorize.value()) {
            if (r == role)
                return true;
        }
        return false;
    }
}

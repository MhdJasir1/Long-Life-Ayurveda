package com.novatechzone.spring.longlifeayurveda.config;

import com.novatechzone.spring.longlifeayurveda.dto.RequestMetaDTO;
import com.novatechzone.spring.longlifeayurveda.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;



@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    private final RequestMetaDTO requestMetaDTO;

    public JwtInterceptor(RequestMetaDTO requestMetaDTO){
        this.requestMetaDTO = requestMetaDTO;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String auth = request.getHeader("authorization");

//        if ((request.getRequestURI().contains("swagger-ui/index.html#/")) && !(request.getRequestURI().contains("auth/"))){
//
//            Claims claims = jwtUtil.verify(auth);
//            requestMetaDTO.setCustomerId(Long.parseLong(claims.getIssuer()));
//            requestMetaDTO.setMobile(claims.get("mobile").toString());
//        }

        if(!(request.getRequestURI().contains("customer/auth/"))){
            Claims claims = jwtUtil.verify(auth);
            requestMetaDTO.setCustomerId(Long.parseLong(claims.getIssuer()));
            requestMetaDTO.setMobile(claims.get("mobile").toString());
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}

package com.novatechzone.spring.longlifeayurveda.util;


import com.novatechzone.spring.longlifeayurveda.domain.customer.Customer;
import com.novatechzone.spring.longlifeayurveda.exception.AccessDeniedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    private static final String secret = "kzxm0Ng3+u943X8ntHQu4+vPXJbCEek9JSuQRnIxn6fGigSFsWVrXsF5bvVXDD70zlnhqBlH5WeWef+7tJ75qNCN3dMtOnCXP/+ThhT0Orw=";
    private static final long expiryDuration = 60 * 60 * 24 * 90;

    public String generateAccessToken(Customer customer){
        long milliTime = System.currentTimeMillis();
        long expiryTime = milliTime+expiryDuration*1000;

        Date issuedAt = new Date(milliTime);
        Date expiredAt = new Date(expiryTime);

        Claims claims = Jwts.claims()
                .setIssuer(String.valueOf(customer.getCustomerId()))
                .setIssuedAt(issuedAt)
                .setExpiration(expiredAt);

        claims.put("customer_id",customer.getCustomerId());
        claims.put("mobile",customer.getMobile());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }

    public Claims verify(String authorization) throws Exception {
        try {
            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(authorization).getBody();
            return claims;
        } catch (Exception e) {
            throw new AccessDeniedException("Access Denied");
        }
    }
}

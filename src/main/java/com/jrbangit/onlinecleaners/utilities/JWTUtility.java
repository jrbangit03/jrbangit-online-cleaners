package com.jrbangit.onlinecleaners.utilities;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class JWTUtility {

    static final String SECRET_KEY = "JrBangitOnlineCleanerApp";
    static final String PREFIX = "Bearer";
    static final long EXPIRATION = 864_000_00; //1-day

    public static String createToken(UserDetails userDetails){
//        Map<String, Object> claims = new HashMap<>();

        String jwt = Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
//                .setClaims(claims)
                .compact();
        return jwt;
    }

    public static String getUsernameFromToken(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }

    public static Date getExpirationDateFromToken(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getExpiration();
    }

    public static Boolean isTokenExpired(String token){
       return JWTUtility.getExpirationDateFromToken(token).before(new Date(System.currentTimeMillis()));
    }

    public static Boolean isTokenValid(String token, UserDetails userDetails){
        String username = JWTUtility.getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token) );

    }
}

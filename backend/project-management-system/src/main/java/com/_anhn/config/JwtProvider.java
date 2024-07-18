package com._anhn.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtProvider {

    public static final SecretKey key = Keys.hmacShaKeyFor(JwtConstraint.SECRET_KEY.getBytes());

    public static String generateToken(Authentication authentication) {

        String jwt = Jwts.builder().setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 86400000))
                .claim("email", authentication.getName())
                .signWith(key)
                .compact();
        System.out.println(jwt);
        return jwt;
        //Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
    }

    public static String getEmailFromToken(String jwt) {
        Claims claims = Jwts.parser().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
        return String.valueOf(claims.get("email"));
    }
}

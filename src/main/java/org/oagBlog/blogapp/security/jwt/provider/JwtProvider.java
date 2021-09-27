package org.oagBlog.blogapp.security.jwt.provider;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.oagBlog.blogapp.entity.role.RoleEntity;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;

@Component
public class JwtProvider {

    private static final long expiration = 1000 * 60 * 60 * 24 * 5;
    private static final String secretKey = "Bu jwt token gereration qilish uchun key buni hech kim bilmasligi kerak!!!";

    public String generateToken(String username, Set<RoleEntity> roles) {
        return Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(expiration + System.currentTimeMillis()))
                .claim("roles", roles)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();

    }

    public String getUsernameFromToken(String token) {
        try {
            String username = Jwts
                    .parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            return username;
        } catch (Exception e) {
            return null;
        }
    }
}

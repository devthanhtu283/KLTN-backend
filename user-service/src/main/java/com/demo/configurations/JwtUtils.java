package com.demo.configurations;

import com.demo.helpers.Role;
import com.demo.services.CustomUserDetails;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationMs}")
    private int jwtExpirationMs;


    public String generateToken(String username, String email, Integer userType) {
        Role role = Role.fromUserType(userType);
        logger.debug("Generating token for username: {}", username);
        String token = Jwts.builder()
                .setSubject(username)
                .claim("email", email)
                .claim("userType", userType)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
        logger.debug("Generated token: {}", token);
        return token;
    }

    public boolean validateToken(String token) {
        try {
            logger.debug("Validating token: {}", token);
            Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
            logger.info("Token validated successfully");
            return true;
        } catch (JwtException e) {
            logger.error("Invalid JWT signature or token: {}", e.getMessage());
            return false;
        } catch (IllegalArgumentException e) {
            logger.error("Invalid token argument: {}", e.getMessage());
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        logger.debug("Extracting username from token: {}", token);
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        String username = claims.getSubject();
        logger.debug("Extracted username: {}", username);
        return username;
    }

    public String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            String token = bearerToken.substring(7);
            logger.debug("Extracted token from request: {}", token);
            return token;
        }
        logger.warn("No valid Bearer token found in request");
        return null;
    }

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }
}
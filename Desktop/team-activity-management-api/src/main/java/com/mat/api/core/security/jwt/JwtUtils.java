package com.mat.api.core.security.jwt;

import com.mat.api.core.security.helpers.ClaimKeyEnum;
import com.mat.api.core.security.services.UserDetailsImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${authentication.jwt.secret}")
    private String jwtSecret;

    @Value("${authentication.jwt.expiration}")
    private int jwtExpirationMs;


    public String generateToken(final Authentication authentication) {
        final Map<String, Object> claims = new HashMap<>();

        final UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        claims.put(ClaimKeyEnum.CLAIM_KEY_USER_ID.getValue(), userPrincipal.getId());

        claims.put(ClaimKeyEnum.CLAIM_KEY_USERNAME.getValue(), userPrincipal.getUsername());

        claims.put(ClaimKeyEnum.CLAIM_KEY_CREATED.getValue(), new Date());

        claims.put(ClaimKeyEnum.CLAIM_KEY_NAME.getValue(), userPrincipal.getFirstName() + " " + userPrincipal.getLastName());

        return generateToken(claims);
    }

    private String generateToken(final Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + jwtExpirationMs * 1000);
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    String getUserNameFromJwtToken(final String token) {
        String username;

        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.get("username").toString();
        } catch (Exception e) {
            username = null;
        }

        return username;
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;

        try {
            claims = Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build().parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }

        return claims;
    }

    boolean validateJwtToken(final String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
            return true;
        } catch (final MalformedJwtException e) {
            LOGGER.error("Invalid JWT token: {}", e.getMessage());
        } catch (final ExpiredJwtException e) {
            LOGGER.error("JWT token is expired: {}", e.getMessage());
        } catch (final UnsupportedJwtException e) {
            LOGGER.error("JWT token is unsupported: {}", e.getMessage());
        } catch (final IllegalArgumentException e) {
            LOGGER.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}

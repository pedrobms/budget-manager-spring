package br.ufsm.csi.budgetmanagerapi.security;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import br.ufsm.csi.budgetmanagerapi.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTUtil {
    private static final long EXPIRATION_TIME = Duration.ofSeconds(480).toMillis();
    private static final String SECRET = "SecretKey";

    public String tokenGenerator(User user) {
        final Map<String, Object> claims = new HashMap<>();
        
        claims.put("id", user.getId());
        claims.put("sub", user.getEmail());
        claims.put("role", user.getRole().getValue());

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        if (token != null) {
            return parseToken(token).getSubject();
        }

        return null;
    }

    public Long getIdFromToken(String token) {
        if (token != null) {
            return parseToken(token).get("id", Long.class);
        }

        return null;
    }

    public boolean isTokenValid(String token) {
        return parseToken(token).getExpiration().before(new Date());
    }

    public Claims parseToken(String token){
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token.replace("Bearer", ""))
                .getBody();
    }
}

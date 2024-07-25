package com.challenge.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;

@Service
public class JWTService {
    private static final String SECRET_KEY = "586E3277255738782FA13F4428472B4B4625065563566B597073373676397924";

    private static final int JWT_EXPIRATION = 18000000; // 5 hours in milliseconds

    public String getToken(UserDetails user) {
        return getToken(new HashMap<>(), user);
    }

    private String getToken(Map<String, Object> extraClaims, UserDetails user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

            return JWT.create()
                    .withPayload(extraClaims)
                    .withSubject(user.getUsername())
                    .withIssuedAt(new Date(System.currentTimeMillis()))
                    .withExpiresAt(new Date(System.currentTimeMillis() + JWT_EXPIRATION)) // 5 hours
                    .sign(algorithm);
        }catch (JWTCreationException e){
            throw new RuntimeException("Token could not be generated");
        }
    }

    public String getSubject(String token) throws JWTVerificationException {
        if (token == null) {
            throw new JWTVerificationException("Token is not valid.");
        }

        DecodedJWT decodedJWT;

        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

            decodedJWT = JWT.require(algorithm)
                    .build()
                    .verify(token);

            String subject = decodedJWT.getSubject();

            if (subject == null) {
                throw new RuntimeException("Invalid token: subject is null.");
            }

            return subject;
        } catch (JWTVerificationException exception) {
            throw new JWTVerificationException("Token verification failed", exception);
        }
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getSubject(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return getClaim(token, DecodedJWT::getExpiresAt).before(new Date());
    }

    private DecodedJWT getAllClaims(String token) throws JWTVerificationException{
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm).build();
            return verifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException("Failed to get claims from token", e);
        }
    }

    public <T> T getClaim(String token, Function<DecodedJWT, T> claimsResolver) {
        final DecodedJWT jwt = getAllClaims(token);
        return claimsResolver.apply(jwt);
    }
}

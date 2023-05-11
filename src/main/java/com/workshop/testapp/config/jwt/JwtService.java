package com.workshop.testapp.config.jwt;

import com.workshop.testapp.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

@Component
public class JwtService {

    private static final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);

    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);

    }

    public String getUsername(String token) {
        Claims claims =getClaims(token);
        return claims.getSubject();
    }

    private Claims getClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Date getExpirationDate(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String generateToken(User user) {
        Claims extratClaims = Jwts.claims().setSubject(user.getId().toString());
        extratClaims.put("scopes", List.of(user.getRole()) );
        return Jwts
                .builder()
                .setClaims(extratClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setIssuer("medoune-wallet.sn")
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //refresh token

    public String generateRefreshToken(User user) {
        Claims extratClaims = Jwts.claims().setSubject(user.getId().toString());
        //extratClaims.put("scopes", List.of(user.getRole()) );
        return Jwts
                .builder()
                .setClaims(extratClaims)
                .setSubject(user.getId().toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setIssuer("medoune-wallet.sn")
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 30))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validate(String token) {
       try{
           Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
           return true;
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
        return false;
    }

}

package com.rf.relatorio.security;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Jwts;


@Service
public class JwtService {

      private final SecretKey SECRET_KEY =
            Keys.secretKeyFor(SignatureAlgorithm.HS512);

      public String generateToken(Authentication authentication){
            UserDetails user = (UserDetails) authentication.getPrincipal();

            System.out.println("AUTHORITIES NO TOKEN: " + user.getAuthorities());

            List<String> roles = user.getAuthorities().stream()
                  .map(GrantedAuthority::getAuthority)
                  .collect(Collectors.toList());
            
            return Jwts.builder()
                  .setSubject(user.getUsername())
                  .claim("roles", roles) 
                  .setIssuedAt(new Date())
                  .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                  .signWith(SECRET_KEY, SignatureAlgorithm.HS512)
                  .compact();
      }

      public String extractUsername(String token){

            return Jwts.parser()
                  .setSigningKey(SECRET_KEY)
                  .parseClaimsJws(token)
                  .getBody()
                  .getSubject();
      }

      public boolean validateToken(String token) {
            try {
                  Jwts.parserBuilder()
                        .setSigningKey(SECRET_KEY)
                        .build()
                        .parseClaimsJws(token);
                  return true;
            } catch (Exception e) {
                  return false;
            }
      }

}

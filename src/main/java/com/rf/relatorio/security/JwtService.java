package com.rf.relatorio.security;

import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.SignatureAlgorithm;

import io.jsonwebtoken.Jwts;


@Service
public class JwtService {

  private String SECRET_KEY = "chave-super-secreta-para-token";

  public String generateToken(String username){

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
  }

  public String extractUsername(String token){

        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}

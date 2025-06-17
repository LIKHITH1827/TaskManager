package com.example.api.service;

import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.web.webauthn.api.PublicKeyCose;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import net.bytebuddy.asm.Advice.This;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class JwtService {
	
	
	
	
	  @Value("${jwt.secret}") 
	  private String secretKey;
	  
	  private final long jwtExpiration = 1000*60*60*24*200;
	  
	  private SecretKey getSigningKey() { 
		  byte[] keyBytes =Decoders.BASE64.decode(secretKey); 
		  return Keys.hmacShaKeyFor(keyBytes); 
		  }
	  
	  public String extractUsername(String token) 
	  { 
		  return extractClaim(token,  Claims::getSubject);
		  }
	  
	  public <T> T extractClaim(String token,Function<Claims,T> claimsResolver) {
	  
	  final Claims claims= extractAllClaims(token);
	  return claimsResolver.apply(claims);
	  
	  }
	  
	  private Claims extractAllClaims(String token) {
	  
	       return Jwts
	    		   .parser()
	    		   .verifyWith(getSigningKey())
	    		   .build()
	    		   .parseSignedClaims(token)
	    		   .getPayload() ;
	  
	  
	  
	  }
	  
	 
	 public String generateToken(Map<String, Object> extraClaims,UserDetails userDetails) {
		 return buildToken(extraClaims,userDetails,jwtExpiration);
	 }
	
	 private String buildToken(Map<String, Object> extraClaims,UserDetails userDetails,long expiration) {
		 
		 return Jwts
				 .builder()
				 .claims(extraClaims)
				 .subject(userDetails.getUsername())
				 .issuedAt(new Date(System.currentTimeMillis()))
				 .expiration(new Date(System.currentTimeMillis()+expiration))
				 .signWith(getSigningKey(),Jwts.SIG.HS256)
				 .compact();
				
		 }
	 
	 
	 
	 
	 
	 
}

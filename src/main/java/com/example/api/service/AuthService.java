package com.example.api.service;
  
import java.net.Authenticator.RequestorType;
import java.nio.file.attribute.UserDefinedFileAttributeView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.api.controller.AuthenticationRequest;
import com.example.api.controller.AuthenticationResponse; 
import com.example.api.controller.RegisterRequest; 
import com.example.api.model.User;
import com.example.api.repository.UserRepository;

import lombok.RequiredArgsConstructor;






  
  @RequiredArgsConstructor
  @Service 
  public class AuthService {
  
	  @Autowired
	  private PasswordEncoder passwordEncoder;
  
	  @Autowired
	  private UserRepository repository;
	  
	  @Autowired
	  private JwtService   jwtService;
	  
	  @Autowired
	  private AuthenticationManager authenticationManager;
	  
  
     public AuthenticationResponse register(RegisterRequest request) {
  
       var user= User.builder().firstname(request.getFirstname())
    		   .lastname(request.getLastname())
    		   .email(request.getEmail())
    		   .password(passwordEncoder.encode(request.getPassword()))
    		   .role(request.getRole())
    		   .build();
       repository.save(user);
       

      

      var jwtToken=  jwtService.generateToken(user);
      
      return AuthenticationResponse.builder().token(jwtToken).build();
  
  }
  
     
     public AuthenticationResponse login(AuthenticationRequest request) {
    	 
    	 System.out.println("user pwd"+ request.getPassword());
    	  
    	authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    	
    	

    	
    	var user = repository.findByEmail(request.getEmail()).orElseThrow();
    	


System.out.println("Loaded user: " + user.getUsername());
//System.out.println("Loaded password: " + user.getPassword());
         
        var jwtToken=  jwtService.generateToken(user);
        
        return AuthenticationResponse.builder().token(jwtToken).build();
    
    }
  
  
  }
 
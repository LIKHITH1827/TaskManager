
  package com.example.api.service;
  
  import java.net.Authenticator.RequestorType;
import java.nio.file.attribute.UserDefinedFileAttributeView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
  
  import com.example.api.controller.AuthenticationResponse; import
  com.example.api.controller.RegisterRequest; import
  com.example.api.model.User;
import com.example.api.repository.UserRepository;



;
  
  
  @Service 
  public class AuthService {
  
	  @Autowired
	  private PasswordEncoder passwordEncoder;
  
	  @Autowired
	  private UserRepository userRepository;
	  
	  @Autowired
	  private JwtService   jwtService;
  
     public AuthenticationResponse register(RegisterRequest request) {
  
       var user= User.builder().firstname(request.getFirstname())
    		   .lastname(request.getLastname())
    		   .email(request.getEmail())
    		   .password(passwordEncoder.encode(request.getPassword()))
    		   .role(request.getRole())
    		   .build();
        userRepository.save(user);
      
      var jwtToken=  jwtService.generateToken(user);
      
      return AuthenticationResponse.builder().token(jwtToken).build();
  
  }
  
  
  
  
  }
 
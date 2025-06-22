package com.example.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.service.AuthService;

//import com.example.api.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
//@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class AuthController {

	
	  @Autowired 
	  private AuthService service;
	  
	  @PostMapping("/register") 
	  public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){ 
		  return ResponseEntity.ok(service.register(request));
		  
	  }
	  
	  @PostMapping("/login")
	  public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request){
		  
		  return ResponseEntity.ok(service.login(request));
	  }
	 
}
 
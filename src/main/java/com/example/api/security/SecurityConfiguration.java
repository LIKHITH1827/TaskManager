package com.example.api.security;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.api.service.JwtService;

import jakarta.websocket.Session;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {
     
	
	private final String[] ALLOW_LIST= {""};
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthFilter;
	
	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		    .csrf(AbstractHttpConfigurer::disable)
		    .authorizeHttpRequests(req->req.requestMatchers(ALLOW_LIST)
		    		.permitAll()
		    		.anyRequest()
		    		.authenticated())
		    .sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		    .authenticationProvider(authenticationProvider)
		    .addFilterBefore(jwtAuthFilter,UsernamePasswordAuthenticationFilter.class);
		    
		  return http.build();  
		
		
		
		
		
		
		
	}
	
	
	
}

package com.example.api.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.EqlParser.New_valueContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.api.service.JwtService;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.NonNull;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	
	@Override
	protected void doFilterInternal(@NonNull  HttpServletRequest request,@NonNull  HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		final String authHeader = request.getHeader("Authorization");
		final String jwt;
		final String userEmail;
		
		if(authHeader==null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
			}
		 
		   jwt= authHeader.substring(7);
		   userEmail  = jwtService.extractUsername(jwt);
		   if(userEmail!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			   
			  UserDetails userDetails= this.userDetailsService.loadUserByUsername(userEmail); 
			   if(jwtService.isTokenValid(jwt, userDetails)){
				   
				  UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities()); 
				  authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				  SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				   
			   }
			   
		   }
		   filterChain.doFilter(request, response);
	
		
		
	}
  
	
	
	
	
	
	
	
	
	
}

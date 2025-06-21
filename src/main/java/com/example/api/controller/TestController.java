package com.example.api.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins  = "http://localhost:4200", allowedHeaders = "*")
public class TestController {

	@GetMapping("/")
	public String Home() {
		
		return "This is Home " ;
	}

}

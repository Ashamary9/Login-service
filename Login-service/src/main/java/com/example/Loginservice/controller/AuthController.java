package com.example.Loginservice.controller;

import jakarta.validation.Valid;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Loginservice.jwt.JwtUtility;
import com.example.Loginservice.request.LoginRequest;
import com.example.Loginservice.response.JSONResponse;
import com.example.Loginservice.service.UserDetailsImpl;
import com.example.Loginservice.service.UserDetailsServiceImpl;



@RestController
@RequestMapping("/registration")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
	
	@Autowired
	DaoAuthenticationProvider authenticationManager;
	
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	JwtUtility jwtUtility;
	
	@PostMapping("/signin")
	public ResponseEntity<?> validateUser(@Valid @RequestBody LoginRequest loginRequest) {
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		String jwtToken = jwtUtility.generateToken(authentication);
		Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
	    List<String> collect = authorities.stream().map(GrantedAuthority :: getAuthority).collect(Collectors.toList());
	    JSONResponse jsonResponse = new JSONResponse(jwtToken, userDetails.getUsername(), collect);
		return ResponseEntity.ok(jsonResponse);
						
												
	}
	
	
}


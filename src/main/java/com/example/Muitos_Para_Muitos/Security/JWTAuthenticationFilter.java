package com.example.Muitos_Para_Muitos.Security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.Muitos_Para_Muitos.Data.UserDetailsData;
import com.example.Muitos_Para_Muitos.Models.Student;
import com.fasterxml.jackson.databind.ObjectMapper;



public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	private final AuthenticationManager authenticationManager;
	
	public static final int TOKEN_INSPIRATION = 600000;
	
	public static final String TOKEN_PASSWORD = "bda45758-0952-4184-916d-5a2148c246ad";

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		try {
			
			Student student = new ObjectMapper().readValue(request.getInputStream(), Student.class);
			
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					
					student.getEmail(),
					student.getPassword(),
					new ArrayList<>()
					
			));
				
		} catch (IOException e) {
			
			throw new RuntimeException("Authentication error " + e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
			UserDetailsData userData = (UserDetailsData) authResult.getPrincipal();
			String token = JWT.create()
					.withSubject(userData.getUsername())
					.withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_INSPIRATION))
					.sign(Algorithm.HMAC512(TOKEN_PASSWORD));
			
			response.getWriter().write(token);
			response.getWriter().flush();
					
	}
	
	
	
	
	
}

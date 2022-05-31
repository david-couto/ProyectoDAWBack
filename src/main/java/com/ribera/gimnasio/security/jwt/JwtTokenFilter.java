package com.ribera.gimnasio.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ribera.gimnasio.security.service.UserDetailsServiceImpl;

public class JwtTokenFilter extends OncePerRequestFilter {

	private final static Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);
	@Autowired
	JwtProvider jwtProvider;
	@Autowired
	UserDetailsServiceImpl userDetailsService;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String token= getToken(request);
			System.out.println(token);
			if(token != null && jwtProvider.validateToken(token)) {
				
				String nombreUsuario = jwtProvider.getNombreUsuarioFromToken(token);
				System.out.println(nombreUsuario);
				UserDetails userDetails = userDetailsService.loadUserByUsername(nombreUsuario);
				System.out.println(userDetails);
				UsernamePasswordAuthenticationToken auth =
						new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}catch(Exception e) {
			logger.error("Fallo ene l metodo doFilter");
		}
		filterChain.doFilter(request, response);
	}
	private String getToken(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		if(header != null && header.startsWith("Bearer")) {
			return header.replace("Bearer", "");
			
		}
		return null;
	}

}

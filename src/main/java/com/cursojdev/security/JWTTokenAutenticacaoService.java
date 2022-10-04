package com.cursojdev.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cursojdev.ApplicationContextLoad;
import com.cursojdev.model.Usuario;
import com.cursojdev.repositories.UsuarioRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
@Component
public class JWTTokenAutenticacaoService {

	private static final long EXPIRATION_TIME = 172800000;
	private static final String SECRET = "SSS-SenhaSuperSecreta";
	private static final String TOKEN_PREFIXO = "Bearer";
	private static final String HEADER_STRING = "Authorization";

	public void addAuthentication(HttpServletResponse response, 
			String username) throws IOException{
		String JWT = Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
		
		String token = TOKEN_PREFIXO + " " + JWT;
		
		response.addHeader(HEADER_STRING, token);
		
		response.getWriter().write("{\"Authorization\" : \"" +token+ "\"}");
	
	}

	public Authentication getAuthentication(HttpServletRequest request) {
		
		String token = request.getHeader(HEADER_STRING);
		
		if(token != null) {
			String user = Jwts.parser().setSigningKey(SECRET)
					.parseClaimsJws(token.replace(TOKEN_PREFIXO, ""))
					.getBody().getSubject();
			
			if(user != null) {
				Usuario usuario = ApplicationContextLoad.getApplicationContext()
						.getBean(UsuarioRepository.class).findByEmail(user);
			
				if(usuario != null) {
					return new UsernamePasswordAuthenticationToken(
							usuario.getEmail(), 
							usuario.getSenha(), 
							usuario.getAuthorities());
				}
			}
		}
		
		return null;
	
	}

}

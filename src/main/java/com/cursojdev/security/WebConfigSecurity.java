package com.cursojdev.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.cursojdev.services.ImplementacaoUserDetailService;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private ImplementacaoUserDetailService implementacaoUserDetailService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
				.disable().authorizeRequests().antMatchers("/**").permitAll()
				.antMatchers("/index").permitAll()
				.antMatchers("/h2-console/**").permitAll()
				.anyRequest().authenticated().and().logout().logoutSuccessUrl("/index")
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.and().addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
						UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(new JWTApiAuthenticacaoFilter(), 
						UsernamePasswordAuthenticationFilter.class);
		
		http.headers().frameOptions().disable();
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(implementacaoUserDetailService)
			.passwordEncoder(new BCryptPasswordEncoder());
	}

}

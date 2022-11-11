package com.cursojdev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class CursospringApiJdevApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(CursospringApiJdevApplication.class, args);
		//System.out.println(new BCryptPasswordEncoder().encode("123"));
	}
	
	/*
	 * public void addCorsMappings(CorsRegistry registry) {
	 * registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE")
	 * .allowedOrigins("http://127.0.0.1:5500"); }
	 */

	/*
	 * public void configure(WebSecurity web) throws Exception {
	 * web.ignoring().antMatchers("/h2-console/**"); }
	 */

	/*
	 * protected void configure(HttpSecurity http) throws Exception {
	 * http.authorizeRequests().antMatchers("/")
	 * .permitAll().antMatchers("http://localhost:8080/h2-console/**") .permitAll()
	 * 
	 * 
	 * http.csrf().disable(); http.headers().frameOptions().sameOrigin(); ; }
	 */
}

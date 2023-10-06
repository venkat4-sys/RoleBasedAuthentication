package com.getinfy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.getinfy.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class Config {

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
         System.out.println("security filter chain called");
		http.csrf().disable().authorizeHttpRequests((req) ->

		req.antMatchers("/Admin/login").permitAll()
		.antMatchers("/Admin/**").hasRole("ADMIN")
		.antMatchers("/User/**").hasAnyRole("ADMIN", "CW")
		.anyRequest().authenticated()
		).formLogin();

		return http.build();

	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}

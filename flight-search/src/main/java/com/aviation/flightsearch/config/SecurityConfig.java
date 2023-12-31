package com.aviation.flightsearch.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.aviation.flightsearch.security.JwtAuthenticationEntryPoint;
import com.aviation.flightsearch.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //private UserDetailsServiceImpl userDetailsService;
	private JwtAuthenticationEntryPoint handler;
	
    public SecurityConfig(JwtAuthenticationEntryPoint handler) {
        //this.userDetailsService = userDetailsService;
        this.handler = handler;
    }
    
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
    	return new JwtAuthenticationFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	
    	CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
        corsConfiguration.setAllowedOrigins(List.of("*"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PUT","OPTIONS","PATCH", "DELETE"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setExposedHeaders(List.of("Authorization"));
        http.csrf().disable();
    	http
    		//.addFilterBefore(corsFilter(), SessionManagementFilter.class) //adds your custom CorsFilter
    		.cors().and()
    		.exceptionHandling(exceptionHandling -> exceptionHandling
                    .authenticationEntryPoint(handler))
    		.sessionManagement(session -> session
    				.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    		.authorizeHttpRequests(auth -> auth
    				.requestMatchers(HttpMethod.POST,"/api/auth/login").permitAll()
    				.requestMatchers(HttpMethod.POST,"/api/auth/register").permitAll()
    				.requestMatchers(HttpMethod.GET, "/api/airports").permitAll()
  					.requestMatchers(HttpMethod.GET, "/api/flights").permitAll()
  					.requestMatchers(HttpMethod.PUT, "/api/flights/setFlights").permitAll()
  					.requestMatchers(HttpMethod.GET, "/api/mock/getExternalFlights").permitAll()
  					.requestMatchers(HttpMethod.GET, "/v3/**", "/swagger-ui/**").permitAll()
  					.anyRequest().authenticated()
    		)
    		.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    	
    	return http.build();
    }
    
}

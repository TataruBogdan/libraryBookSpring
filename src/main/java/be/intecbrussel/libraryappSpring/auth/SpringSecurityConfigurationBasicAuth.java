package be.intecbrussel.libraryappSpring.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SpringSecurityConfigurationBasicAuth{
//
//	// it's not working ???
//	// tell Spring Security that you need to allow all options request,
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		//1: Response to preflight request doesn't pass access control check
//		//2: basic auth
//		return http.authorizeHttpRequests(
//				auth -> auth.
//						requestMatchers(HttpMethod.OPTIONS, "/**").
//						permitAll().
//						requestMatchers(HttpMethod.POST, "/users/login").
//						permitAll().
//						anyRequest().
//						authenticated()).
//						httpBasic(Customizer.withDefaults()).
//						sessionManagement(
//								session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//						)
//						.csrf(AbstractHttpConfigurer::disable) // customizer -> customizer.disable() / AbstractHttpConfigurer::disable
//						.build();
//	}

//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http.cors().and()
//				.csrf()
//				.disable()
//				.sessionManagement()
//				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//				.and()
//				.addFilterAfter(requestHeaderAuthenticationFilter(), HeaderWriterFilter.class)
//				.authorizeHttpRequests()
//				.antMatchers("/**").autneticated();
//
//		return http.build();
//
//	}

//}

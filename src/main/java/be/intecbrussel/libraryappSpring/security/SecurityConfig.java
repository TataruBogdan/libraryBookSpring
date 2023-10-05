package be.intecbrussel.libraryappSpring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
//@Configuration: Indicates that this class provides configuration information to the Spring container.
@EnableWebSecurity
// @EnableWebSecurity: Enables Spring Security's web security features.
public class SecurityConfig {

    // need out Customer User object defined in CustomUserDetailsService
    private final CustomUserDetailsService userDetailsService;

    // to configure our Security -> @Configuration
    //                              @EnableWebSecurity
    //we need an authorization filter :
        // Filter base class that aims to guarantee a single execution per request dispatch,
        // on any servlet container. It provides a doFilterInternal method with HttpServletRequest
        // and HttpServletResponse arguments.

    private final JWTAuthorizationFilter jwtAuthorizationFilter;

    public SecurityConfig(CustomUserDetailsService userDetailsService, JWTAuthorizationFilter jwtAuthorizationFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder) throws Exception{
        System.out.println("s-a initializat un @bean AuthenticationManager");
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return  http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authz -> authz.requestMatchers(HttpMethod.OPTIONS).permitAll())
                .authorizeHttpRequests(authz -> authz.requestMatchers("/", "/login", "/register").permitAll())
                .authorizeHttpRequests(authz -> authz.requestMatchers("/**").authenticated())
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        System.out.println("s-a initializat un @bean BCryptPasswordEncoder");
        String password = new BCryptPasswordEncoder().encode("password");
        System.out.println(password);
        return new BCryptPasswordEncoder();
    }

}

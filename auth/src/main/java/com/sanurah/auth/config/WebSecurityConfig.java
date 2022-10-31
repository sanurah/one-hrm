package com.sanurah.auth.config;

import com.sanurah.auth.service.AuthenticationProvider;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@EnableWebSecurity
public class WebSecurityConfig {

    private AuthenticationProvider authenticationProvider;

    @Autowired
    public WebSecurityConfig(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(Customizer.withDefaults())
                .authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
                .formLogin()
                //.loginPage("http://127.0.0.1:4200/login")
                .permitAll()
                .and()
                .build();
    }

    @Autowired
    public void bindAuthenticationProvider(AuthenticationManagerBuilder authenticationManagerBuilder) {
        authenticationManagerBuilder.authenticationProvider(authenticationProvider);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        return source -> {
            CorsConfiguration cc = new CorsConfiguration();
            cc.setAllowCredentials(true);
            cc.setAllowedOrigins(List.of("http://127.0.0.1:4200"));
            cc.setAllowedHeaders(List.of("*"));
            cc.setAllowedMethods(List.of("*"));
            return cc;
        };
    }
}

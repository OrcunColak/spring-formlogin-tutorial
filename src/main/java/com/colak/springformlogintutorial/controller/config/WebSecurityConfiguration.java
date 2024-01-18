package com.colak.springformlogintutorial.controller.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfiguration {

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeHttpRequestsCustomizer -> authorizeHttpRequestsCustomizer
                        .requestMatchers("/logout").permitAll()
                        .requestMatchers("/home").hasRole("USER")
                        .requestMatchers("/hello").hasRole("ADMIN")
                        .anyRequest().authenticated()
                ).formLogin(formLoginCustomizer -> formLoginCustomizer
                        .loginPage("/login").permitAll()
                )
                .logout(LogoutConfigurer::permitAll)
                .rememberMe(r -> r.key("uniqueAndSecret"));

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user1 = User.builder()
                .username("admin")
                .password(encoder().encode("123456"))
                .roles("ADMIN", "USER")
                .build();
        UserDetails user2 = User.builder()
                .username("user")
                .password(encoder().encode("123456"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user1, user2);
    }
}

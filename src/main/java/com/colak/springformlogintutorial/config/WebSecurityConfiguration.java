package com.colak.springformlogintutorial.config;

import com.colak.springformlogintutorial.userdetailsservice.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfiguration {

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, UserDetailsServiceImpl userDetailsService) throws Exception {

        http.userDetailsService(userDetailsService)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(toH2Console()).permitAll()

                        .requestMatchers("/logout").permitAll()
                        .requestMatchers("/home").hasRole("USER")
                        .requestMatchers("/hello").hasRole("ADMIN")

                        // RBAC Urls
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/user/**").hasRole("USER")
                        .requestMatchers("/public/**").permitAll()

                        .anyRequest().authenticated()
                )
                .formLogin(formLoginCustomizer -> formLoginCustomizer
                        .loginPage("/login").permitAll()
                )
                .logout(LogoutConfigurer::permitAll)
                .rememberMe(r -> r.key("uniqueAndSecret"));

        return http.build();
    }
    /*
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
    https://medium.com/@bubu.tripathy/role-based-access-control-with-spring-security-ca59d2ce80b0
     */
}

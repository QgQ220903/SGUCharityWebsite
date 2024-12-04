package com.web.sgucharitywebsite.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
  private final CustomAuthenticationSuccessHandler successHandler;

  // Constructor Injection
  public SecurityConfig(CustomAuthenticationSuccessHandler successHandler) {
    this.successHandler = successHandler;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/css/**", "/js/**", "/img/**", "/", "/home", "/project", "/project/**", "/createOrder", "/submitOrder", "/vnpay-payment-return")
                    .permitAll()
                    .requestMatchers("/register").permitAll()
                    .requestMatchers("/logout").permitAll()
                    .requestMatchers("/resources/**").permitAll()
                    .requestMatchers("/user/css/**").permitAll() // Added for user CSS
                    .requestMatchers("/admin","/admin/**").hasRole("ADMIN")

                    .anyRequest().authenticated())
            .formLogin(form -> form
                    //.successHandler(successHandler))
                    .defaultSuccessUrl("/home", true))
            .logout(config -> config.logoutSuccessUrl("/home"))
            .build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}



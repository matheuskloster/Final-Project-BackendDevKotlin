package com.kloster.kotlin.backend.Final.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests { requests ->
                requests
                    .requestMatchers(HttpMethod.DELETE, "/products/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/customers/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/orders/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
            }
            .httpBasic()
            .and()
            .csrf().disable()

        return http.build()
    }

    @Bean
    fun userDetailsService(): UserDetailsService {
        val user = User.withDefaultPasswordEncoder()
            .username("user")
            .password("password")
            .roles("USER")
            .build()

        val admin = User.withDefaultPasswordEncoder()
            .username("admin")
            .password("admin")
            .roles("ADMIN")
            .build()

        return InMemoryUserDetailsManager(user, admin)
    }
}
package com.example.demo.security

import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.reactive.CorsConfigurationSource
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource

/**
 * @author Jefferson Alves Reis (jefaokpta) < jefaokpta@hotmail.com >
 * Date: 2020-11-23
 */
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
class SecurityConfig{

    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http.cors().and().authorizeExchange()
                .pathMatchers(HttpMethod.POST, "/login").permitAll()
                .pathMatchers(HttpMethod.GET, "/users/**").permitAll()
//                .pathMatchers( "/persons/**").hasRole("ADMIN")
                .anyExchange().authenticated()
                .and().httpBasic()
                .and().csrf().disable()
                .build()
//                .and()
//                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
    }

    @Bean
    fun authenticationManager(userAppService: UserAppService): ReactiveAuthenticationManager{
        return UserDetailsRepositoryReactiveAuthenticationManager(userAppService)
    }

    // CONFIGURACAO PARA FUNCIONAR O http.cors().and() ACIMA
    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("*")
        configuration.allowedMethods = listOf("GET", "POST", "PUT", "DELETE")
        configuration.allowedHeaders = listOf("Authorization", "Content-Type")
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}
package com.example.demo.security

import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextImpl
import org.springframework.security.web.server.context.ServerSecurityContextRepository
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import java.util.*

/**
 * @author Jefferson Alves Reis (jefaokpta) < jefaokpta@hotmail.com >
 * Date: 2020-11-26
 */
@Component
class JwtSecurityContextRepository(private val jwtAuthenticationManager: JwtAuthenticationManager): ServerSecurityContextRepository {

    override fun save(p0: ServerWebExchange?, p1: SecurityContext?): Mono<Void> {
        throw IllegalStateException("METODO SAVE NAO IMPLEMENTADO AQUI")
    }

    override fun load(exchange: ServerWebExchange): Mono<SecurityContext> {

        val token = Optional.ofNullable(exchange.request.headers.getFirst(HttpHeaders.AUTHORIZATION))
                .filter{it.startsWith("Bearer ")}
                .map { it.substring(7) }

        if (token.isPresent)
            return jwtAuthenticationManager
                    .authenticate(UsernamePasswordAuthenticationToken(null, token.get()))
                    .map(::SecurityContextImpl)

        return Mono.empty()
    }
}
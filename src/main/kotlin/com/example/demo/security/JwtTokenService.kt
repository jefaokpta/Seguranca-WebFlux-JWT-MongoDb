package com.example.demo.security

import com.example.demo.model.UserApp
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.http.HttpStatus
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Mono
import java.time.Instant
import java.time.Period
import java.time.ZoneId
import java.util.*
import javax.crypto.KeyAgreementSpi

/**
 * @author Jefferson Alves Reis (jefaokpta) < jefaokpta@hotmail.com >
 * Date: 2020-11-26
 */
@Service
class JwtTokenService {

    private val key =
            "jefaokpta102030405060708090asdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdjefao123"

    fun extractUsername(token: String) = validateToken(token)
            .map { Mono.just(it.body.subject) }
            .orElse(Mono.empty())

    fun validateToken(token: String) = Optional.ofNullable(
            try {
                Jwts.parserBuilder()
                .setSigningKey(Base64.getEncoder().encodeToString(this.key.toByteArray()))
                .build()
                .parseClaimsJws(token)
            }catch (exception: Exception){
                println(exception.stackTrace)
                println(":::: TOKEN INVALIDO! $token")
                null
            }
    )

    fun generateToken(user: UserApp) = Jwts.builder()
            .setIssuer("Seguranca WEBFLUX")
            .setSubject(user.username)
            .setIssuedAt(Date.from(Instant.now()))
            .setExpiration(Date.from(Instant.now().plusSeconds(300L)))
            .signWith(Keys.hmacShaKeyFor(this.key.toByteArray()))
            .compact()

}
package com.example.demo.endpoint

import com.example.demo.model.Login
import com.example.demo.model.Token
import com.example.demo.model.UserApp
import com.example.demo.repository.UserAppRepository
import com.example.demo.security.JwtTokenService
import com.example.demo.security.UserAppSecurityService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.cast
import reactor.kotlin.core.publisher.cast

/**
 * @author Jefferson Alves Reis (jefaokpta) < jefaokpta@hotmail.com >
 * Date: 2020-11-26
 */
@RestController
@RequestMapping("/login")
class LoginController(
        private val userAppRepository: UserAppRepository,
        private val jwtTokenService: JwtTokenService,
) {

    @PostMapping
    fun save(@RequestBody login: Login) = userAppRepository.findByUsername(login.username)
            .map(jwtTokenService::generateToken)
            .map(::Token)
}
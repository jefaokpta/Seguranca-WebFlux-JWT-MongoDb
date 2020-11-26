package com.example.demo.endpoint

import com.example.demo.model.Login
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

/**
 * @author Jefferson Alves Reis (jefaokpta) < jefaokpta@hotmail.com >
 * Date: 2020-11-26
 */
@RestController
@RequestMapping("/login")
class LoginController {

    @PostMapping
    fun save(@RequestBody login: Login) = Mono.just(login)
}
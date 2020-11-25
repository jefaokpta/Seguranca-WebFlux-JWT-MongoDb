package com.example.demo

import com.example.demo.model.Person
import com.example.demo.model.UserApp
import com.example.demo.repository.PersonRepository
import com.example.demo.repository.UserAppRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.stereotype.Component
import java.time.LocalTime
import java.util.*

/**
 * @author Jefferson Alves Reis (jefaokpta) < jefaokpta@hotmail.com >
 * Date: 2020-11-21
 */
@Component
class Bootstrap(private val userAppRepository: UserAppRepository): CommandLineRunner {
    override fun run(vararg args: String?) {
        userAppRepository.findByUsername("admin")
                .switchIfEmpty(userAppRepository.save(UserApp(
                        null,
                        "user",
                        PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("jefao123"),
                        "ROLE_USER"
                )))
                .map(::println)
                .subscribe()
    }
}
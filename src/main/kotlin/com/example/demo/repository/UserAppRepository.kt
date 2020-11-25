package com.example.demo.repository

import com.example.demo.model.UserApp
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono

/**
 * @author Jefferson Alves Reis (jefaokpta) < jefaokpta@hotmail.com >
 * Date: 2020-11-25
 */
interface UserAppRepository: ReactiveMongoRepository<UserApp, String> {

    fun findByUsername(username: String): Mono<UserApp>
}
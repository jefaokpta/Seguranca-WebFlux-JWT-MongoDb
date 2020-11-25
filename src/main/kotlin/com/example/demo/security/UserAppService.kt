package com.example.demo.security

import com.example.demo.repository.UserAppRepository
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import reactor.kotlin.core.publisher.toMono

/**
 * @author Jefferson Alves Reis (jefaokpta) < jefaokpta@hotmail.com >
 * Date: 2020-11-25
 */
@Service
class UserAppService(private val userAppRepository: UserAppRepository) : ReactiveUserDetailsService {

    override fun findByUsername(username: String) = userAppRepository.findByUsername(username)
            .cast(UserDetails::class.java)
            .toMono()
}
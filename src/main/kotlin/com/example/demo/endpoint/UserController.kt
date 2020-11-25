package com.example.demo.endpoint

import com.example.demo.model.UserAppDTO
import com.example.demo.repository.UserAppRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author Jefferson Alves Reis (jefaokpta) < jefaokpta@hotmail.com >
 * Date: 2020-11-25
 */
@RestController
@RequestMapping("/users")
class UserController(private val userAppRepository: UserAppRepository) {

    @GetMapping
    fun getAll() = userAppRepository.findAll()
            .map(::UserAppDTO)
}
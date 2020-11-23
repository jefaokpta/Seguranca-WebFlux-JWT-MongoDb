package com.example.demo.repository

import com.example.demo.model.Person
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

/**
 * @author Jefferson Alves Reis (jefaokpta) < jefaokpta@hotmail.com >
 * Date: 2020-11-21
 */
interface PersonRepository: ReactiveMongoRepository<Person, String> {
}
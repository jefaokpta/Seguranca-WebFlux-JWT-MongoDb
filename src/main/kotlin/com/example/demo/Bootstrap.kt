package com.example.demo

import com.example.demo.model.Person
import com.example.demo.repository.PersonRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.time.LocalTime
import java.util.*

/**
 * @author Jefferson Alves Reis (jefaokpta) < jefaokpta@hotmail.com >
 * Date: 2020-11-21
 */
//@Component
class Bootstrap(private val personRepository: PersonRepository): CommandLineRunner {
    override fun run(vararg args: String?) {
        personRepository.save(Person(null, "jefones", LocalTime.now().minute))
                .map(::println)
                .subscribe()
    }
}
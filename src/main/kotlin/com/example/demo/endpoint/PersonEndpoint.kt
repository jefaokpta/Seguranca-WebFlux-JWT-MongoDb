package com.example.demo.endpoint

import com.example.demo.model.Person
import com.example.demo.repository.PersonRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Mono
import javax.validation.Valid

/**
 * @author Jefferson Alves Reis (jefaokpta) < jefaokpta@hotmail.com >
 * Date: 2020-11-21
 */
@RestController
@RequestMapping("/persons")
class PersonEndpoint(private val personRepository: PersonRepository) {

    @GetMapping
    fun getAll() = personRepository.findAll()
            .filter { !it.deleted }

    @GetMapping("/{id}")
    fun get(@PathVariable id: String) = personRepository.findById(id)

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    fun save(@RequestBody @Valid person: Person) = personRepository.save(person)

    @PutMapping
    fun update(@RequestBody @Valid person: Person) = personRepository.findById(person.id?:"")
            .map { Person(person) }
            .flatMap(personRepository::save)
            .switchIfEmpty(Mono.error { ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa nao encontrada") })
            .then()

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String) = personRepository.findById(id)
            .map { Person(it.id, it.name, it.age, true)}
            .flatMap(personRepository::save)
            .switchIfEmpty(Mono.error { ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa nao encontrada") })
            .then()
}
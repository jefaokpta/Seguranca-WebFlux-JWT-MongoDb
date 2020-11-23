package com.example.demo.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import javax.validation.constraints.NotEmpty

/**
 * @author Jefferson Alves Reis (jefaokpta) < jefaokpta@hotmail.com >
 * Date: 2020-11-21
 */
@Document("pessoas")
data class Person(
        @Id
        val id: String?,
        @field:NotEmpty(message = ":::: NOME VAZIO PORRA! ASSIM FICA DIFICIL.")
        val name: String,
        val age: Int,
        val deleted: Boolean = false
) {
        constructor(person: Person) : this(
                person.id,
                person.name,
                person.age
        )
}
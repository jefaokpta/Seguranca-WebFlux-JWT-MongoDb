package com.example.demo.model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class UserAppTest{

    //@Test
    fun criaListaDeString(){
        val words = "eu,vc,tu,nos"
        val list = words.split(",").toList()
        println(list)
        println(list[3])
    }
}
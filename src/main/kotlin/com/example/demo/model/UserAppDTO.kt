package com.example.demo.model

/**
 * @author Jefferson Alves Reis (jefaokpta) < jefaokpta@hotmail.com >
 * Date: 2020-11-25
 */
class UserAppDTO(
        val id: String?,
        val username: String
) {
    constructor(userApp: UserApp): this(
        userApp.id, userApp.username
    )
}
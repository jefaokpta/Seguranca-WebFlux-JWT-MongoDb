package com.example.demo.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

/**
 * @author Jefferson Alves Reis (jefaokpta) < jefaokpta@hotmail.com >
 * Date: 2020-11-23
 */
@Document("users")
data class UserApp(
        @Id
        val id: String?,
        private val username: String,
        private val password: String,
        private val authorities: String = ""
): UserDetails {
        override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
                return authorities.split(",")
                        .map(::SimpleGrantedAuthority)
                        .toMutableList()
        }

        override fun getPassword() = password

        override fun getUsername() = username

        override fun isAccountNonExpired() = true

        override fun isAccountNonLocked() = true

        override fun isCredentialsNonExpired() = true

        override fun isEnabled() = true
}
package com.tkresic.url_shortener.config

import com.tkresic.url_shortener.repositories.AccountRepository
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

/**
 * Basic authentication entry point.
 */
@Component
class AuthenticationEntryPoint(private val accountRepository: AccountRepository) : AuthenticationProvider {
    override fun authenticate(authentication: Authentication): UsernamePasswordAuthenticationToken? {
        val name = authentication.name
        val password = authentication.credentials.toString()

        val user = accountRepository.findById(name)

        if (user.isEmpty() || !BCryptPasswordEncoder().matches(password, user.get().password)) {
            return null
        }

        return UsernamePasswordAuthenticationToken(name, password, ArrayList())
    }

    override fun supports(authentication: Class<*>): Boolean {
        return authentication == UsernamePasswordAuthenticationToken::class.java
    }
}
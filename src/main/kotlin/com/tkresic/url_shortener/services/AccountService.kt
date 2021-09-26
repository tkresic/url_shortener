package com.tkresic.url_shortener.services

import com.tkresic.url_shortener.dtos.accounts.AccountDTO
import com.tkresic.url_shortener.dtos.accounts.AccountResponseDTO
import com.tkresic.url_shortener.models.Account
import com.tkresic.url_shortener.repositories.AccountRepository
import com.tkresic.url_shortener.utils.RandomString
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

/**
 * Account service.
 */
@Service
class AccountService(private val accountRepository: AccountRepository) {
    /**
     * Creates account with provided account ID if the entity doesn't already exist.
     */
    fun createAccount(account: AccountDTO): AccountResponseDTO {
        if (accountRepository.existsById(account.AccountId)) {
            throw DataIntegrityViolationException("Entity already exists.")
        }

        val password = RandomString.generate(8)

        accountRepository.save(
            Account(
                account.AccountId,
                BCryptPasswordEncoder().encode(password)
            )
        )

        return AccountResponseDTO(
            true,
            "Your account is opened",
            password
        )
    }
}
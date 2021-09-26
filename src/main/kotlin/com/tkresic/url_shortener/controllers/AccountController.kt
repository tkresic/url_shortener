package com.tkresic.url_shortener.controllers

import com.tkresic.url_shortener.dtos.accounts.AccountDTO
import com.tkresic.url_shortener.dtos.accounts.AccountResponseDTO
import com.tkresic.url_shortener.services.AccountService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

/**
 * Account controller.
 */
@RestController
class AccountController(private val accountService: AccountService) {
    @PostMapping(
        "/account",
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun account(@Valid @RequestBody account: AccountDTO): ResponseEntity<AccountResponseDTO> =
        ResponseEntity(accountService.createAccount(account), HttpStatus.CREATED)
}
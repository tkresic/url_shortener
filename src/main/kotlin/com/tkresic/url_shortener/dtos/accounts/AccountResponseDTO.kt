package com.tkresic.url_shortener.dtos.accounts

/**
 * Account Response DTO.
 */
data class AccountResponseDTO(
    var success: Boolean,
    var description: String,
    var password: String
)
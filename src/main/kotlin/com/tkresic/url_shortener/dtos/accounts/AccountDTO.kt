package com.tkresic.url_shortener.dtos.accounts

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotBlank

/**
 * Account DTO.
 */
data class AccountDTO(
    @JsonProperty("AccountId")
    @field:NotBlank
    var AccountId: String
)
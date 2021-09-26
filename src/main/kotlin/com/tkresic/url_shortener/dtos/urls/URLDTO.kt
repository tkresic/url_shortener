package com.tkresic.url_shortener.dtos.urls

import org.hibernate.validator.constraints.Range
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

/**
 * URL DTO.
 */
data class URLDTO(
    @field:NotBlank
    var url: String,
    @field:NotNull
    @field:Range(min = 301, max = 302)
    var redirectType: Int
)
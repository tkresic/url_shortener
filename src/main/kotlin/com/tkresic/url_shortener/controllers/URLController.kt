package com.tkresic.url_shortener.controllers

import com.tkresic.url_shortener.dtos.urls.URLDTO
import com.tkresic.url_shortener.dtos.urls.URLResponseDTO
import com.tkresic.url_shortener.services.URLService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

/**
 * URL controller.
 */
@ControllerAdvice
@RestController
class URLController(private val URLService: URLService) {
    @PostMapping(
        "/register",
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun register(@Valid @RequestBody url: URLDTO): ResponseEntity<URLResponseDTO> =
        ResponseEntity(URLService.registerURL(url), HttpStatus.CREATED)

    @GetMapping(
        "/statistic/{AccountId}",
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun statistic(@PathVariable AccountId: String) =
        ResponseEntity(URLService.statistic(AccountId), HttpStatus.OK)

    @GetMapping("/r/{shortUrl}")
    fun redirect(@PathVariable shortUrl: String): ResponseEntity<Any> =
        URLService.redirect(shortUrl)
}
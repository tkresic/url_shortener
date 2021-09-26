package com.tkresic.url_shortener.utils

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import javax.persistence.EntityNotFoundException

@ControllerAdvice
class GlobalControllerExceptionHandler {
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Incorrect JSON format.")
    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun badRequest() {}

    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Entity already exists.")
    @ExceptionHandler(DataIntegrityViolationException::class)
    fun conflict() {}

    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Incorrect JSON data.")
    @ExceptionHandler(IllegalArgumentException::class)
    fun illegalArgument() {}

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Entity not found.")
    @ExceptionHandler(EntityNotFoundException::class)
    fun entityNotFound() {}

    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Incorrect JSON data.")
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun invalidArgument() {}
}
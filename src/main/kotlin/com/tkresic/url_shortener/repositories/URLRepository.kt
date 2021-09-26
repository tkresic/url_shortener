package com.tkresic.url_shortener.repositories

import com.tkresic.url_shortener.models.URL
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

/**
 * URL JPA repository.
 */
@Repository
interface URLRepository : JpaRepository<URL, String> {
    fun findByShortUrl(shortUrl: String): Optional<URL>

    fun existsByShortUrl(shortUrl: String): Boolean
}
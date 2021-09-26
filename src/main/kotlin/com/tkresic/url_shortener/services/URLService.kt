package com.tkresic.url_shortener.services

import com.tkresic.url_shortener.dtos.urls.URLDTO
import com.tkresic.url_shortener.dtos.urls.URLResponseDTO
import com.tkresic.url_shortener.models.Account
import com.tkresic.url_shortener.models.AccountURL
import com.tkresic.url_shortener.models.AccountURLID
import com.tkresic.url_shortener.models.URL
import com.tkresic.url_shortener.repositories.AccountRepository
import com.tkresic.url_shortener.repositories.AccountURLRepository
import com.tkresic.url_shortener.repositories.URLRepository
import com.tkresic.url_shortener.utils.RandomString
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI
import java.util.*
import javax.persistence.EntityNotFoundException

/**
 * URL service.
 */
@Service
class URLService(
    private val urlRepository: URLRepository,
    private val accountRepository: AccountRepository,
    private val accountURLRepository: AccountURLRepository
) {
    /**
     * Shortens the URL if it hasn't already been shortened.
     */
    fun registerURL(url: URLDTO): URLResponseDTO {
        if (urlRepository.existsById(url.url)) {
            throw DataIntegrityViolationException("Entity already exists.")
        }

        var shortUrl = RandomString.generate(6)

        while (urlRepository.existsByShortUrl(shortUrl)) {
            shortUrl = RandomString.generate(6)
        }

        urlRepository.save(
            URL(url.url, shortUrl, url.redirectType)
        )

        val baseUrl = getServiceBaseURL()

        return URLResponseDTO("$baseUrl/r/$shortUrl")
    }

    /**
     * Redirects to the original URL.
     */
    fun redirect(shortUrl: String): ResponseEntity<Any> {
        val url = urlRepository.findByShortUrl(shortUrl)

        if (url.isEmpty()) {
            throw EntityNotFoundException("Entity not found.")
        }

        val urlData = url.get()
        val auth: Authentication = SecurityContextHolder.getContext().authentication
        val accountId: String = auth.name

        val accountData = accountRepository.findById(accountId).get()

        val stats = accountURLRepository.findByAccountAndUrl(accountData, urlData)
        createOrUpdateStats(stats, accountData, urlData)

        return ResponseEntity.status(urlData.redirectType).location(URI.create(urlData.url)).build()
    }

    /**
     * Gets statistics by account.
     */
    fun statistic(accountId: String): MutableMap<String, Int>? {
        if (!accountRepository.existsById(accountId)) {
            throw EntityNotFoundException("Entity does not exist.")
        }

        val accountData = accountRepository.findById(accountId).get()
        val data = accountURLRepository.findByAccount(accountData)

        var response: MutableMap<String, Int>? = null

        if (data != null) {
            response = mutableMapOf()
            for (stat in data) {
                response[stat.url.url] = stat.numberOfCalls
            }
        }

        return response
    }

    /**
     * Creates or updates stats based on account & URL.
     */
    private fun createOrUpdateStats(stats: Optional<AccountURL>, account: Account, url: URL) {
        if (stats.isEmpty()) {
            accountURLRepository.save(
                AccountURL(
                    id = AccountURLID(),
                    account = account,
                    url = url,
                    numberOfCalls = 1
                )
            )
        } else {
            val statsData = stats.get()
            statsData.numberOfCalls++
            accountURLRepository.save(statsData)
        }
    }

    /**
     * Helper method for getting the service's base URL.
     */
    private fun getServiceBaseURL() =
        ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString()
}
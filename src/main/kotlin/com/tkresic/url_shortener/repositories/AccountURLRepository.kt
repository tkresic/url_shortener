package com.tkresic.url_shortener.repositories

import com.tkresic.url_shortener.models.Account
import com.tkresic.url_shortener.models.AccountURL
import com.tkresic.url_shortener.models.URL
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

/**
 * Account URL JPA repository.
 */
@Repository
interface AccountURLRepository : JpaRepository<AccountURL, Long> {
    fun findByAccount(account: Account): List<AccountURL>?

    fun findByAccountAndUrl(account: Account, url: URL): Optional<AccountURL>
}
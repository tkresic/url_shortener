package com.tkresic.url_shortener.repositories

import com.tkresic.url_shortener.models.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Account JPA repository.
 */
@Repository
interface AccountRepository : JpaRepository<Account, String>
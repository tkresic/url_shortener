package com.tkresic.url_shortener.models;

import java.io.Serializable
import java.util.*
import javax.persistence.Column
import javax.persistence.Embeddable

/**
 * Account URL ID.
 */
@Embeddable
class AccountURLID : Serializable {
    @Column(name = "accountId")
    var accountId: String? = null
    @Column(name = "url")
    var url: String? = null

    override fun hashCode(): Int {
        return Objects.hash(accountId, url)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other !is AccountURLID) {
            return false
        }
        val that: AccountURLID = other
        return Objects.equals(accountId, that.accountId) && Objects.equals(url, that.url)
    }
}
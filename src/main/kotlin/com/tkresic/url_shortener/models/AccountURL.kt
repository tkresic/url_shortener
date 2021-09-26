package com.tkresic.url_shortener.models;

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

/**
 * Account URL model.
 */
@Entity
@Table
data class AccountURL(
    @EmbeddedId
    var id: AccountURLID,
    @JsonIgnore
    @ManyToOne
    @MapsId("accountId")
    @JoinColumn(name = "accountId")
    var account: Account,
    @ManyToOne
    @MapsId("url")
    @JoinColumn(name = "url")
    var url: URL,
    var numberOfCalls: Int
)
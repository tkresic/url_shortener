package com.tkresic.url_shortener.models

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

/**
 * Account model.
 */
@Entity
data class Account(
    @Id
    @Column(nullable = false, unique = true)
    var id: String,
    @Column(nullable = false, unique = true)
    var password: String
)
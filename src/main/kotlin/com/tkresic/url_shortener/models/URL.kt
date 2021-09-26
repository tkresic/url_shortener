package com.tkresic.url_shortener.models

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

/**
 * URL model.
 */
@Entity
@Table
data class URL(
    @Id
    @Column(nullable = false, unique = true)
    var url: String,
    @Column(nullable = false, unique = true)
    var shortUrl: String,
    @Column(nullable = false)
    var redirectType: Int
)
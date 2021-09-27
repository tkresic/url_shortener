package com.tkresic.url_shortener.utils

import org.springframework.web.servlet.support.ServletUriComponentsBuilder

/**
 * Helper class for retrieving the services base URI.
 */
class Base {
    companion object {
        fun getServiceURI() =
            ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString()
    }
}
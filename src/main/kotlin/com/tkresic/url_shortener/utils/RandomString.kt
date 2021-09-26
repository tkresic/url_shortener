package com.tkresic.url_shortener.utils

import java.util.*

/**
 * Helper class for generating random string with arbitrary length.
 */
class RandomString {
    companion object {
        fun generate(length: Int) =
            UUID.randomUUID().toString().replace("-", "").substring(0, length)
    }
}
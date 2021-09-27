package com.tkresic.url_shortener

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * Main class which runs the app.
 */
@SpringBootApplication
class App
fun main(args: Array<String>) {
	runApplication<App>(*args)
}
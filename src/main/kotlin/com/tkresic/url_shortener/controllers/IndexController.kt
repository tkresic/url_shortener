package com.tkresic.url_shortener.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView

/**
 * Base controller which returns index & help views.
 */
@Controller
class IndexController {
    @GetMapping(path = ["/"])
    fun index(): ModelAndView? {
        val modelAndView = ModelAndView()
        modelAndView.viewName = "index"
        return modelAndView
    }

    @GetMapping(path = ["/help"])
    fun help(): ModelAndView? {
        val modelAndView = ModelAndView()
        modelAndView.viewName = "help"
        return modelAndView
    }
}
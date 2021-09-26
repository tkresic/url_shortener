package com.tkresic.url_shortener.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

/**
 * Web security configuration.
 */
@Configuration
@EnableWebSecurity
class WebConfig : WebSecurityConfigurerAdapter() {
    @Autowired
    private val authProvider: AuthenticationEntryPoint? = null

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.authenticationProvider(authProvider)
    }

    override fun configure(http: HttpSecurity) {
        http.csrf()
            .disable()
            .httpBasic()
            .and()
            .authorizeRequests()
            .antMatchers("/", "/help", "/account")
            .permitAll()
            .antMatchers("/register", "/r/**", "/statistic/**")
            .authenticated()
    }
}
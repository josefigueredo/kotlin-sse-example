package com.josefigueredo.examples.sse

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.reactive.config.EnableWebFlux

@SpringBootApplication
@EnableWebFlux
class SseTweetApplication

fun main(args: Array<String>) {
    runApplication<SseTweetApplication>(*args)
}

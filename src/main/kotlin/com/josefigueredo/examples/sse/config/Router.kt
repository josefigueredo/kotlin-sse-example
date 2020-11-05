package com.josefigueredo.examples.sse.config

import com.josefigueredo.examples.sse.handler.TweetHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.router

@Configuration
class Router {

    @Bean
    fun routes(tweetHandler: TweetHandler) =
            router {
                accept(MediaType.APPLICATION_JSON)
                        .nest {
                            POST("/tweet",
                                    tweetHandler::tweet)
                        }
                accept(MediaType.TEXT_EVENT_STREAM)
                        .nest {
                            GET("/stream",
                                    tweetHandler::stream)
                        }
            }
}
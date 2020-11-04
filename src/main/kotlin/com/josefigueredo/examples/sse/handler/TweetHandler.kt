package com.josefigueredo.examples.sse.handler

import com.josefigueredo.examples.sse.model.Tweet
import com.josefigueredo.examples.sse.repository.TweetRepository
import kotlinx.coroutines.reactive.asPublisher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import org.springframework.web.reactive.function.server.sse
import reactor.core.publisher.Mono

@Component
class TweetHandler @Autowired constructor(
        private var tweetRepository: TweetRepository
) {

    fun add(request: ServerRequest): Mono<ServerResponse> =
            ServerResponse.ok()
                    .body(
                            tweetRepository.add(
                                    request.bodyToMono(
                                            Tweet::class.java
                                    )
                            )
                    )

    fun stream(serverRequest: ServerRequest): Mono<ServerResponse> =
            ServerResponse
                    .ok()
                    .sse()
                    .body(
                            tweetRepository.stream()
                                    .asPublisher()
                    )
}
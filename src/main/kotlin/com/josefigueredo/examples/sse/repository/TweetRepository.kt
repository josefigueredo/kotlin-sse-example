package com.josefigueredo.examples.sse.repository

import com.josefigueredo.examples.sse.model.Tweet
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.core.listenToChannelAsFlow
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
class TweetRepository(
        private val redisTemplate: ReactiveRedisTemplate<String, Tweet>
) {
    companion object {
        private const val TWEET_CHANNEL = "TWEET_CHANNEL"
    }

    fun add(monoTweet: Mono<Tweet>): Mono<Tweet> =
            monoTweet
                    .flatMap {
                        redisTemplate
                                .convertAndSend(
                                        TWEET_CHANNEL,
                                        it
                                )
                    }
                    .flatMap { monoTweet }

    fun stream(): Flow<Tweet> =
            redisTemplate
                    .listenToChannelAsFlow(
                            TWEET_CHANNEL
                    )
                    .map { it.message }
}
package com.josefigueredo.examples.sse.config

import org.springframework.context.annotation.Configuration
import redis.embedded.RedisServer
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

@Configuration
internal class LocalRedisConfiguration(
        private val redisServer: RedisServer = RedisServer(6379)
) {

    @PostConstruct
    fun postConstruct() = redisServer.start()

    @PreDestroy
    fun preDestroy() = redisServer.stop()
}
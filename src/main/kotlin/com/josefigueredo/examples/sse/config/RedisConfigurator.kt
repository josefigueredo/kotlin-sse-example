package com.josefigueredo.examples.sse.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.josefigueredo.examples.sse.model.Tweet
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
class RedisConfigurator {

    @Bean
    fun reactiveRedisTemplate(
            reactiveRedisConnectionFactory: ReactiveRedisConnectionFactory,
            objectMapper: ObjectMapper
    ): ReactiveRedisTemplate<String, Tweet> =
            ReactiveRedisTemplate(
                    reactiveRedisConnectionFactory,
                    RedisSerializationContext
                            .newSerializationContext<String, Tweet>(StringRedisSerializer())
                            .value(
                                    Jackson2JsonRedisSerializer(Tweet::class.java)
                                            .apply {
                                                setObjectMapper(objectMapper)
                                            }
                            )
                            .build()
            )

}
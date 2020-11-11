package com.josefigueredo.examples.sse.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.*

data class Tweet(
        @JsonProperty("uuid")
        val uuid: String = UUID.randomUUID().toString(),
        @JsonProperty("text")
        val text: String,
        @JsonProperty("at")
        val at: ZonedDateTime = ZonedDateTime.now(ZoneId.of("UTC"))
)
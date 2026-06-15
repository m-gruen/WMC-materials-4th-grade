package com.htl.leo.restclient.data

import com.fasterxml.jackson.annotation.JsonProperty

data class Joke(
    @JsonProperty("type") val type: String,
    @JsonProperty("setup") val setup: String,
    @JsonProperty("punchline") val punchline: String,
    @JsonProperty("id") val id: Number
)

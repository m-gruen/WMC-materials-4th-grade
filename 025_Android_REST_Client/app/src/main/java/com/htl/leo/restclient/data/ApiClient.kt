package com.htl.leo.restclient.data

import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

private const val URL = "https://official-joke-api.appspot.com/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(JacksonConverterFactory.create())
    .baseUrl(URL)
    .build()

val jokeService: JokeServiceClient = retrofit.create(JokeServiceClient::class.java)

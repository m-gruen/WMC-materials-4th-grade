package com.htl.leo.restclient.data

import retrofit2.Response
import retrofit2.http.GET

interface JokeServiceClient {
    @GET("random_joke")
    suspend fun getRandomJoke(): Response<Joke>
}

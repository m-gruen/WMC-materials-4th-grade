package com.htl.leo.restclient.data

class JokeRepository {

    suspend fun getJoke(): Joke? {
        val response = jokeService.getRandomJoke()
        if (response.isSuccessful) {
            return response.body()
        } else {
            throw Exception("Network Error ${response.code()}")
        }
    }
}

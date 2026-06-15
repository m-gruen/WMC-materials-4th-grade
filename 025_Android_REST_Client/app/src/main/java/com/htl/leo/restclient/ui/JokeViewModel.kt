package com.htl.leo.restclient.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.htl.leo.restclient.data.JokeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class JokeViewModel(
    private val jokeRepository: JokeRepository
) : ViewModel() {

    private val _joke = MutableStateFlow(":-)")
    val joke = _joke.asStateFlow()

    fun update() {
        viewModelScope.launch {
            try {
                val randomJoke = jokeRepository.getJoke()
                println("joke from api: $randomJoke")
                _joke.value = randomJoke?.setup + " " + randomJoke?.punchline
            } catch (e: Exception) {
                _joke.value = e.message ?: "Error"
            }
        }
    }
}

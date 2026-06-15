package com.htl.leo.restclient.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.htl.leo.restclient.data.JokeRepository

class JokeViewModelFactory(
    private val jokeRepository: JokeRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return JokeViewModel(jokeRepository) as T
    }
}

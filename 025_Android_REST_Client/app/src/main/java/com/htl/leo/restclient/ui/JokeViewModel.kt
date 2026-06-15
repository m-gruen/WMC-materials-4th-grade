package com.htl.leo.restclient.ui

import androidx.lifecycle.ViewModel
import com.htl.leo.restclient.data.JokeRepository

// TODO: Create a class JokeViewModel that extends ViewModel()
//       - Takes a JokeRepository as a constructor parameter
//       - Exposes a StateFlow<String> `joke` with initial value ":-)"
//       - Has a fun update() that launches a coroutine in viewModelScope,
//         fetches a joke from the repository and updates the state
class JokeViewModel(
    private val jokeRepository: JokeRepository
) : ViewModel() {
    // TODO: _joke, joke, update()
}

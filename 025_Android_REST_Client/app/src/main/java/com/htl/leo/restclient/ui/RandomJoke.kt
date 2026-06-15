package com.htl.leo.restclient.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun RandomJoke(jokeViewModel: JokeViewModel) {
    val joke by jokeViewModel.joke.collectAsState()
    Column {
        Text("Random Joke:\n${joke}")
        Button(onClick = { jokeViewModel.update() }) {
            Text("Get random joke...")
        }
    }
}

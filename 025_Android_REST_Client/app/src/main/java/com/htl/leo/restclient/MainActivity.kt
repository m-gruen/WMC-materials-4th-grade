package com.htl.leo.restclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.htl.leo.restclient.data.JokeRepository
import com.htl.leo.restclient.ui.JokeViewModel
import com.htl.leo.restclient.ui.JokeViewModelFactory
import com.htl.leo.restclient.ui.RandomJoke
import com.htl.leo.restclient.ui.theme.RestClientTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RestClientTheme {
                val jokeRepository = JokeRepository()
                val jokeViewModel = viewModel<JokeViewModel>(
                    factory = JokeViewModelFactory(jokeRepository)
                )
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        RandomJoke(jokeViewModel)
                    }
                }
            }
        }
    }
}

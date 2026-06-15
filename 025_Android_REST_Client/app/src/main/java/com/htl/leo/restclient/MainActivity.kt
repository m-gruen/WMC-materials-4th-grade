package com.htl.leo.restclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.htl.leo.restclient.ui.theme.RestClientTheme

// TODO: In onCreate, inside setContent { RestClientTheme { ... } }:
//       - Create a JokeRepository
//       - Obtain a JokeViewModel via viewModel<JokeViewModel>(factory = JokeViewModelFactory(repo))
//       - Wrap RandomJoke(jokeViewModel) in a Scaffold + Column
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // TODO
        }
    }
}

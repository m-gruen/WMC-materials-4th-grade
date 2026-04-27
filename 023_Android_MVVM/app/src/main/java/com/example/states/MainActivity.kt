package com.example.states

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.OptIn
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import android.util.Log
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.states.ui.theme.StatesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StatesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CounterScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

/**
 * Most intuitive solution.
 * - Build - doesn't work
 * - Add logging-line > look in logcat > works
 * - It's not a compose-state, so it doesn't trigger a recomposition
 */
@Composable
fun Variable(modifier: Modifier = Modifier) {
    Log.d("ComposeTest", ">>> Recomposition")
    var counter = 0;
    Column(modifier.fillMaxSize()) {
        Text("counter is $counter")
        Button(onClick = {
            counter++
            Log.d("ComposeTest", "counter = $counter")
        }) {
            Text("counter++")
        }
    }
}

/**
 * MutableState is a compose-state.
 * - Build - doesn't work
 * - Add logging-line > look in logcat > works
 * - It's a compose-state, so it triggers a recomposition
 * - But during recomposition the old value is lost (not remembered)
 */
@SuppressLint("UnrememberedMutableState")
@Composable
fun StateMutable(modifier: Modifier = Modifier) {
    Log.d("ComposeTest", ">>> Recomposition")
    val counter = mutableStateOf(0)
    Column(modifier.fillMaxSize()) {
        Text("counter is ${counter.value}")
        Button(onClick = {
            counter.value++
            Log.d("ComposeTest", "counter = $counter")
        }) {
            Text("counter++")
        }
    }
}

/**
 * We remember the state now.
 * - Works
 * - But on screen-rotation the state is still lost, since the fragment is re-created
 */
@Composable
fun StateRemember(modifier: Modifier = Modifier) {
    Log.d("ComposeTest", ">>> Recomposition")
    val counter = remember { mutableStateOf(0) } // try a screen-rotation now.
    Column(modifier.fillMaxSize()) {
            Text("counter is ${counter.value}")
            Button(onClick = {
                counter.value++
                Log.d("ComposeTest", "counter = $counter")
            }) {
            Text("counter++")
        }
    }
}

/**
 * We remember the state now.
 * - Works
 * - And on screen-rotation the state is remembered
 */
@Composable
fun StateRememberSaveable(modifier: Modifier = Modifier) {
    Log.d("ComposeTest", ">>> Recomposition")
    val counter = rememberSaveable { mutableStateOf(0) }

    Column(modifier.fillMaxSize()) {
        Text("counter is ${counter.value}")
        Button(onClick = {
            counter.value++
            Log.d("ComposeTest", "counter = $counter")
        }) {
            Text("counter++")
        }
    }
}

@Composable
fun CounterScreen(
    modifier: Modifier = Modifier,
    counterViewModel: CounterViewModel = viewModel()
) {
    Column(modifier.fillMaxSize()) {
        Text("counter is ${counterViewModel.counter}")
        Button(onClick = { counterViewModel.increment() }) {
            Text("counter++")
        }
    }
}

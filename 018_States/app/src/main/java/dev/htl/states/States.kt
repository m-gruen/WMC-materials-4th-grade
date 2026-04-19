package dev.htl.states

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier

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

@SuppressLint("UnrememberedMutableState")
@Composable
fun StateMutable(modifier: Modifier = Modifier) {
    Log.d("ComposeTest", ">>> Recomposition")
    val counter = mutableIntStateOf(0)
    Column(modifier.fillMaxSize()) {
        Text("counter is ${counter.intValue}")
        Button(onClick = {
            counter.intValue++
            Log.d("ComposeTest", "counter = $counter")
        }) {
            Text("counter++")
        }
    }
}

@Composable
fun StateRemember(modifier: Modifier = Modifier) {
    Log.d("ComposeTest", ">>> Recomposition")
    val counter = remember { mutableIntStateOf(0) }
    Column(modifier.fillMaxSize()) {
        Text("counter is ${counter.intValue}")
        Button(onClick = {
            counter.intValue++
            Log.d("ComposeTest", "counter = $counter")
        }) {
            Text("counter++")
        }
    }
}

@Composable
fun StateRememberSaveable(modifier: Modifier = Modifier) {
    val counter = rememberSaveable { mutableIntStateOf(0) }
    Column(modifier.fillMaxSize()) {
        Text("counter is ${counter.intValue}")
        Button(onClick = { counter.intValue++ }) {
            Text("counter++")
        }
    }
}

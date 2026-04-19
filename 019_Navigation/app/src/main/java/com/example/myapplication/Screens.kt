package com.example.myapplication

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    title: String,
    onProfileClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement =
            Arrangement.Center,
        horizontalAlignment =
            Alignment.CenterHorizontally
    ) {
        Text(
            title,
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        Button(onClick = onProfileClick) {
            Text("Go to Profile")
        }
    }
}

@Composable
fun ProfileScreen(title: String) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement =
            Arrangement.Center,
        horizontalAlignment =
            Alignment.CenterHorizontally
    ) {
        Text(
            title,
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(
            modifier = Modifier.height(8.dp)
        )
        Text("Welcome to the profile!")
    }
}

@Composable
fun NoteListScreen(
    onNoteClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(5) { index ->
            ListItem(
                headlineContent = {
                    Text("Note #$index")
                },
                modifier = Modifier
                    .clickable {
                        onNoteClick(index)
                    }
            )
        }
    }
}

@Composable
fun NoteDetailScreen(noteId: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement =
            Arrangement.Center,
        horizontalAlignment =
            Alignment.CenterHorizontally
    ) {
        Text(
            "Note #$noteId",
            style = MaterialTheme
                .typography.headlineLarge
        )
        Spacer(
            modifier = Modifier.height(8.dp)
        )
        Text("Content of note $noteId")
    }
}

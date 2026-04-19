package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.navigation3.ListDetailSceneStrategy
import androidx.compose.material3.adaptive.navigation3.rememberListDetailSceneStrategy
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.myapplication.ui.theme.MyApplicationTheme

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                val backStack = rememberNavBackStack(Home("Home"))
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavDisplay(
                        backStack = backStack,
                        onBack = { backStack.removeLastOrNull() },
                        modifier = Modifier.padding(innerPadding),
                        entryProvider = entryProvider {
                            entry<Home> { key ->
                                HomeScreen(
                                    title = key.title,
                                    onProfileClick = {
                                        backStack.add(Profile("Profile"))
                                    }
                                )
                            }
                            entry<Profile> { key ->
                                ProfileScreen(title = key.title)
                            }
                        }
                    )
                }

                val backStackNew = rememberNavBackStack(NoteList)
                val listDetailStrategy = rememberListDetailSceneStrategy<NavKey>()

                NavDisplay(
                    backStack = backStackNew,
                    onBack = { backStackNew.removeLastOrNull() },
                    sceneStrategy = listDetailStrategy,
                    entryProvider = entryProvider {
                        entry<NoteList>(
                            metadata = ListDetailSceneStrategy.listPane()
                        ) {
                            NoteListScreen(
                                onNoteClick = { id -> backStackNew.add(NoteDetail(id)) }
                            )
                        }
                        entry<NoteDetail>(
                            metadata = ListDetailSceneStrategy.detailPane()
                        ) { key ->
                            NoteDetailScreen(key.noteId)
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}

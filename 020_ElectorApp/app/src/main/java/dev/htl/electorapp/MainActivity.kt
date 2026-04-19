package dev.htl.electorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import dev.htl.electorapp.ui.theme.ElectorAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ElectorAppTheme {
                val backStack = rememberNavBackStack(Home)
                var totalCount by remember { mutableIntStateOf(0) }

                NavDisplay(
                    backStack = backStack,
                    onBack = { backStack.removeLastOrNull() },
                    entryProvider = entryProvider {
                        entry<Home> {
                            HomeScreen(
                                onCountClick = { backStack.add(Count) },
                                onOverviewClick = { backStack.add(Overview) },
                                onAboutClick = { backStack.add(About) }
                            )
                        }
                        entry<Count> {
                            CountScreen(
                                onApplyCount = { count ->
                                    totalCount += count
                                    backStack.add(Overview)
                                },
                                onHomeClick = { backStack.add(Home) },
                                onAboutClick = { backStack.add(About) }
                            )
                        }
                        entry<Overview> {
                            OverviewScreen(
                                totalCount = totalCount,
                                onAboutClick = { backStack.add(About) },
                                onBackClick = { backStack.removeLastOrNull() }
                            )
                        }
                        entry<About> {
                            AboutScreen(
                                onBackClick = { backStack.removeLastOrNull() }
                            )
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
    ElectorAppTheme {
        Greeting("Android")
    }
}

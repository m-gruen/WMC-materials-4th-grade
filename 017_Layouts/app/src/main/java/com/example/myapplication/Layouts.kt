package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Layouts(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Layout Example", style = MaterialTheme.typography.headlineSmall)
        Text(text = "another example")

        HorizontalDivider()

        Text(text = "Column Example (Vertical)", style = MaterialTheme.typography.headlineSmall)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(8.dp)
        ) {
            Text("Item 1")
            Text("Item 2")
            Text("Item 3")
        }

        HorizontalDivider()

        Text(text = "Row Example (Horizontal)", style = MaterialTheme.typography.headlineSmall)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Cyan)
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text("Left")
            Text("Center")
            Text("Right")
        }

        HorizontalDivider()

        Text(
            text = "Box Example",
            style = MaterialTheme.typography.headlineSmall,
        )

        Box(
            modifier = Modifier
                .background(Color.Green)
        ) {
            Text("inside the box")
        }

        HorizontalDivider()

        Text(
            text = "Box Example (Stacking)",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.align(Alignment.End)
        )

        Box(
            modifier = Modifier
                .size(150.dp)
                .background(Color.Yellow)
                .align(Alignment.End) // Moves the box to the right side of the column
        ) {
            Text(
                "Bottom Layer",
                modifier = Modifier.align(Alignment.TopStart)
            )
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.Red.copy(alpha = 0.5f))
                    .align(Alignment.Center)
            ) {
                Text(
                    "Top Layer",
                    modifier = Modifier.align(Alignment.BottomEnd)
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Text("This is now, surprisingly, on the bottom of the screen")
    }
}

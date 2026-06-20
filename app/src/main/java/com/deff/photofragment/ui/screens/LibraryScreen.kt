package com.deff.photofragment.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.deff.photofragment.data.model.Story
import com.deff.photofragment.ui.components.StoryCard
import com.deff.photofragment.ui.theme.DarkBg
import com.deff.photofragment.ui.theme.GoldAccent
import com.deff.photofragment.ui.theme.PhotoFragmentTheme
import java.util.*

@Composable
fun LibraryScreen(
    stories: List<Story> = emptyList(),
    onStoryClick: (Story) -> Unit = {},
    onDeleteStory: (Story) -> Unit = {},
    onAddClick: () -> Unit = {}
) {
    Scaffold(
        containerColor = DarkBg,
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddClick,
                containerColor = GoldAccent,
                contentColor = Color.Black,
                shape = RoundedCornerShape(50)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Nueva")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp, vertical = 12.dp)
        ) {
            Text(
                text = "Biblioteca",
                style = MaterialTheme.typography.headlineMedium.copy(color = Color.White),
                modifier = Modifier.padding(bottom = 24.dp)
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(stories, key = { it.id }) { story ->
                    StoryCard(
                        story = story, 
                        onClick = { onStoryClick(story) },
                        onDelete = { onDeleteStory(story) }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LibraryScreenPreview() {
    PhotoFragmentTheme {
        LibraryScreen(
            stories = listOf(
                Story("1", "Fragmento en dorado", "Contenido de la historia...", Date()),
                Story("2", "Otra historia", "Más contenido...", Date())
            ),
            onDeleteStory = {}
        )
    }
}

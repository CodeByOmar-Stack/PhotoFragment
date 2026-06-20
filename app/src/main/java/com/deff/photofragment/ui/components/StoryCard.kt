package com.deff.photofragment.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.deff.photofragment.data.model.Story
import com.deff.photofragment.ui.theme.GoldAccent
import com.deff.photofragment.ui.theme.WarmGray
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun StoryCard(
    story: Story,
    onClick: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1A1917))
    ) {
        Column {
            // Visual Strip (Prompt image or user photo)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
            ) {
                AsyncImage(
                    model = story.imageUrl,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Box(modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.2f)))
            }

            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = story.title.ifBlank { "Sin título" },
                        style = MaterialTheme.typography.titleMedium.copy(color = GoldAccent),
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(onClick = onDelete) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Eliminar",
                            tint = Color.White.copy(alpha = 0.5f)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = story.content,
                    style = MaterialTheme.typography.bodySmall.copy(color = WarmGray),
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(story.date),
                    style = MaterialTheme.typography.labelSmall.copy(color = WarmGray.copy(alpha = 0.7f))
                )
            }
        }
    }
}

package com.deff.photofragment.ui.screens

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.deff.photofragment.ui.theme.DarkBg
import com.deff.photofragment.ui.theme.GoldAccent
import com.deff.photofragment.ui.theme.PhotoFragmentTheme

@Composable
fun EditorScreen(
    imageUri: String? = null,
    onBack: () -> Unit = {},
    onSave: (String, String) -> Unit = { _, _ -> }
) {
    var title by remember { mutableStateOf("") }
    var text by remember { mutableStateOf("") }
    val wordCount = if (text.isBlank()) 0 else text.trim().split("\\s+".toRegex()).size

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBg)
    ) {
        // Top Image Strip (The stimulus image)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        ) {
            AsyncImage(
                model = imageUri,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            // Subtle overlay to keep it dark
            Box(modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.3f)))
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(24.dp)
        ) {
            // Title Field
            BasicTextField(
                value = title,
                onValueChange = { title = it },
                modifier = Modifier.fillMaxWidth(),
                textStyle = MaterialTheme.typography.headlineSmall.copy(color = GoldAccent),
                cursorBrush = SolidColor(GoldAccent),
                singleLine = true,
                decorationBox = { innerTextField ->
                    if (title.isEmpty()) {
                        Text(
                            "Título del fragmento...",
                            style = MaterialTheme.typography.headlineSmall.copy(color = GoldAccent.copy(alpha = 0.3f))
                        )
                    }
                    innerTextField()
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Content Field
            Box(modifier = Modifier.weight(1f)) {
                BasicTextField(
                    value = text,
                    onValueChange = { text = it },
                    modifier = Modifier.fillMaxSize(),
                    textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.White),
                    cursorBrush = SolidColor(GoldAccent),
                    decorationBox = { innerTextField ->
                        if (text.isEmpty()) {
                            Text(
                                "Comienza a escribir...",
                                style = MaterialTheme.typography.bodyLarge.copy(color = Color.White.copy(alpha = 0.3f))
                            )
                        }
                        innerTextField()
                    }
                )

                // Word counter
                Text(
                    text = "$wordCount palabras",
                    style = MaterialTheme.typography.labelSmall.copy(color = Color.White.copy(alpha = 0.5f)),
                    modifier = Modifier.align(Alignment.BottomEnd)
                )
            }
        }

        // Bottom Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(onClick = onBack) {
                Text("Volver", color = Color.White)
            }

            Button(
                onClick = { onSave(title, text) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (text.isNotEmpty()) Color.White else Color.White.copy(alpha = 0.1f),
                    contentColor = if (text.isNotEmpty()) Color.Black else Color.White.copy(alpha = 0.3f)
                ),
                shape = MaterialTheme.shapes.medium
            ) {
                Text("Guardar")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditorScreenPreview() {
    PhotoFragmentTheme {
        EditorScreen()
    }
}

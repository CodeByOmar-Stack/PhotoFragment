package com.deff.photofragment.ui.screens

import android.net.Uri
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Collections
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.deff.photofragment.data.model.FragmentPrompt
import com.deff.photofragment.ui.theme.GlassWhite
import com.deff.photofragment.ui.theme.GoldAccent
import com.deff.photofragment.ui.theme.PhotoFragmentTheme

@Composable
fun HomeScreen(
    prompt: FragmentPrompt?,
    customImageUri: Uri? = null,
    currentStep: Int = 0,
    totalSteps: Int = 5,
    onWriteClick: () -> Unit = {},
    onLibraryClick: () -> Unit = {},
    onCameraClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        // Full Screen Image (With Animation)
        AnimatedContent(
            targetState = customImageUri ?: prompt?.imageUrl,
            transitionSpec = {
                fadeIn(animationSpec = tween(1500)) togetherWith fadeOut(animationSpec = tween(1500))
            },
            label = "ImageTransition"
        ) { targetImage ->
            AsyncImage(
                model = targetImage,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        // Gradient Overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.8f)),
                        startY = 300f
                    )
                )
        )

        // Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Prompt Text
            prompt?.let {
                Text(
                    text = it.text,
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Light,
                        letterSpacing = 0.5.sp
                    ),
                    modifier = Modifier.padding(bottom = 32.dp),
                    textAlign = TextAlign.Center
                )
            }

            // Progress dots
            Row(
                modifier = Modifier.padding(bottom = 32.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                repeat(totalSteps) { index ->
                    Box(
                        modifier = Modifier
                            .size(if (index == currentStep) 8.dp else 6.dp)
                            .background(
                                color = if (index == currentStep) GoldAccent else Color.White.copy(alpha = 0.3f),
                                shape = RoundedCornerShape(50)
                            )
                    )
                }
            }

            // Buttons
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(bottom = 32.dp)
            ) {
                GlassButton(
                    text = "Crear Historia",
                    onClick = onWriteClick,
                    modifier = Modifier.width(200.dp)
                )
                
                Spacer(modifier = Modifier.height(16.dp))

                IconButton(
                    onClick = onCameraClick,
                    modifier = Modifier
                        .size(56.dp)
                        .background(GlassWhite, RoundedCornerShape(50))
                ) {
                    Icon(
                        imageVector = Icons.Default.PhotoCamera,
                        contentDescription = "Hacer Foto",
                        tint = Color.White
                    )
                }
            }
        }

        // Library Button (Top Right)
        IconButton(
            onClick = onLibraryClick,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .statusBarsPadding()
                .padding(16.dp)
                .background(GlassWhite, RoundedCornerShape(50))
        ) {
            Icon(
                imageVector = Icons.Default.Collections,
                contentDescription = "Biblioteca",
                tint = Color.White
            )
        }
    }
}

@Composable
fun GlassButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(56.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = GlassWhite,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(12.dp),
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Medium,
                letterSpacing = 1.sp
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    PhotoFragmentTheme {
        HomeScreen(
            prompt = FragmentPrompt("1", "Una estación vacía de noche", ""),
            currentStep = 0
        )
    }
}

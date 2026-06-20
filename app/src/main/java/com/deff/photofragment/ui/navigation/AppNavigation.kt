package com.deff.photofragment.ui.navigation

import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.delay
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.deff.photofragment.ui.screens.EditorScreen
import com.deff.photofragment.ui.screens.HomeScreen
import com.deff.photofragment.ui.screens.LibraryScreen
import com.deff.photofragment.data.model.Story
import com.deff.photofragment.data.repository.PromptRepository
import com.deff.photofragment.data.repository.StoryRepository
import com.deff.photofragment.data.database.AppDatabase
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.util.*

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Editor : Screen("editor")
    object Library : Screen("library")
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    
    // Repository initialization
    val repository = remember { 
        val db = AppDatabase.getDatabase(context)
        StoryRepository(db.storyDao())
    }
    
    val stories by repository.allStories.collectAsState(initial = emptyList())
    
    // Prompts state
    var currentPromptIndex by remember { mutableStateOf(0) }
    val currentPrompt = PromptRepository.prompts[currentPromptIndex]
    
    // Gallery/Camera Image state
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    // Auto-cycle prompts every 6 seconds
    LaunchedEffect(selectedImageUri) {
        if (selectedImageUri == null) {
            while (true) {
                delay(3000)
                currentPromptIndex = (currentPromptIndex + 1) % PromptRepository.prompts.size
            }
        }
    }
    
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            selectedImageUri = uri
        }
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap: Bitmap? ->
        if (bitmap != null) {
            // Helper to get Uri from Bitmap
            val bytes = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
            val path = MediaStore.Images.Media.insertImage(context.contentResolver, bitmap, "Title", null)
            selectedImageUri = Uri.parse(path)
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            cameraLauncher.launch()
        }
    }

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(
                prompt = currentPrompt,
                customImageUri = selectedImageUri,
                currentStep = currentPromptIndex,
                totalSteps = PromptRepository.prompts.size,
                onWriteClick = { 
                    galleryLauncher.launch("image/*")
                },
                onLibraryClick = {
                    navController.navigate(Screen.Library.route)
                },
                onCameraClick = {
                    permissionLauncher.launch(android.Manifest.permission.CAMERA)
                }
            )

            // Effect to navigate to editor once an image is picked or taken
            LaunchedEffect(selectedImageUri) {
                if (selectedImageUri != null) {
                    navController.navigate(Screen.Editor.route)
                }
            }
        }
        composable(Screen.Editor.route) {
            EditorScreen(
                imageUri = selectedImageUri?.toString() ?: currentPrompt.imageUrl,
                onBack = { 
                    selectedImageUri = null
                    navController.popBackStack() 
                },
                onSave = { title, content ->
                    if (content.isNotBlank()) {
                        scope.launch {
                            repository.insert(
                                Story(
                                    id = UUID.randomUUID().toString(),
                                    title = title,
                                    content = content,
                                    date = Date(),
                                    imageUrl = selectedImageUri?.toString() ?: currentPrompt.imageUrl
                                )
                            )
                        }
                        navController.navigate(Screen.Library.route) {
                            popUpTo(Screen.Home.route)
                        }
                        selectedImageUri = null // Reset for next time
                    }
                }
            )
        }
        composable(Screen.Library.route) {
            LibraryScreen(
                stories = stories,
                onStoryClick = { /* Handle story selection if needed */ },
                onDeleteStory = { story ->
                    scope.launch {
                        repository.delete(story)
                    }
                },
                onAddClick = {
                    navController.navigate(Screen.Home.route)
                }
            )
        }
    }
}

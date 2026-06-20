package com.deff.photofragment.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "stories")
data class Story(
    @PrimaryKey val id: String,
    val title: String,
    val content: String,
    val date: Date,
    val imageUrl: String? = null
)

data class FragmentPrompt(
    val id: String,
    val text: String,
    val imageUrl: String
)

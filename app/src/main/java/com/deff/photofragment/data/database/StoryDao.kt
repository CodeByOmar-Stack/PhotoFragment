package com.deff.photofragment.data.database

import androidx.room.*
import com.deff.photofragment.data.model.Story
import kotlinx.coroutines.flow.Flow

@Dao
interface StoryDao {
    @Query("SELECT * FROM stories ORDER BY date DESC")
    fun getAllStories(): Flow<List<Story>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStory(story: Story): Unit

    @Delete
    suspend fun deleteStory(story: Story): Unit
}

package com.deff.photofragment.data.repository

import com.deff.photofragment.data.database.StoryDao
import com.deff.photofragment.data.model.Story
import kotlinx.coroutines.flow.Flow

class StoryRepository(private val storyDao: StoryDao) {
    val allStories: Flow<List<Story>> = storyDao.getAllStories()

    suspend fun insert(story: Story) {
        storyDao.insertStory(story)
    }

    suspend fun delete(story: Story) {
        storyDao.deleteStory(story)
    }
}

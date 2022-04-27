package com.example.aad_engadget_app.database

import androidx.lifecycle.LiveData
import com.example.aad_engadget_app.NewsItem
import com.example.aad_engadget_app.NewsItemDao

class Repository (private val entryDao: NewsItemDao) {

    val entries: LiveData<List<NewsItem>> = entryDao.entries

    suspend fun insert(entry: NewsItem){
        entryDao.insert(entry)
    }

    suspend fun delete(entry: NewsItem){
        entryDao.delete(entry)
    }

    suspend fun deleteAll() {
        entryDao.deleteAll()
    }
}
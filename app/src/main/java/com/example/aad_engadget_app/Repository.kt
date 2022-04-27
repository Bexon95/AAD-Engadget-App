package com.example.aad_engadget_app

import androidx.lifecycle.LiveData

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
package com.example.aad_engadget_app

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NewsItemDao {

    //in entries steht immer der output von diesem query drin (dran koppeln)
    @get:Query("SELECT * FROM articles ORDER BY publicationDate")
    val entries: LiveData<List<NewsItem>>

    @Insert
    suspend fun insert(entry: NewsItem)
    @Update
    suspend fun update(entry: NewsItem)
    @Delete
    suspend fun delete(entry: NewsItem)

}
package com.example.aad_engadget_app

import androidx.annotation.NonNull
import androidx.room.*
import java.io.Serializable
import java.util.*

@Entity(tableName = "articles", indices = [Index(value = ["title"], unique = true)])
data class NewsItem (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "_id") var id : Long? = null,
    @ColumnInfo @NonNull var title:String,
    @ColumnInfo @NonNull val description: String,
    @ColumnInfo @NonNull val imageUrl: String,
    @ColumnInfo @NonNull val author: String,
    @ColumnInfo @NonNull val publicationDate: Date,
    @ColumnInfo @NonNull val articleLink: String,
    @ColumnInfo @NonNull val keywords: String) {

    @Ignore
    constructor(title:String, description: String, imageUrl: String,
                author: String, publicationDate: Date, articleLink: String,
                keywords: String): this(null, title, description, imageUrl, author, publicationDate, articleLink, keywords)

}





//class NewsItem(var identifier : String,
//               var title : String,
//               var link : String?,
//               var description : String?,
//               var imageUrl : String?,
//               var author : String?,
//               var publicationDate : String,
//               var keywords : Set<String>) :
//               //var keywords : String)  :
//    Serializable {
//
//    }

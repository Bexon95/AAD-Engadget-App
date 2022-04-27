package com.example.aad_engadget_app

import androidx.annotation.NonNull
import androidx.room.*
import java.io.Serializable

@Entity(tableName = "newsItems", indices = [Index(value = ["title"], unique = true)])
data class NewsItem(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "_id") var id: Long? = null,
    @ColumnInfo @NonNull var identifier: String?,
    @ColumnInfo @NonNull var title: String?,
    @ColumnInfo @NonNull var link: String?,
    @ColumnInfo @NonNull val description: String?,
    @ColumnInfo @NonNull val imageUrl: String?,
    @ColumnInfo @NonNull val author: String?,
    @ColumnInfo @NonNull val publicationDate: String?,
    //@ColumnInfo @NonNull val keywords: Set<String>
    @ColumnInfo @NonNull val keywords: String?
) : Serializable {

    @Ignore
    constructor(
        identifier: String?,
        title: String?,
        link: String?,
        description: String?,
        imageUrl: String?,
        author: String?,
        publicationDate: String?,
        //keywords: Set<String>
        keywords: String?
    ) : this(null, identifier, title, link, description, imageUrl, author, publicationDate, keywords)

}

//@Entity
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

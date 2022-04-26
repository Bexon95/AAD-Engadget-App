package com.example.aad_engadget_app

import java.io.Serializable

class NewsItem(var identifier : String,
               var title : String,
               var link : String?,
               var description : String?,
               var imageUrl : String?,
               var author : String?,
               var publicationDate : String,
               var keywords : Set<String>) :
               //var keywords : String)  :
    Serializable {

    }

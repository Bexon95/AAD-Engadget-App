package com.example.aad_engadget_app

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//im entities array stehen alle unsere db elemente drin (im moment nur eines)
@Database(entities = [NewsItem::class], version = 1, )
abstract class ApplicationDataBase : RoomDatabase(){ //: heißt hier erweitert RoomDatabase()

    //DAO = Data acces object oder so
    abstract fun newsItemDao(): NewsItemDao

    //companion object ist wie static in java
    //darauf kann man von überall zugreifen
    companion object{
        @Volatile
        //man muss aufpassen, dass nur eine instanz davon existiert und wird wieder verowrfen, wenns ned gebraucht wird
        private var INSTANCE: ApplicationDataBase? = null

        //erstellt eine Instanz von der Datenbank - Singleton
        fun getDatabase(context: Context): ApplicationDataBase{
            val instance = Room.databaseBuilder(context.applicationContext,
                ApplicationDataBase::class.java,
                "newsItem").build()
            INSTANCE = instance
            return instance
        }
    }
}
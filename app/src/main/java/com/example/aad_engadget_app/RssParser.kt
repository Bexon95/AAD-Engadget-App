package com.example.aad_engadget_app
import android.content.Context
import org.xmlpull.v1.XmlPullParser

import android.util.Xml

import org.xmlpull.v1.XmlPullParserException

import java.io.IOException
import java.io.InputStream
import java.text.ParseException

import android.util.Log
import android.widget.Toast
import com.prof.rssparser.Parser
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets
import java.text.SimpleDateFormat
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

class RssParser(var appContext: Context, var urlString: String) {
    companion object {
        val LOG_TAG: String = RssParser::class.java.canonicalName ?: "RssParser"
        val ns: String? = null
    }


    //taken from https://stackoverflow.com/questions/64652057/parse-rss-xml-with-kotlin
    @Throws(XmlPullParserException::class, IOException::class, ParseException::class)
    fun parse(inputStream: InputStream): List<NewsItem> {
        val parser = Parser.Builder()
            .context(appContext)
            .charset(Charset.forName(StandardCharsets.UTF_8.name()))
            .cacheExpirationMillis(24L * 60L * 60L * 100L)
            .build()

        val titleList = arrayListOf<String>()
        val articleList = mutableSetOf<NewsItem>()

        runBlocking {
            launch {
                Log.d(LOG_TAG, "This is the url $urlString")
                val channel = parser.getChannel(url = urlString)
                //val channel = parser.getChannel(url = "https://www.engadget.com/rss.xml")
                Log.d("MyChannel", channel.toString())

                for (art in channel.articles) {
                    titleList.add(art.title!!)
                    articleList.add(
                        NewsItem(
                            art.guid!!,
                            art.title!!,
                            art.link!!,
                            art.description!!,
                            art.image!!,
                            art.author!!,
                            art.pubDate!!,
                            art.categories!!.toString()
                        )
                    )
                }
            }
        }
        var tempList = articleList.toList()

        //Toast.makeText(appContext, R.string.articles_loaded, Toast.LENGTH_SHORT).show()

        return tempList
    }
}
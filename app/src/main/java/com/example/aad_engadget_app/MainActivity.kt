package com.example.aad_engadget_app

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.descriptors.PrimitiveKind
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.text.ParseException

class MainActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {


    companion object {
        val LOG_TAG: String = MainActivity::class.java.canonicalName ?: "RssParser"
    }

    private var adapter: ListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.rv_list)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)


        adapter = ListAdapter()
        recyclerView.adapter = adapter

        adapter?.itemClickListener = {
            Toast.makeText(this, it.author + " was clicked.", Toast.LENGTH_SHORT).show()

            val intent = Intent(this,DetailActivity::class.java)
            intent.putExtra(DetailActivity.NEWS_ITEM_KEY, it)
            startActivity(intent)

        }

        lifecycleScope.launchWhenStarted {
            //load("https://www.engadget.com/rss.xml")
            load(getString(R.string.settings_newsfeed_url))
        }

        var generateButton = findViewById<Button>(R.id.btn_generate)
        generateButton.setOnClickListener {
            lifecycleScope.launchWhenStarted {
                //load("https://www.engadget.com/rss.xml")
                load(getString(R.string.settings_newsfeed_url))
            }
        }

    }

    suspend fun load(urlString: String) {
        Log.d(LOG_TAG, "the url is: $urlString")
        val result = withContext(Dispatchers.IO) {
            loadInt(urlString)
        }

        if (result != null) {
            adapter?.items = withContext(Dispatchers.Default) {
                result
            }
        }
    }

    private fun loadInt(urlString: String) : List<NewsItem>? {
        return try {
            Log.d(LOG_TAG, "Start downloading $urlString ...")
            val url = URL(urlString)
            val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
            try {
                urlConnection.connectTimeout = 5000
                if (urlConnection.responseCode != HttpURLConnection.HTTP_OK) {
                    Log.w(
                        LOG_TAG,
                        "Error opening RSS feed, Error code: ${urlConnection.responseCode}"
                    )
                    return null
                }
                Log.d(LOG_TAG, "Start parsing ...")
                val parser = RssParser()
                val result = parser.parse(urlConnection.inputStream)
                Log.d(LOG_TAG, "Parsing finished.")
                result
            } finally {
                urlConnection.disconnect()
            }
        } catch (ex: MalformedURLException) {
            Log.w(
                LOG_TAG,
                String.format("Error opening RSS feed, Feed %1\$s url invalid.", urlString),
                ex
            )
            null
        } catch (ex: IOException) {
            Log.w(LOG_TAG, "Error reading RSS feed.", ex)
            null
        } catch (ex: ParseException) {
            Log.w(LOG_TAG, "Error parsing RSS feed.", ex)
            null
        } catch (ex: XmlPullParserException) {
            Log.w(LOG_TAG, "Error parsing RSS feed.", ex)
            null
        }
    }



    //Settings Menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean { //warum kein "menu: Menu?" mehr?
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemId: Int = item.itemId
        if (itemId == R.id.action_settings) {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
//        if (key.equals(getString(R.string.settings_newsfeed_url))){
//            loadContent()
//        }
        //load("https://www.engadget.com/rss.xml")
    }


    override fun onDestroy() {
        super.onDestroy()
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }
}
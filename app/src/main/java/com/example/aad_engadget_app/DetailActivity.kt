package com.example.aad_engadget_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DetailActivity : AppCompatActivity() {

    companion object {
        const val NEWS_ITEM_KEY = "NEWS_ITEM_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val textViewNewsItemTitle = findViewById<TextView>(R.id.tv_news_item_title)
        val textViewNewsItemAuthor = findViewById<TextView>(R.id.tv_news_item_author)
        textViewNewsItemTitle.text = (intent.getSerializableExtra(NEWS_ITEM_KEY) as? NewsItem)?.title
        textViewNewsItemAuthor.text = (intent.getSerializableExtra(NEWS_ITEM_KEY) as? NewsItem)?.author

    }
}
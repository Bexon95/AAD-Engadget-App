package com.example.aad_engadget_app

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    companion object {
        const val NEWS_ITEM_KEY = "NEWS_ITEM_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val textViewNewsItemId = findViewById<TextView>(R.id.tv_news_item_identifier)
        val textViewNewsItemTitle = findViewById<TextView>(R.id.tv_news_item_title)
        val textViewNewsItemDescription = findViewById<TextView>(R.id.tv_news_item_description)
        val textViewNewsItemImageUrl = findViewById<TextView>(R.id.tv_news_item_image_url)
        val textViewNewsItemAuthor = findViewById<TextView>(R.id.tv_news_item_author)
        val textViewNewsItemPublicationDate = findViewById<TextView>(R.id.tv_news_item_publication_date)
        val textViewNewsItemArticleLink = findViewById<TextView>(R.id.tv_news_item_article_link)
        val textViewNewsItemKeywords = findViewById<TextView>(R.id.tv_news_item_keywords)
        val btnFullStory = findViewById<Button>(R.id.btn_full_story)

        val mItemTitleTextView = findViewById<TextView>(R.id.tv_item_title)
        val mItemAuthorTextView = findViewById<TextView>(R.id.tv_item_author)
        val mItemDateTextView = findViewById<TextView>(R.id.tv_item_date)

        textViewNewsItemId.text = (intent.getSerializableExtra(NEWS_ITEM_KEY) as? NewsItem)?.identifier.toString()
        textViewNewsItemTitle.text = (intent.getSerializableExtra(NEWS_ITEM_KEY) as? NewsItem)?.title
        textViewNewsItemDescription.text = Html.fromHtml((intent.getSerializableExtra(NEWS_ITEM_KEY) as? NewsItem)?.description)
        textViewNewsItemImageUrl.text = (intent.getSerializableExtra(NEWS_ITEM_KEY) as? NewsItem)?.imageUrl
        textViewNewsItemAuthor.text = (intent.getSerializableExtra(NEWS_ITEM_KEY) as? NewsItem)?.author
        textViewNewsItemPublicationDate.text = (intent.getSerializableExtra(NEWS_ITEM_KEY) as? NewsItem)?.publicationDate
        textViewNewsItemArticleLink.text = (intent.getSerializableExtra(NEWS_ITEM_KEY) as? NewsItem)?.link
        textViewNewsItemKeywords.text = (intent.getSerializableExtra(NEWS_ITEM_KEY) as? NewsItem)?.keywords

        val imageViewHeader: ImageView = findViewById<ImageView>(R.id.iv_item_thumbnail)
        mItemTitleTextView.text = (intent.getSerializableExtra(NEWS_ITEM_KEY) as? NewsItem)?.title
        mItemAuthorTextView.text = (intent.getSerializableExtra(NEWS_ITEM_KEY) as? NewsItem)?.author
        mItemDateTextView.text = (intent.getSerializableExtra(NEWS_ITEM_KEY) as? NewsItem)?.publicationDate
        Glide.with(this)
            .load((intent.getSerializableExtra(NEWS_ITEM_KEY) as? NewsItem)?.imageUrl)
            .into(imageViewHeader)

        btnFullStory.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(textViewNewsItemArticleLink.text as String?))
            startActivity(browserIntent)
        }

    }
}
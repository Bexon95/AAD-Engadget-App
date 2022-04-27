package com.example.aad_engadget_app

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.lang.IllegalArgumentException
import com.bumptech.glide.Glide

class ListAdapter(items: List<NewsItem>? = listOf<NewsItem>()) :
    RecyclerView.Adapter<ListAdapter.ItemViewHolder>() {
    companion object {
        const val VIEW_TYPE_TOP = 0
        const val VIEW_TYPE_NORMAL = 1
    }

    var items = items
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var itemClickListener: ((NewsItem) -> Unit)? = null

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mItemTitleTextView = itemView.findViewById<TextView>(R.id.tv_item_title)
        private val mItemAuthorTextView = itemView.findViewById<TextView>(R.id.tv_item_author)
        private val mItemDateTextView = itemView.findViewById<TextView>(R.id.tv_item_date)
        private val mItemThumbnail = itemView.findViewById<ImageView>(R.id.iv_item_thumbnail)
        //private val mItemHeader = itemView.findViewById<ImageView>(R.id.iv_item_header) //does not yet work
        //private val mItemNameTextView = itemView.findViewById<TextView>(R.id.tv_item_name)
        private val mLayout = itemView.findViewById<LinearLayout>(R.id.ll_view)
        private val mLayoutTop = itemView.findViewById<FrameLayout>(R.id.ll_view_top)

        init {
            itemView.setOnClickListener { itemClickListener?.invoke(items!![absoluteAdapterPosition]) }
        }

        fun bind(index: Int) {
            mItemTitleTextView.text = items!![index].title
            mItemAuthorTextView.text = items!![index].author
            mItemDateTextView.text = items!![index].publicationDate
            Glide.with(itemView.context)
                .load(items!![index].imageUrl)
                .override(300, 300)
                .into(mItemThumbnail)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val context = parent.context
        val layoutIdForListItem = when (viewType) {
            VIEW_TYPE_TOP -> {
                R.layout.list_item_top
            }
            VIEW_TYPE_NORMAL -> {
                R.layout.list_item
            }
            else -> {
                throw IllegalArgumentException("Invalid View Type")
            }
        }
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(layoutIdForListItem, parent, false);
        return ItemViewHolder(view)

    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        Log.d("POSITION: ", position.toString())
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return items!!.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            VIEW_TYPE_TOP
        } else {
            VIEW_TYPE_NORMAL
        }
    }


}
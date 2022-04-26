package com.example.aad_engadget_app

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.lang.IllegalArgumentException

class ListAdapter(items: List<NewsItem>? = listOf<NewsItem>()) : RecyclerView.Adapter<ListAdapter.ItemViewHolder>() {
companion object {
    const val VIEW_TYPE_TOP = 0
    const val VIEW_TYPE_NORMAL = 1
}

    var items = items
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var itemClickListener : ((NewsItem)->Unit)? = null

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val mItemNameTextView = itemView.findViewById<TextView>(R.id.tv_item_name)
        private val mLayout = itemView.findViewById<LinearLayout>(R.id.ll_view)

        init {
            itemView.setOnClickListener{itemClickListener?.invoke(items!![absoluteAdapterPosition])}
        }

        fun bind(index: Int){
            mItemNameTextView.text = items!![index].title
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val context = parent.context
        val layoutIdForListItem = when (viewType){
            VIEW_TYPE_TOP ->{
                R.layout.list_item_top
            }
            VIEW_TYPE_NORMAL -> {
                R.layout.list_item
            }
            else -> { throw IllegalArgumentException("Invalid View Type") }
        }
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(layoutIdForListItem,parent, false);
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
        return if(position == 0){
            VIEW_TYPE_TOP
        } else {
            VIEW_TYPE_NORMAL
        }
    }



}
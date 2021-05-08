package com.davit.kotlin.volonewstask.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.davit.kotlin.volonewstask.R
import com.davit.kotlin.volonewstask.database.NewsEntity
import com.davit.kotlin.volonewstask.models.NewsModel

class SavedNewsAdapter(private val context: Context, private val savedNews: List<NewsEntity>): RecyclerView.Adapter<SavedNewsAdapter.SavedNewsViewHolder>()  {

    //

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedNewsViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.saved_news_item,parent,false)
        return SavedNewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: SavedNewsViewHolder, position: Int) {
        val currentItem = savedNews[holder.adapterPosition]
        Glide.with(context)
            .load(currentItem.imageUrl)
            .error(R.drawable.ic_launcher_foreground)
            .into(holder.news_image)

        holder.news_title.text = currentItem.title
        holder.news_description.text = currentItem.summary

    }

    override fun getItemCount(): Int {
        return savedNews.size
    }

    class SavedNewsViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val news_image: ImageView = view.findViewById(R.id.saved_news_image)
        val news_title: TextView = view.findViewById(R.id.saved_news_title)
        val news_description: TextView = view.findViewById(R.id.saved_news_description)
    }
}
package com.davit.kotlin.volonewstask.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.davit.kotlin.volonewstask.R
import com.davit.kotlin.volonewstask.models.NewsModelItem

class NewsPagedListAdapter constructor(private val context:Context) : PagedListAdapter<NewsModelItem,NewsPagedListAdapter.NewsViewHolder>(NEWS_COMPARATOR) {

    private var starClickListener:OnStarClickListener? = null

    interface OnStarClickListener{
        fun starClick(item:NewsModelItem)
    }

    fun setOnStarClickListener(listener:OnStarClickListener){
        this.starClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recyclerview_news_item,parent,false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem: NewsModelItem? = getItem(holder.adapterPosition)
        Glide.with(context)
            .load(currentItem?.imageUrl)
            .error(R.drawable.ic_launcher_foreground)
            .into(holder.news_image)

        holder.news_title.text = currentItem?.title
        holder.news_description.text = currentItem?.summary

        holder.star.setOnClickListener {
            starClickListener?.let {
                currentItem?.let {
                    starClickListener?.starClick(currentItem)
                }
                holder.star.setImageResource(R.drawable.ic_star_filled)
            }
        }
    }


    class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val news_image: ImageView = view.findViewById(R.id.news_image)
        val news_title: TextView = view.findViewById(R.id.news_title)
        val news_description: TextView = view.findViewById(R.id.news_description)
        val star: ImageView = view.findViewById(R.id.star)
    }

    companion object{
        private val NEWS_COMPARATOR = object:DiffUtil.ItemCallback<NewsModelItem>(){
            override fun areItemsTheSame(oldItem: NewsModelItem, newItem: NewsModelItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: NewsModelItem, newItem: NewsModelItem): Boolean {
                return true
            }

        }
    }
}
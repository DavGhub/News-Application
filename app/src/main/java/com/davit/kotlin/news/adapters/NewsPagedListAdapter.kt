package com.davit.kotlin.news.adapters

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
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.davit.kotlin.news.R
import com.davit.kotlin.news.models.NewsModelItem

class NewsPagedListAdapter constructor(private val context:Context) : PagedListAdapter<NewsModelItem,NewsPagedListAdapter.NewsViewHolder>(NEWS_COMPARATOR) {

    private var starClickListener:OnStarClickListener? = null
    private val favoritesList = mutableSetOf<Int>()

    interface OnStarClickListener{
        fun starClick(item:NewsModelItem,startTag:Int)
    }

    fun setOnStarClickListener(listener:OnStarClickListener){
        this.starClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recyclerview_news_item,parent,false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentPosition = holder.adapterPosition
        val currentItem: NewsModelItem? = getItem(currentPosition)
        Glide.with(context)
            .load(currentItem?.imageUrl)
            .error(R.drawable.ic_launcher_foreground)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .into(holder.news_image)

        holder.news_title.text = currentItem?.title
        holder.news_description.text = currentItem?.summary
        holder.star.tag = 1

        holder.star.setOnClickListener {
            starClickListener?.let {
                if(holder.star.tag == 1){
                    holder.star.setImageResource(R.drawable.ic_star_filled)
                    holder.star.tag = 2
                    favoritesList.add(currentPosition)
                }else{
                    holder.star.setImageResource(R.drawable.ic_star_not_filled)
                    holder.star.tag = 1
                    favoritesList.remove(currentPosition)
                }
                val tag:Int = (holder.star.tag) as Int
                currentItem?.let {
                    starClickListener?.starClick(currentItem,tag)
                }
            }
        }

        if(favoritesList.contains(currentPosition)){
            holder.star.setImageResource(R.drawable.ic_star_filled)
        }else{
            holder.star.setImageResource(R.drawable.ic_star_not_filled)
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
                return oldItem == newItem
            }

        }
    }
}
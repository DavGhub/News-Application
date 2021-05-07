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
import com.davit.kotlin.volonewstask.models.NewsModel
import com.davit.kotlin.volonewstask.models.NewsModelItem

class NewsAdapter(private val context: Context, private val newsModel: NewsModel): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

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
        val currentItem = newsModel[holder.adapterPosition]
        Glide.with(context)
            .load(currentItem.imageUrl)
            .error(R.drawable.ic_launcher_foreground)
            .into(holder.news_image)

        holder.news_title.text = currentItem.title
        holder.news_description.text = currentItem.summary

        holder.star.setOnClickListener {
            starClickListener?.let {
                starClickListener?.starClick(currentItem)
                if(holder.star.drawable == context.getDrawable(R.drawable.ic_star_filled)){
                    Glide.with(context)
                        .load(R.drawable.ic_star_not_filled)
                        .into(holder.star)
                }else{
                    Glide.with(context)
                        .load(R.drawable.ic_star_filled)
                        .into(holder.star)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return newsModel.size
    }

    class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val news_image: ImageView = view.findViewById(R.id.news_image)
        val news_title: TextView = view.findViewById(R.id.news_title)
        val news_description: TextView = view.findViewById(R.id.news_description)
        val star:ImageView = view.findViewById(R.id.star)
    }
}
package com.davit.kotlin.news.pages

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.davit.kotlin.news.adapters.NewsPagedListAdapter
import com.davit.kotlin.news.databinding.FragmentLiveNewsBinding
import com.davit.kotlin.news.models.NewsModelItem
import com.davit.kotlin.news.viewmodels.NewsViewModel


class LiveNewsFragment : Fragment(),
    NewsPagedListAdapter.OnStarClickListener {

    private lateinit var binding: FragmentLiveNewsBinding
    private lateinit var newsViewModel: NewsViewModel
//    private lateinit var newsAdapter: NewsAdapter
    private lateinit var newsPagedAdapter:NewsPagedListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentLiveNewsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

        setupObservers()
        initNewsPagedAdapter()

//        newsViewModel.fetchNews()
    }

    private fun setupObservers(){
        newsViewModel.newsLiveData.observe(this, {
//            initNewsAdapter(it)
//            Log.e("LiveNews","updated data: $it")
        })

        newsViewModel.getPagedLiveData().observe(this,{
            newsPagedAdapter.submitList(it)
        })

        newsViewModel.failRequestLiveData.observe(this, {
            Log.e("FailNews","Message:$it")
        })
    }

//    private fun initNewsAdapter(newsModel: NewsModel){
//        newsAdapter = NewsAdapter(requireActivity(),newsModel)
//        newsAdapter.setOnStarClickListener(this)
//        val layoutManager = LinearLayoutManager(requireActivity())
//        binding.newsRecyclerview.layoutManager = layoutManager
//        binding.newsRecyclerview.adapter = newsAdapter
//    }

    private fun initNewsPagedAdapter(){
        newsPagedAdapter = NewsPagedListAdapter(requireActivity())
        newsPagedAdapter.setOnStarClickListener(this)
        val layoutManager = LinearLayoutManager(requireActivity())
        binding.newsRecyclerview.layoutManager = layoutManager
        binding.newsRecyclerview.adapter = newsPagedAdapter
    }

    override fun starClick(item: NewsModelItem,startTag:Int) {
        if(startTag == 2){
            newsViewModel.insertData(requireActivity(),item)
        }else{
            newsViewModel.deleteNewsFromFavorites(requireActivity(),item)
        }
    }
}
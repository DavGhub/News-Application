package com.davit.kotlin.volonewstask.pages

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.davit.kotlin.volonewstask.adapters.NewsAdapter
import com.davit.kotlin.volonewstask.databinding.FragmentLiveNewsBinding
import com.davit.kotlin.volonewstask.models.NewsModel
import com.davit.kotlin.volonewstask.models.NewsModelItem
import com.davit.kotlin.volonewstask.viewmodels.NewsViewModel


class LiveNewsFragment : Fragment(), NewsAdapter.OnStarClickListener {

    private lateinit var binding:FragmentLiveNewsBinding
    private lateinit var newsViewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentLiveNewsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

        setupObservers()

        newsViewModel.fetchNews()
    }

    private fun setupObservers(){
        newsViewModel.newsLiveData.observe(this, {
            initNewsAdapter(it)
            Log.e("LiveNews","updated data: $it")
        })

        newsViewModel.failRequestLiveData.observe(this, {
            Log.e("FailNews","Message:$it")
        })
    }

    private fun initNewsAdapter(newsModel: NewsModel){
        newsAdapter = NewsAdapter(requireActivity(),newsModel)
        newsAdapter.setOnStarClickListener(this)
        val layoutManager = LinearLayoutManager(requireActivity())
        binding.newsRecyclerview.layoutManager = layoutManager
        binding.newsRecyclerview.adapter = newsAdapter
    }

    override fun starClick(item: NewsModelItem) {
        // insert to database
        newsViewModel.insertData(requireActivity(),item)
    }
}
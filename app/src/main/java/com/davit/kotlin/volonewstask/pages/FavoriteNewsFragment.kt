package com.davit.kotlin.volonewstask.pages

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.davit.kotlin.volonewstask.adapters.SavedNewsAdapter
import com.davit.kotlin.volonewstask.database.NewsEntity
import com.davit.kotlin.volonewstask.databinding.FragmentFavoriteNewsBinding
import com.davit.kotlin.volonewstask.viewmodels.NewsViewModel


class FavoriteNewsFragment : Fragment() {

    //

    private lateinit var binding:FragmentFavoriteNewsBinding
    private lateinit var newsViewModel: NewsViewModel
    private lateinit var savedNewsAdapter:SavedNewsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFavoriteNewsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

        setupObserver()
    }

    private fun setupObserver(){
        newsViewModel.getSavedNewsLiveData(requireActivity())?.observe(this, {
            initSavedNewsAdapter(it)
            Log.e("SavedNews","saved data: $it")
        })
    }

    private fun initSavedNewsAdapter(savedNews: List<NewsEntity>){
        savedNewsAdapter = SavedNewsAdapter(requireActivity(),savedNews)
        val layoutManager = LinearLayoutManager(requireActivity())
        binding.savedNewsRecyclerview.layoutManager = layoutManager
        binding.savedNewsRecyclerview.adapter = savedNewsAdapter
    }
}
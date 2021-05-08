package com.davit.kotlin.news

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.davit.kotlin.news.adapters.SlidePagerAdapter
import com.davit.kotlin.news.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    //

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pager.adapter = SlidePagerAdapter(this)

        TabLayoutMediator(binding.tabLayout, binding.pager) {tab, position ->
            if (position == 0) {
                tab.text = "Live News"
            } else {
                tab.text = "Favorites"
            }

        }.attach()
    }
}
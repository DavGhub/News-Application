package com.davit.kotlin.volonewstask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.davit.kotlin.volonewstask.adapters.SlidePagerAdapter
import com.davit.kotlin.volonewstask.databinding.ActivityMainBinding
import com.davit.kotlin.volonewstask.pages.FavoriteNewsFragment
import com.davit.kotlin.volonewstask.pages.LiveNewsFragment
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
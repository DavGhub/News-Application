package com.davit.kotlin.volonewstask.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.davit.kotlin.volonewstask.pages.FavoriteNewsFragment
import com.davit.kotlin.volonewstask.pages.LiveNewsFragment


class SlidePagerAdapter(fa:FragmentActivity) : FragmentStateAdapter(fa) {

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) LiveNewsFragment() else FavoriteNewsFragment()
    }

    override fun getItemCount(): Int {
        return 2
    }
}
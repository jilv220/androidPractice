package com.example.myapplication.Util

import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

class ViewPagerUtil {

    fun init (viewpager: ViewPager,adapter: PagerAdapter, onPageChangeListener: ViewPager.OnPageChangeListener) {

        viewpager.adapter = adapter
        viewpager.addOnPageChangeListener(onPageChangeListener)
    }


}
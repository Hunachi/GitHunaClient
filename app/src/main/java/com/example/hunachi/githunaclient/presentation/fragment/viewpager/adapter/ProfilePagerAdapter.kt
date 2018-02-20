package com.example.hunachi.githunaclient.presentation.fragment.viewpager.adapter

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.hunachi.githunaclient.presentation.fragment.feeds.FeedsFragment
import com.example.hunachi.githunaclient.util.extension.convertToLowerText

/**
 * Created by hunachi on 2018/02/10.
 */
class ProfilePagerAdapter(
        private val fragmentManager: FragmentManager,
        private val feedsFragment: FeedsFragment
) : FragmentPagerAdapter(fragmentManager) {
    
    override fun getItem(position: Int) =
        when (position) {
            0 -> feedsFragment
            else -> null
        }
    
    override fun getCount() = 1
    
    override fun getPageTitle(position: Int) =
            when (position) {
                0    -> feedsFragment.toString().convertToLowerText()
                else -> null
            }
}
package com.example.hunachi.githunaclient.presentation.fragment.viewpager.adapter

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.hunachi.githunaclient.presentation.fragment.list.ListsFragment
import com.example.hunachi.githunaclient.util.ListType
import com.example.hunachi.githunaclient.util.extension.convertToLowerText

/**
 * Created by hunachi on 2018/02/10.
 */
class ProfilePagerAdapter(
        fragmentManager: FragmentManager,
        private val fragments: List<ListsFragment>
    /*private val feedsFragment: ListsFragment,
    private val followerFragment: ListsFragment,
    private val followingFragment: ListsFragment*/
) : FragmentPagerAdapter(fragmentManager) {
    
    override fun getItem(position: Int) =
            if (fragments.size > position) fragments[position] else null
    /*0    -> feedsFragment
    1    -> followerFragment
    2    -> followingFragment*/
    
    
    override fun getCount() = fragments.size
    
    override fun getPageTitle(position: Int) =
            if (fragments.size > position) ListType.values()[position].name else null
            /*when (position) {
                0    -> ListType.FEEDS.name*//*feedsFragment.toString().convertToLowerText()*//*
                1    -> ListType.FOLLOWER.name
                2    -> ListType.FOLLOWING.name
                else -> null
            }*/
}
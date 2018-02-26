package com.example.hunachi.githunaclient.presentation.fragment.viewpager.adapter

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.hunachi.githunaclient.presentation.fragment.list.ListsFragment
import com.example.hunachi.githunaclient.presentation.fragment.list.ListType

/**
 * Created by hunachi on 2018/02/10.
 */
class ProfilePagerAdapter(
        fragmentManager: FragmentManager,
        private val fragments: List<ListsFragment>
) : FragmentPagerAdapter(fragmentManager) {
    
    override fun getItem(position: Int) =
            if (fragments.size > position) fragments[position] else null
    
    override fun getCount() = fragments.size
    
    override fun getPageTitle(position: Int) =
            if (fragments.size > position) ListType.values()[position].name else null
    
}
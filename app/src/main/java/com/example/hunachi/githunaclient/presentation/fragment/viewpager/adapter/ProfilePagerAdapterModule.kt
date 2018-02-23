package com.example.hunachi.githunaclient.presentation.fragment.viewpager.adapter

import android.support.v4.app.FragmentManager
import com.example.hunachi.githunaclient.presentation.fragment.list.ListsArgument
import com.example.hunachi.githunaclient.util.ListType
import com.github.salomonbrys.kodein.*

/**
 * Created by hunachi on 2018/02/10.
 */
val profilePagerAdapterModule = Kodein.Module{
    bind<ProfilePagerAdapter>() with factory { it: Pair<FragmentManager, String> ->
        ProfilePagerAdapter(
            fragmentManager = it.first,
            feedsFragment = with(ListsArgument(it.second, ListType.FEEDS)).instance()
        )
    }
}
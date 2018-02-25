package com.example.hunachi.githunaclient.presentation.fragment.viewpager.adapter

import android.support.v4.app.FragmentManager
import com.example.hunachi.githunaclient.presentation.fragment.list.ListsArgument
import com.example.hunachi.githunaclient.presentation.fragment.list.ListsFragment
import com.example.hunachi.githunaclient.presentation.fragment.viewpager.ListType
import com.github.salomonbrys.kodein.*

/**
 * Created by hunachi on 2018/02/10.
 */
val profilePagerAdapterModule = Kodein.Module{
    bind<ProfilePagerAdapter>() with factory { it: Pair<FragmentManager, String> ->
        val fragments = mutableListOf<ListsFragment>()
        ListType.values().forEach { listType ->
            fragments.add(with(ListsArgument(it.second, listType)).instance())
        }
        ProfilePagerAdapter(
            fragmentManager = it.first,
            fragments = fragments.toList()
        )
    }
}
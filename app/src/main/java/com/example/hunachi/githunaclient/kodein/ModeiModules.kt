package com.example.hunachi.githunaclient.kodein

import android.support.v4.app.FragmentManager
import com.example.hunachi.githunaclient.data.repository.GithubTokenRepository
import com.example.hunachi.githunaclient.presentation.base.BaseActivity
import com.example.hunachi.githunaclient.presentation.fragment.list.ListType
import com.example.hunachi.githunaclient.presentation.fragment.list.ListsArgument
import com.example.hunachi.githunaclient.presentation.fragment.list.ListsFragment
import com.example.hunachi.githunaclient.presentation.fragment.viewpager.adapter.ProfilePagerAdapter
import com.example.hunachi.githunaclient.presentation.helper.Navigator
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.androidActivityScope

/**
 * Created by hunachi on 2018/02/28.
 */
val modelModules = Kodein.Module {
    bind<GithubTokenRepository>() with factory { callback: GithubTokenRepository.Callback ->
        GithubTokenRepository(
            scheduler = instance(),
            application = instance(),
            callback = callback
        )
    }
    
    bind<Navigator>() with scopedSingleton(androidActivityScope) {
        Navigator(
            activity = it as BaseActivity,
            mainActivity = instance(),
            loginGithubActivity = instance(),
            mainProfileActivity = instance()
        )
    }
    
    bind<ProfilePagerAdapter>() with factory { it: Pair<FragmentManager, String> ->
        val fragments = mutableListOf<ListsFragment>()
        ListType.values().forEach { listType ->
            if (listType != ListType.TL) fragments.add(with(ListsArgument(it.second, listType)).instance())
        }
        ProfilePagerAdapter(
            fragmentManager = it.first,
            fragments = fragments.toList()
        )
    }
}

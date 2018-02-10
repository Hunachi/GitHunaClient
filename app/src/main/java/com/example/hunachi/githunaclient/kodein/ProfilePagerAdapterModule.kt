package com.example.hunachi.githunaclient.kodein

import android.support.v4.app.FragmentManager
import com.example.hunachi.githunaclient.presentation.main.profile.ProfilePagerAdapter
import com.github.salomonbrys.kodein.*

/**
 * Created by hunachi on 2018/02/10.
 */
val profilePagerAdapterModule = Kodein.Module{
    bind<ProfilePagerAdapter>() with factory {
        fragmentManager: FragmentManager ->
        ProfilePagerAdapter(
            fragmentManager = fragmentManager,
            followerEventFragment = instance(),
            userInfoFragment = instance()
        )
    }
}
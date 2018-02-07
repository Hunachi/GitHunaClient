package com.example.hunachi.githunaclient.kodein

import com.example.hunachi.githunaclient.presentation.fragment.event.FollowerEventViewModel
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton

/**
 * Created by hunachi on 2018/02/07.
 */
val followerEventViewModelModule = Kodein.Module {
    bind<FollowerEventViewModel>() with singleton {
        FollowerEventViewModel(instance())
    }
}
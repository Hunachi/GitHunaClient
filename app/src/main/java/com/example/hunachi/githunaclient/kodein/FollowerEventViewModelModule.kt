package com.example.hunachi.githunaclient.kodein

import com.example.hunachi.githunaclient.presentation.MyApplication
import com.example.hunachi.githunaclient.presentation.fragment.event.FollowerEventViewModel
import com.github.salomonbrys.kodein.*

/**
 * Created by hunachi on 2018/02/07.
 */
val followerEventViewModelModule = Kodein.Module {
    bind<FollowerEventViewModel>() with singleton {
        FollowerEventViewModel(
            application = instance(),
            githubApiRepository = with((instance() as MyApplication).token).instance()
        )
    }
}
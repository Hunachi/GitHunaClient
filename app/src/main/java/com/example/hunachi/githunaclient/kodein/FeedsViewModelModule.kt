package com.example.hunachi.githunaclient.kodein

import com.example.hunachi.githunaclient.data.api.responce.User
import com.example.hunachi.githunaclient.presentation.MyApplication
import com.example.hunachi.githunaclient.presentation.fragment.feeds.FeedsViewModel
import com.github.salomonbrys.kodein.*

/**
 * Created by hunachi on 2018/02/07.
 */
val followerEventViewModelModule = Kodein.Module {
    bind<FeedsViewModel>() with factory { user: User ->
        FeedsViewModel(
            application = instance(),
            githubApiRepository = with((instance() as MyApplication).token).instance(),
            user = user
        )
    }
}
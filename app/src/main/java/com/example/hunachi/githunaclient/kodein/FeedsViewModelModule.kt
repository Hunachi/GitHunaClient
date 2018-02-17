package com.example.hunachi.githunaclient.kodein

import com.example.hunachi.githunaclient.data.api.responce.User
import com.example.hunachi.githunaclient.presentation.MyApplication
import com.example.hunachi.githunaclient.presentation.fragment.feeds.FeedsViewModel
import com.github.salomonbrys.kodein.*

/**
 * Created by hunachi on 2018/02/07.
 */
val eventViewModelModule = Kodein.Module {
    bind<FeedsViewModel>() with factory { userName: String ->
        FeedsViewModel(
            githubApiRepository = with((instance() as MyApplication).token).instance(),
            userName = userName
        )
    }
}
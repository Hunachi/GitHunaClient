package com.example.hunachi.githunaclient.presentation.fragment.feeds

import com.example.hunachi.githunaclient.presentation.application.MyApplication
import com.example.hunachi.githunaclient.presentation.fragment.feeds.FeedsViewModel
import com.github.salomonbrys.kodein.*

/**
 * Created by hunachi on 2018/02/07.
 */
val eventViewModelModule = Kodein.Module {
    bind<FeedsViewModel>() with factory { userName: String ->
        FeedsViewModel(
            githubApiRepository = with((instance() as MyApplication)).instance(),
            userName = userName,
            schedulers = instance()
        )
    }
}
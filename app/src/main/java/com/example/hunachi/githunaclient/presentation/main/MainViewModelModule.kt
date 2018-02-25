package com.example.hunachi.githunaclient.presentation.main

import com.example.hunachi.githunaclient.presentation.MyApplication
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.androidActivityScope

/**
 * Created by hunachi on 2018/02/04.
 */
val mainViewModelModule = Kodein.Module {
    bind<MainViewModel>() with scopedSingleton(androidActivityScope) {
        MainViewModel(
            scheduler = instance(),
            githubApiRepository = with((instance() as MyApplication)).instance()
        )
    }
}
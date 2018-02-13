package com.example.hunachi.githunaclient.kodein

import android.support.v7.app.AppCompatActivity
import com.example.hunachi.githunaclient.presentation.MyApplication
import com.example.hunachi.githunaclient.presentation.main.profile.MainProfileViewModel
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.androidActivityScope

/**
 * Created by hunachi on 2018/02/11.
 */
val mainProfileViewModelModule = Kodein.Module{
    bind<MainProfileViewModel>() with scopedSingleton(androidActivityScope) {
        MainProfileViewModel(
            navigator = with(it as AppCompatActivity).instance(),
            application = instance(),
            scheduler = instance(),
            githubApiRepository = with((instance() as MyApplication).token).instance()
        )
    }
}
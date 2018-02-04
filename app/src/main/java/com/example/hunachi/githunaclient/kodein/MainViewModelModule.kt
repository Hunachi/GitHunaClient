package com.example.hunachi.githunaclient.kodein

import android.support.v7.app.AppCompatActivity
import com.example.hunachi.githunaclient.presentation.main.MainViewModel
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.androidActivityScope

/**
 * Created by hunachi on 2018/02/04.
 */
val mainViewModelModule = Kodein.Module {
    bind<MainViewModel>() with scopedSingleton(androidActivityScope) {
        MainViewModel(
            navigator = with(it as AppCompatActivity).instance(),
            application = instance(),
            userInfoFragment = instance()
        )
    }
}
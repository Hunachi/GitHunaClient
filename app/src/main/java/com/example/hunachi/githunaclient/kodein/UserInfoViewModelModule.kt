package com.example.hunachi.githunaclient.kodein

import android.arch.lifecycle.LifecycleOwner
import android.support.v7.app.AppCompatActivity
import com.example.hunachi.githunaclient.presentation.fragment.UserInfoViewModel
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.androidSupportFragmentScope

/**
 * Created by hunachi on 2018/02/04.
 */
val userInfoViewModelModule = Kodein.Module {
    bind<UserInfoViewModel>() with scopedSingleton(androidSupportFragmentScope) {
        UserInfoViewModel(
            navigator = with(it.activity as AppCompatActivity).instance(),
            application = instance(),
            owner = it as LifecycleOwner /*使う時が来るのか？*/
        )
    }
}
package com.example.hunachi.githunaclient.presentation.fragment.userinfo

import android.support.v7.app.AppCompatActivity
import com.example.hunachi.githunaclient.data.api.responce.User
import com.example.hunachi.githunaclient.presentation.application.MyApplication
import com.example.hunachi.githunaclient.presentation.base.BaseFragment
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.androidSupportFragmentScope

/**
 * Created by hunachi on 2018/02/04.
 */
val userInfoViewModelModule = Kodein.Module {
    bind<UserInfoViewModel>() with scopedSingleton(androidSupportFragmentScope) {
        UserInfoViewModel(
            navigator = with(it.activity as AppCompatActivity).instance(),
            githubApiRepository = with((instance() as MyApplication).token).instance(),
            scheduler = instance()
        )
    }
}
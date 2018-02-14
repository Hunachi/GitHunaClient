package com.example.hunachi.githunaclient.kodein

import android.support.v7.app.AppCompatActivity
import com.example.hunachi.githunaclient.presentation.MyApplication
import com.example.hunachi.githunaclient.presentation.base.BaseActivity
import com.example.hunachi.githunaclient.presentation.main.profile.MainProfileViewModel
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.androidActivityScope

/**
 * Created by hunachi on 2018/02/11.
 */
val mainProfileViewModelModule = Kodein.Module {
    bind<MainProfileViewModel>() with multiton { it: Pair<BaseActivity, String> ->
        MainProfileViewModel(
            navigator = with(it.first as AppCompatActivity).instance(),
            application = instance(),
            scheduler = instance(),
            githubApiRepository = with((instance() as MyApplication).token).instance(),
            userName = it.second
        )
    }
}
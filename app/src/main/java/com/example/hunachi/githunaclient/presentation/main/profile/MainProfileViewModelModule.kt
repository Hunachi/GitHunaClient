package com.example.hunachi.githunaclient.presentation.main.profile

import android.support.v7.app.AppCompatActivity
import com.example.hunachi.githunaclient.presentation.application.MyApplication
import com.example.hunachi.githunaclient.presentation.base.BaseActivity
import com.github.salomonbrys.kodein.*

/**
 * Created by hunachi on 2018/02/11.
 */
val mainProfileViewModelModule = Kodein.Module {
    bind<MainProfileViewModel>() with multiton { it: Pair<BaseActivity, String> ->
        MainProfileViewModel(
            navigator = with(it.first as AppCompatActivity).instance(),
            scheduler = instance(),
            githubApiRepository = with((instance() as MyApplication).token).instance(),
            userName = it.second
        )
    }
}
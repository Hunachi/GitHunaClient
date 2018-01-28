package com.example.hunachi.githunaclient.presentation

import android.app.Application
import com.example.hunachi.githunaclient.presentation.main.MainActivity
import com.example.hunachi.githunaclient.presentation.main.MainViewModel
import com.example.hunachi.githunaclient.presentation.oauth.OauthAuthorizationFragment
import com.github.salomonbrys.kodein.*

/**
 * Created by hunachi on 2018/01/27.
 */
class MyApplication : Application(), KodeinAware {
    override val kodein by Kodein.lazy {
        bind<MyApplication>() with singleton { this@MyApplication }
        bind<MainActivity>() with instance(MainActivity())
        bind<MainViewModel>() with instance(MainViewModel())
        bind<OauthAuthorizationFragment>() with instance(OauthAuthorizationFragment.newInstance())
    }
}
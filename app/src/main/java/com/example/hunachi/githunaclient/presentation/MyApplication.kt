package com.example.hunachi.githunaclient.presentation

import android.app.Application
import com.example.hunachi.githunaclient.presentation.main.MainActivity
import com.example.hunachi.githunaclient.presentation.main.mainViewModelModule
import com.example.hunachi.githunaclient.presentation.oauth.OauthFragment
import com.example.hunachi.githunaclient.presentation.oauth.oauthFragmentViewModel
import com.github.salomonbrys.kodein.*

/**
 * Created by hunachi on 2018/01/27.
 */
class MyApplication : Application(), KodeinAware {
    override val kodein by Kodein.lazy {
        bind<MyApplication>() with singleton { this@MyApplication }
        import(mainViewModelModule)
        import(oauthFragmentViewModel)
        bind<MainActivity>() with singleton { MainActivity() }
        bind<OauthFragment>() with factory { _: String -> OauthFragment.newInstance() }
    }
}
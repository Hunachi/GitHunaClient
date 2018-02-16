package com.example.hunachi.githunaclient.kodein

import com.example.hunachi.githunaclient.presentation.base.BaseActivity
import com.example.hunachi.githunaclient.presentation.helper.Navigator
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.android.androidActivityScope
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.scopedSingleton

/**
 * Created by hunachi on 2018/02/04.
 */
val navigatorModule = Kodein.Module {
    bind<Navigator>() with scopedSingleton(androidActivityScope) {
        Navigator(
            activity = it as BaseActivity,
            mainActivity = instance(),
            loginGithubActivity = instance(),
            mainProfileActivity = instance()
        )
    }
}
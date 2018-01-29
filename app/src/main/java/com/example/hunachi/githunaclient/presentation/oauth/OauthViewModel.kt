package com.example.hunachi.githunaclient.presentation.oauth

import com.example.hunachi.githunaclient.presentation.base.BaseFragmentViewModel
import com.example.hunachi.githunaclient.util.OauthFragmentModlules
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.android.androidFragmentScope
import com.github.salomonbrys.kodein.android.androidSupportFragmentScope
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.scopedSingleton

/**
 * Created by hunachi on 2018/01/29.
 */
class OauthViewModel(private val modules: OauthFragmentModlules) : BaseFragmentViewModel(modules.application) {

}

val oauthFragmentViewModel = Kodein.Module {
    bind<OauthViewModel>() with scopedSingleton(androidSupportFragmentScope) {
        OauthViewModel(OauthFragmentModlules(it as OauthFragment, instance()))
    }
}
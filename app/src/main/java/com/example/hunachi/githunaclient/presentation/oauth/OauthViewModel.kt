package com.example.hunachi.githunaclient.presentation.oauth

import com.example.hunachi.githunaclient.presentation.base.BaseFragmentViewModel
import com.example.hunachi.githunaclient.util.OauthFragmentModules
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.androidSupportFragmentScope

/**
 * Created by hunachi on 2018/01/29.
 */
class OauthViewModel(private val modules: OauthFragmentModules) : BaseFragmentViewModel(modules.application) {
    
    private val fragment = modules.oauthFragment
    private val oauthWebAdapter = modules.oauthWebAdapter
    private val intent = oauthWebAdapter.intent
    
    fun onClickWebOauth() {
        fragment.activity?.startActivity(intent)
    }
    
    fun onClickBasicOauth() {
    
    }
}

val oauthFragmentViewModel = Kodein.Module {
    bind<OauthViewModel>() with scopedSingleton(androidSupportFragmentScope) {
        OauthViewModel(OauthFragmentModules(it as OauthFragment, instance(), with(mutableListOf("repo")).instance()))
    }
}
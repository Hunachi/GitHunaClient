package com.example.hunachi.githunaclient.presentation.main

import com.example.hunachi.githunaclient.presentation.base.BaseViewModel
import com.example.hunachi.githunaclient.util.MainViewModels
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.androidActivityScope

/**
 * Created by hunachi on 2018/01/27.
 */
class MainViewModel(private val modules: MainViewModels) : BaseViewModel(modules.activity) {
    
    /*private val textProcessor: PublishProcessor<String> = PublishProcessor.create()
    var text: LiveData<String> = LiveDataReactiveStreams.fromPublisher(textProcessor)*/
    private val authorizationFragment = modules.oauthFragment
    private val containerId = modules.containerId
    private val activity = modules.activity
    
    override fun onCreate() {
        super.onCreate()
        /*confirm authorization*/
        if (true) {
            activity.replaceFragment(authorizationFragment, containerId)
        }
    }
}

val mainViewModelModule = Kodein.Module {
    bind<MainViewModel>() with scopedSingleton(androidActivityScope) {
        MainViewModel(MainViewModels(it as MainActivity, instance(), instance()))
    }
}
package com.example.hunachi.githunaclient.presentation.main

import com.example.hunachi.githunaclient.presentation.base.BaseViewModel
import com.example.hunachi.githunaclient.util.MainViewModels
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.androidActivityScope

/**
 * Created by hunachi on 2018/01/27.
 */
class MainViewModel(private val models: MainViewModels) : BaseViewModel(models.application) {
    /*private val textProcessor: PublishProcessor<String> = PublishProcessor.create()
    var text: LiveData<String> = LiveDataReactiveStreams.fromPublisher(textProcessor)*/
}

val mainViewModelModule = Kodein.Module {
    bind<MainViewModel>() with scopedSingleton(androidActivityScope) {
        MainViewModel(MainViewModels(
                navigator = with(it as MainActivity).instance(),
                application = instance())
        )
    }
}
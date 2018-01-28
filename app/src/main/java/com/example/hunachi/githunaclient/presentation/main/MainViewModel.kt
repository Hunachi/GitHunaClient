package com.example.hunachi.githunaclient.presentation.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.view.View
import com.example.hunachi.githunaclient.presentation.base.BaseViewModel
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.android.androidActivityScope
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.scopedSingleton
import io.reactivex.processors.PublishProcessor

/**
 * Created by hunachi on 2018/01/27.
 */
class MainViewModel(private val activity: MainActivity) : BaseViewModel(activity) {
    
    private val textProcessor: PublishProcessor<String> = PublishProcessor.create()
    var text: LiveData<String> = LiveDataReactiveStreams.fromPublisher(textProcessor)
    var count = 0
    
    fun onClick() {
        count++
        textProcessor.onNext("${context.packageName} + $count")
    }
}

val mainViewModelModule = Kodein.Module {
    bind<MainViewModel>() with scopedSingleton(androidActivityScope) { MainViewModel(instance()) }
}
package com.example.hunachi.githunaclient.presentation.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.view.View
import com.example.hunachi.githunaclient.presentation.base.BaseViewModel
import io.reactivex.processors.PublishProcessor

/**
 * Created by hunachi on 2018/01/27.
 */
class MainViewModel: BaseViewModel() {
    
    private val textProcessor: PublishProcessor<String> = PublishProcessor.create()
    var text: LiveData<String> = LiveDataReactiveStreams.fromPublisher(textProcessor)
    var count = 0
    
    fun onClick(){
        count++
        textProcessor.onNext("hoge + $count")
    }
    
}
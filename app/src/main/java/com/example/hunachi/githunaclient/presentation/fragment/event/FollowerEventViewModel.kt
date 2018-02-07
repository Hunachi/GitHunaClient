package com.example.hunachi.githunaclient.presentation.fragment.event

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModelProviders
import com.example.hunachi.githunaclient.presentation.MyApplication
import com.example.hunachi.githunaclient.presentation.base.BaseFragmentViewModel
import com.example.hunachi.githunaclient.util.EventCallback
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.androidSupportFragmentScope
import io.reactivex.processors.PublishProcessor

/**
 * Created by hunachi on 2018/02/05.
 */
class FollowerEventViewModel(
        private val application: MyApplication
) : BaseFragmentViewModel(application) {
    
    //val eventsProcessor: PublishProcessor<MutableList<FollowerEvent>> = PublishProcessor.create()
    //var list: LiveData<MutableList<FollowerEvent>> = LiveDataReactiveStreams.fromPublisher(eventsProcessor)
    var eventList: MutableLiveData<FollowerEvent> = MutableLiveData()
    //fun events(): LiveData<MutableSet<FollowerEvent>> = eventList
    
    override fun onCreate() {
        super.onCreate()
        eventList.value = FollowerEvent()
    }
    
    val callback: EventCallback = {
        eventList.value = FollowerEvent()
    }
}
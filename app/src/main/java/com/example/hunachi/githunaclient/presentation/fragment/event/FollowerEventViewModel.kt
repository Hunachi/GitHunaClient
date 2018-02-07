package com.example.hunachi.githunaclient.presentation.fragment.event

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModelProviders
import android.util.Log
import com.example.hunachi.githunaclient.data.repository.GithubApiRepository
import com.example.hunachi.githunaclient.presentation.MyApplication
import com.example.hunachi.githunaclient.presentation.base.BaseFragmentViewModel
import com.example.hunachi.githunaclient.util.EventCallback
import com.example.hunachi.githunaclient.util.extension.convertToFollowerEvent
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.androidSupportFragmentScope
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.processors.PublishProcessor
import io.reactivex.schedulers.Schedulers

/**
 * Created by hunachi on 2018/02/05.
 */
class FollowerEventViewModel(
        private val application: MyApplication,
        private val githubApiRepository: GithubApiRepository
) : BaseFragmentViewModel(application) {
    //todo itemの文字とアイコンがバグってる．
    //val eventsProcessor: PublishProcessor<MutableList<FollowerEvent>> = PublishProcessor.create()
    //var list: LiveData<MutableList<FollowerEvent>> = LiveDataReactiveStreams.fromPublisher(eventsProcessor)
    var eventList: MutableLiveData<FollowerEvent> = MutableLiveData()
    
    override fun onCreate() {
        super.onCreate()
        initEvents()
    }
    
    private fun initEvents(){
        githubApiRepository.follwerEvent(user = "hunachi", pages = 2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.forEach { eventList.value = it.convertToFollowerEvent() }
                },{
                    it.printStackTrace()
                })
    }
    
    fun updateEvents(){
    
    }
    
    val callback: EventCallback = {
        eventList.value = FollowerEvent()
    }
}
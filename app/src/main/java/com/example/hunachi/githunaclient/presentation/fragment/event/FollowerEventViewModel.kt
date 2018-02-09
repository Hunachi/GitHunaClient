package com.example.hunachi.githunaclient.presentation.fragment.event

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModelProviders
import android.databinding.Bindable
import android.support.v4.widget.SwipeRefreshLayout
import android.util.Log
import com.example.hunachi.githunaclient.BR
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
import java.util.logging.Handler

/**
 * Created by hunachi on 2018/02/05.
 */
class FollowerEventViewModel(
        private val application: MyApplication,
        private val githubApiRepository: GithubApiRepository
) : BaseFragmentViewModel(application) {
    
    var eventList: MutableLiveData<FollowerEvent> = MutableLiveData()
    var refreshing: MutableLiveData<Boolean> = MutableLiveData()
    
    override fun onCreate() {
        super.onCreate()
        initEvents()
    }
    
    private fun initEvents() {
        githubApiRepository.follwerEvent(user = "hunachi", pages = 2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.forEach { eventList.value = it.convertToFollowerEvent() }
                }, {
                    it.printStackTrace()
                })
    }
    
    fun updateEvents(): SwipeRefreshLayout.OnRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        Log.d("hoge", "refresh")
    }
    
    val callback: EventCallback = {
        refreshing.value = false
    }
}
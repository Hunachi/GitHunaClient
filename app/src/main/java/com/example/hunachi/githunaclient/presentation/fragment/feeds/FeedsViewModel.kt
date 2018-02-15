package com.example.hunachi.githunaclient.presentation.fragment.feeds

import android.arch.lifecycle.MutableLiveData
import android.support.v4.widget.SwipeRefreshLayout
import com.example.hunachi.githunaclient.data.api.responce.User
import com.example.hunachi.githunaclient.data.repository.GithubApiRepository
import com.example.hunachi.githunaclient.presentation.MyApplication
import com.example.hunachi.githunaclient.presentation.base.BaseFragmentViewModel
import com.example.hunachi.githunaclient.util.EventCallback
import com.example.hunachi.githunaclient.util.extension.convertToFollowerEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by hunachi on 2018/02/05.
 */
class FeedsViewModel(
        application: MyApplication,
        private val githubApiRepository: GithubApiRepository,
        private val user: User
) : BaseFragmentViewModel(application) {
    
    var event: MutableLiveData<Feeds> = MutableLiveData()
    var refreshing: MutableLiveData<Boolean> = MutableLiveData()
    private var pages = 0
    
    override fun onCreate() {
        super.onCreate()
        updateList()
    }
    
    private fun updateList() {
        refreshing.value = true
        githubApiRepository.followerEvent(user = user.userName, pages = pages)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.reversed().forEach { event.value = it.convertToFollowerEvent() }
                }, {
                    it.printStackTrace()
                },{
                    refreshing.value = false
                })
    }
    
    fun updateEvents(): SwipeRefreshLayout.OnRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        updateList()
    }
    
    val callback: EventCallback = {
        refreshing.value = false
    }
}
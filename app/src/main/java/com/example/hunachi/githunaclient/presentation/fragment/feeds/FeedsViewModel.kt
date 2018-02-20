package com.example.hunachi.githunaclient.presentation.fragment.feeds

import android.arch.lifecycle.MutableLiveData
import android.support.v4.widget.SwipeRefreshLayout
import com.example.hunachi.githunaclient.data.api.responce.Repository
import com.example.hunachi.githunaclient.data.repository.GithubApiRepository
import com.example.hunachi.githunaclient.presentation.base.BaseFragmentViewModel
import com.example.hunachi.githunaclient.util.extension.convertToFollowerEvent
import com.example.hunachi.githunaclient.util.rx.SchedulerProvider
import io.reactivex.processors.PublishProcessor

/**
 * Created by hunachi on 2018/02/05.
 */
class FeedsViewModel(
        private val githubApiRepository: GithubApiRepository,
        private val userName: String,
        private val schedulers: SchedulerProvider
) : BaseFragmentViewModel() {
    
    val event: MutableLiveData<Feed> = MutableLiveData() //todo make processor.
    val refreshing: MutableLiveData<Boolean> = MutableLiveData()
    val repositoryProcessor: PublishProcessor<Repository> = PublishProcessor.create()
    private var pages = 0
    
    override fun onCreate() {
        super.onCreate()
        updateList()
    }
    
    private fun updateList() {
        refreshing.value = true
        githubApiRepository.followerEvent(user = userName, pages = pages)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe({
                    it.reversed().forEach { event.value = it.convertToFollowerEvent() }
                }, {
                    it.printStackTrace()
                },{
                    refreshing.value = false
                })
    }
    
    fun repository(ownerRepo: Pair<String, String>){
        githubApiRepository.repository(ownerRepo.first, ownerRepo.second)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe({
                    repositoryProcessor.onNext(it)
                },{
                    repositoryProcessor.onError(it)
                })
    }
    
    fun updateEvents(): SwipeRefreshLayout.OnRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        updateList()
    }
    
}
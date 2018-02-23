package com.example.hunachi.githunaclient.presentation.fragment.feeds

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
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
    
    private val repositoryProcessor: PublishProcessor<Repository> = PublishProcessor.create()
    private val feedsPublishProcessor: PublishProcessor<List<Feed>> = PublishProcessor.create()
    private val refreshingProcessor: PublishProcessor<Boolean> = PublishProcessor.create()
    val feeds: LiveData<List<Feed>> = LiveDataReactiveStreams.fromPublisher(feedsPublishProcessor) //todo make processor.
    val refreshing: LiveData<Boolean> = LiveDataReactiveStreams.fromPublisher(refreshingProcessor)
    val repository: LiveData<Repository> = LiveDataReactiveStreams.fromPublisher(repositoryProcessor)
    private var pages = 0
    
    override fun onCreate() {
        super.onCreate()
        updateList(true)
    }
    
    private fun updateList(setUp: Boolean) {
        if (feeds.value == null || !setUp) {
            refreshingProcessor.onNext(true)
            githubApiRepository.followerEvent(userName = userName, pages = pages)
                    .subscribeOn(schedulers.io())
                    .observeOn(schedulers.ui())
                    .subscribe({
                        feedsPublishProcessor.onNext(it.reversed().map { it.convertToFollowerEvent() })
                    }, {
                        it.printStackTrace()
                    }, {
                        refreshingProcessor.onNext(false)
                    })
        } else {
            feedsPublishProcessor.onNext(feeds.value)
            refreshingProcessor.onNext(false)
        }
    }
    
    fun repository(ownerRepo: Pair<String, String>) {
        githubApiRepository.repository(ownerRepo.first, ownerRepo.second)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe({
                    repositoryProcessor.onNext(it)
                }, {
                    repositoryProcessor.onError(it)
                })
    }
    
    fun updateEvents(): SwipeRefreshLayout.OnRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        updateList(setUp = false)
    }
    
}
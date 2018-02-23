package com.example.hunachi.githunaclient.presentation.fragment.list

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.support.v4.widget.SwipeRefreshLayout
import com.example.hunachi.githunaclient.data.repository.GithubApiRepository
import com.example.hunachi.githunaclient.presentation.base.BaseFragmentViewModel
import com.example.hunachi.githunaclient.presentation.fragment.list.feed.Feed
import com.example.hunachi.githunaclient.util.GoWebCallback
import com.example.hunachi.githunaclient.util.ListType
import com.example.hunachi.githunaclient.util.LoadingCallback
import com.example.hunachi.githunaclient.util.extension.convertToFollowerEvent
import com.example.hunachi.githunaclient.util.rx.SchedulerProvider
import io.reactivex.processors.PublishProcessor

/**
 * Created by hunachi on 2018/02/05.
 */
class ListsViewModel(
        private val githubApiRepository: GithubApiRepository,
        private val listsArgument: ListsArgument,
        private val schedulers: SchedulerProvider
) : BaseFragmentViewModel() {
    
    private val feedsPublishProcessor: PublishProcessor<List<Feed>> = PublishProcessor.create()
    val feeds: LiveData<List<Feed>> = LiveDataReactiveStreams.fromPublisher(feedsPublishProcessor)
    private var loadingCallback: LoadingCallback? = null
    private var pages = 0
    
    /*call this by all means first*/
    fun updateList(setUp: Boolean, callback: LoadingCallback) {
        loadingCallback = callback
        when (listsArgument.listsType) {
            ListType.FEEDS     -> updateFeeds(setUp, callback)
            ListType.FOLLOWER  -> { }
            ListType.FOLLOWING -> { }
            ListType.GIST      -> { }
            ListType.REPO      -> { }
            ListType.STARED    -> { }
        }
    }
    
    private fun updateFeeds(setUp: Boolean, callback: LoadingCallback) {
        if (feeds.value == null || !setUp) {
            callback(true)
            githubApiRepository.followerEvent(userName = listsArgument.userName, pages = pages)
                    .subscribeOn(schedulers.io())
                    .observeOn(schedulers.ui())
                    .subscribe({
                        feedsPublishProcessor.onNext(it.reversed().map { it.convertToFollowerEvent() })
                    }, {
                        it.printStackTrace()
                    }, {
                        callback(false)
                    })
        } else {
            feedsPublishProcessor.onNext(feeds.value)
            callback(false)
        }
    }
    
    fun repository(ownerRepo: Pair<String, String>, callback: GoWebCallback) {
        githubApiRepository.repository(ownerRepo.first, ownerRepo.second)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe({
                    callback(it.htmlUrl)
                }, {
                    it.printStackTrace()
                })
    }
    
    fun updateEvents(): SwipeRefreshLayout.OnRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        loadingCallback?.also { updateList(setUp = false, callback = it) }
    }
    
}
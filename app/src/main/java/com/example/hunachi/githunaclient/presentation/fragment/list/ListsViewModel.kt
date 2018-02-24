package com.example.hunachi.githunaclient.presentation.fragment.list

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.support.v4.widget.SwipeRefreshLayout
import com.example.hunachi.githunaclient.data.api.responce.ChildUser
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
    private val usersPublishProcessor: PublishProcessor<List<ChildUser>> = PublishProcessor.create()
    val feeds: LiveData<List<Feed>> = LiveDataReactiveStreams.fromPublisher(feedsPublishProcessor)
    val users: LiveData<List<ChildUser>> = LiveDataReactiveStreams.fromPublisher(usersPublishProcessor)
    private lateinit var loadingCallback: LoadingCallback
    private var pages = 0
    
    /*call this by all means first*/
    fun updateList(setUp: Boolean, callback: LoadingCallback) {
        loadingCallback = callback
        when (listsArgument.listsType) {
            ListType.FEEDS     -> updateFeeds(setUp)
            ListType.FOLLOWER  -> updateFollows(setUp, follower = true)
            ListType.FOLLOWING -> updateFollows(setUp, follower = false)
        ///ListType.GIST      -> { }
        ///ListType.REPO      -> { }
        ///ListType.STARED    -> { }
        }
    }
    
    private fun updateFeeds(setUp: Boolean) {
        if (feeds.value == null || !setUp) {
            loadingCallback(true)
            githubApiRepository.followerEvent(userName = listsArgument.userName, pages = pages)
                    .subscribeOn(schedulers.io())
                    .observeOn(schedulers.ui())
                    .subscribe({
                        feedsPublishProcessor.onNext(it.reversed().map { it.convertToFollowerEvent() })
                    }, {
                        it.printStackTrace()
                    }, {
                        loadingCallback(false)
                    })
        } else loadingCallback(false)
    }
    
    private fun updateFollows(setUp: Boolean, follower: Boolean) {
        if (users.value == null || !setUp) {
            loadingCallback(true)
            if (follower) {
                githubApiRepository.follower(userName = listsArgument.userName)
            } else {
                githubApiRepository.following(listsArgument.userName)
            }
                    .subscribeOn(schedulers.io())
                    .observeOn(schedulers.ui())
                    .subscribe({
                        usersPublishProcessor.onNext(it.sortedByDescending {
                            it.name?.toLowerCase() ?: it.userName.toLowerCase()
                        })
                    }, {
                        it.printStackTrace()
                    }, {
                        loadingCallback(false)
                    })
        } else loadingCallback(false)
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
        updateList(setUp = false, callback = loadingCallback)
    }
    
}
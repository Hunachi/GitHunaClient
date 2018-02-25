package com.example.hunachi.githunaclient.presentation.fragment.list

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.arch.lifecycle.MutableLiveData
import android.support.v4.widget.SwipeRefreshLayout
import com.example.hunachi.githunaclient.data.api.responce.ChildUser
import com.example.hunachi.githunaclient.data.api.responce.Gist
import com.example.hunachi.githunaclient.data.api.responce.Repository
import com.example.hunachi.githunaclient.data.repository.GithubApiRepository
import com.example.hunachi.githunaclient.presentation.base.BaseFragmentViewModel
import com.example.hunachi.githunaclient.presentation.fragment.list.feed.Feed
import com.example.hunachi.githunaclient.presentation.fragment.list.follow.FollowType
import com.example.hunachi.githunaclient.presentation.fragment.list.repository.RepositoryType
import com.example.hunachi.githunaclient.util.GoWebCallback
import com.example.hunachi.githunaclient.presentation.fragment.viewpager.ListType
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
    
    private val listSizePublishProcessor: PublishProcessor<Int> = PublishProcessor.create()
    val listSize: LiveData<Int> = LiveDataReactiveStreams.fromPublisher(listSizePublishProcessor)
    private lateinit var loadingCallback: LoadingCallback
    /*It was the neck that I could access it from View, but became strong in the turn of the screen thanks to this*/
    val list: MutableList<BaseItem> = mutableListOf()
    private var pages = 0 //TODO
    
    /*call this by all means first*/
    fun updateList(setUp: Boolean, callback: LoadingCallback) {
        loadingCallback = callback
        if (listSize.value == null || !setUp) {
            loadingCallback(true)
            when (listsArgument.listsType) {
                ListType.FEEDS      -> updateFeeds()
                ListType.FOLLOWER   -> updateFollows(FollowType.Follower)
                ListType.FOLLOWING  -> updateFollows(FollowType.Following)
                ListType.GIST       -> updateGists()
                ListType.STARED     -> updateRepositories(RepositoryType.STARED)
                ListType.WATCH      -> updateRepositories(RepositoryType.WATCH)
                ListType.REPOSITORY -> updateRepositories(RepositoryType.MY_REPOSITORY)
            }
        }
    }
    
    private fun updateFeeds() {
        githubApiRepository.followerEvent(userName = listsArgument.userName, pages = pages)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe({
                    addList(it.map { it.convertToFollowerEvent() }.filterNot { list.contains(it) })
                }, {
                    it.printStackTrace()
                }, {
                    loadingCallback(false)
                })
    }
    
    private fun updateFollows(followType: FollowType) {
        when (followType) {
            FollowType.Follower  -> githubApiRepository.follower(userName = listsArgument.userName)
            FollowType.Following -> githubApiRepository.following(userName = listsArgument.userName)
        }
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe({
                    addList(it.sortedBy {
                        it.name?.toLowerCase() ?: it.userName.toLowerCase()
                    }.filterNot { list.contains(it) })
                }, {
                    it.printStackTrace()
                }, {
                    loadingCallback(false)
                })
    }
    
    private fun updateGists() {
        githubApiRepository.gists(userName = listsArgument.userName)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe({
                    addList(it.sortedByDescending { it.updatedAt }.filterNot { list.contains(it) })
                }, {
                    it.printStackTrace()
                }, {
                    loadingCallback(false)
                })
    }
    
    private fun updateRepositories(repositoryType: RepositoryType) {
        when (repositoryType) {
            RepositoryType.WATCH         -> githubApiRepository.watchingRepo(userName = listsArgument.userName)
            RepositoryType.STARED        -> githubApiRepository.staring(userName = listsArgument.userName)
            RepositoryType.MY_REPOSITORY -> githubApiRepository.repositories(userName = listsArgument.userName)
        }
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe({
                    addList(it.sortedByDescending { it.updatedAt }.filterNot { list.contains(it) })
                }, {
                    it.printStackTrace()
                }, {
                    loadingCallback(false)
                })
    }
    
    fun repository(ownerRepo: Pair<String, String>, callback: GoWebCallback) {
        loadingCallback(true)
        githubApiRepository.repository(ownerRepo.first, ownerRepo.second)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe({
                    callback(it.htmlUrl)
                }, {
                    it.printStackTrace()
                }, {
                    loadingCallback(false)
                })
    }
    
    fun addList(addList: List<BaseItem>) {
        list.addAll(0, addList)
        listSizePublishProcessor.onNext(addList.size)
    }
    
    fun updateEvents(): SwipeRefreshLayout.OnRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        updateList(setUp = false, callback = loadingCallback)
    }
    
}
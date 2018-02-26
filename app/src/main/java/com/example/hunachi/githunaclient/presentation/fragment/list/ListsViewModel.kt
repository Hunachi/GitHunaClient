package com.example.hunachi.githunaclient.presentation.fragment.list

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.support.v4.widget.SwipeRefreshLayout
import com.example.hunachi.githunaclient.data.repository.GithubApiRepository
import com.example.hunachi.githunaclient.presentation.base.BaseFragmentViewModel
import com.example.hunachi.githunaclient.presentation.fragment.list.feed.FeedType
import com.example.hunachi.githunaclient.presentation.fragment.list.follow.FollowType
import com.example.hunachi.githunaclient.presentation.fragment.list.repository.RepositoryType
import com.example.hunachi.githunaclient.util.GoWebCallback
import com.example.hunachi.githunaclient.util.ErrorCallback
import com.example.hunachi.githunaclient.util.LoadingCallback
import com.example.hunachi.githunaclient.util.extension.addListItem
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
    /*It was the neck that I could access it from View, but became strong in the turn of the screen thanks to this*/
    val list: MutableList<BaseItem> = mutableListOf()
    private lateinit var loadingCallback: LoadingCallback
    private lateinit var errorCallback: ErrorCallback
    //TODO 時間があったらlistを下向きに更新できるようにしたい．．
    
    /*call this by all means first*/
    fun init( loadingCallback: LoadingCallback, errorCallback: ErrorCallback){
        this.errorCallback = errorCallback
        this.loadingCallback = loadingCallback
    }
    
    /*call this by all means second*/
    fun updateList(setUp: Boolean) {
        if (listSize.value == null || !setUp) {
            loadingCallback(true)
            when (listsArgument.listsType) {
                ListType.TL           -> updateFeeds(FeedType.FOLLOWER_FEED)
                ListType.FEED         -> updateFeeds(FeedType.MY_FEED)
                ListType.FOLLOWERS    -> updateFollows(FollowType.Follower)
                ListType.FOLLOWING    -> updateFollows(FollowType.Following)
                ListType.GISTS        -> updateGists()
                ListType.STARED       -> updateRepositories(RepositoryType.STARED)
                ListType.WATCH        -> updateRepositories(RepositoryType.WATCH)
                ListType.REPOSITORIES -> updateRepositories(RepositoryType.MY_REPOSITORY)
            }
        }
    }
    
    private fun updateFeeds(feedType: FeedType) {
        when (feedType) {
            FeedType.FOLLOWER_FEED -> githubApiRepository.followerEvent(userName = listsArgument.userName)
            FeedType.MY_FEED       -> githubApiRepository.feed(userName = listsArgument.userName)
        }
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe({
                    loadingCallback(false)
                    listSizePublishProcessor.onNext(
                        list.addListItem(it.map { it.convertToFollowerEvent() }, isTopAddPosition = true)
                    )
                }, {
                    it.printStackTrace()
                    errorCallback()
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
                    loadingCallback(false)
                    addList(it.sortedBy {
                        it.name?.toLowerCase() ?: it.userName.toLowerCase()
                    }.filterNot { list.contains(it) })
                }, {
                    it.printStackTrace()
                    errorCallback()
                })
    }
    
    private fun updateGists() {
        githubApiRepository.gists(userName = listsArgument.userName)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe({
                    loadingCallback(false)
                    listSizePublishProcessor.onNext(
                        list.addListItem(it.sortedByDescending { it.updatedAt }, isTopAddPosition = true)
                    )
                }, {
                    it.printStackTrace()
                    errorCallback()
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
                    loadingCallback(false)
                    addList(it.sortedByDescending { it.updatedAt }.filterNot { list.contains(it) })
                }, {
                    it.printStackTrace()
                    errorCallback()
                })
    }
    
    fun repository(ownerRepo: Pair<String, String>, callback: GoWebCallback) {
        loadingCallback(true)
        githubApiRepository.repository(ownerRepo.first, ownerRepo.second)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe({
                    loadingCallback(false)
                    callback(it.htmlUrl)
                }, {
                    it.printStackTrace()
                    errorCallback()
                })
    }
    
    private fun addList(addList: List<BaseItem>) {
        loadingCallback(false)
        list.addAll(0, addList)
        listSizePublishProcessor.onNext(addList.size)
    }
    
    fun updateEvents(): SwipeRefreshLayout.OnRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        updateList(setUp = false)
    }
    
}
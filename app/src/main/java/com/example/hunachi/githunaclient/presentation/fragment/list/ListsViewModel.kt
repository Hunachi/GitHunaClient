package com.example.hunachi.githunaclient.presentation.fragment.list

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.support.v4.widget.SwipeRefreshLayout
import com.example.hunachi.githunaclient.data.api.responce.ChildUser
import com.example.hunachi.githunaclient.data.repository.GithubApiRepository
import com.example.hunachi.githunaclient.presentation.base.BaseFragmentViewModel
import com.example.hunachi.githunaclient.presentation.fragment.list.feed.FeedType
import com.example.hunachi.githunaclient.presentation.fragment.list.follow.FollowType
import com.example.hunachi.githunaclient.presentation.fragment.list.repository.RepositoryType
import com.example.hunachi.githunaclient.util.GoWebCallback
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
    
    private val listSizeProcessor: PublishProcessor<Int> = PublishProcessor.create()
    private val loadingProcessor: PublishProcessor<Boolean> = PublishProcessor.create()
    private val errorProcessor: PublishProcessor<Boolean> = PublishProcessor.create()
    private val lunchWebProcessor: PublishProcessor<String> = PublishProcessor.create()
    val listSize: LiveData<Int> = LiveDataReactiveStreams.fromPublisher(listSizeProcessor)
    /*It was the neck that I could access it from View, but became strong in the turn of the screen thanks to this*/
    val list: MutableList<BaseItem> = mutableListOf()
    val loading: LiveData<Boolean> = LiveDataReactiveStreams.fromPublisher(loadingProcessor)
    val error: LiveData<Boolean> = LiveDataReactiveStreams.fromPublisher(errorProcessor)
    val lunchWeb: LiveData<String> = LiveDataReactiveStreams.fromPublisher(lunchWebProcessor)
    //TODO 時間があったらpading....に....して下にpage更新できるようにする．
    
    /*call this by all means second*/
    fun updateList(setUp: Boolean) {
        if (listSize.value == null || !setUp) {
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
                    loadingProcessor.onNext(false)
                    listSizeProcessor.onNext(
                        list.addListItem(it.map { it.convertToFollowerEvent() }, isTopAddPosition = true)
                    )
                }, {
                    it.printStackTrace()
                    onError()
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
                    loadingProcessor.onNext(false)
                    addUserList(it.filterNot { list.contains(it) })
                }, {
                    it.printStackTrace()
                    onError()
                })
    }
    
    private fun updateGists() {
        githubApiRepository.gists(userName = listsArgument.userName)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe({
                    loadingProcessor.onNext(false)
                    listSizeProcessor.onNext(
                        list.addListItem(it.sortedByDescending { it.updatedAt }, isTopAddPosition = true)
                    )
                }, {
                    it.printStackTrace()
                    onError()
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
                    loadingProcessor.onNext(false)
                    listSizeProcessor.onNext(
                        if (repositoryType == RepositoryType.MY_REPOSITORY)
                            list.addListItem(it.sortedByDescending { it.updatedAt }, isTopAddPosition = true)
                        else list.addListItem(it, isTopAddPosition = true)
                    )
                }, {
                    it.printStackTrace()
                    onError()
                })
    }
    
    fun repository(ownerRepo: Pair<String, String>) {
        loadingProcessor.onNext(true)
        githubApiRepository.repository(ownerRepo.first, ownerRepo.second)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe({
                    loadingProcessor.onNext(false)
                    lunchWebProcessor.onNext(it.htmlUrl)
                }, {
                    it.printStackTrace()
                    onError()
                })
    }
    
    private fun addUserList(addList: List<ChildUser>) {
        loadingProcessor.onNext(false)
        if (addList.isNotEmpty()) {
            list.addAll(0, addList)
            (list as MutableList<ChildUser>).sortBy {
                it.name?.toLowerCase() ?: it.userName.toLowerCase()
            }
            listSizeProcessor.onNext(addList.size)
        }
    }
    
    private fun onError() {
        errorProcessor.run {
            onNext(true)
            onNext(false)
        }
    }
    
    fun updateEvents(): SwipeRefreshLayout.OnRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        updateList(setUp = false)
    }
    
}

package com.example.hunachi.githunaclient.presentation.fragment.list

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
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
    
    private val feedsPublishProcessor: PublishProcessor<List<Feed>> = PublishProcessor.create()
    private val usersPublishProcessor: PublishProcessor<List<ChildUser>> = PublishProcessor.create()
    private val gistsPublishProcessor: PublishProcessor<List<Gist>> = PublishProcessor.create()
    private val repositoriesPublishProcessor: PublishProcessor<List<Repository>> = PublishProcessor.create()
    val feeds: LiveData<List<Feed>> = LiveDataReactiveStreams.fromPublisher(feedsPublishProcessor)
    val users: LiveData<List<ChildUser>> = LiveDataReactiveStreams.fromPublisher(usersPublishProcessor)
    val gists: LiveData<List<Gist>> = LiveDataReactiveStreams.fromPublisher(gistsPublishProcessor)
    val repositories: LiveData<List<Repository>> = LiveDataReactiveStreams.fromPublisher(repositoriesPublishProcessor)
    private lateinit var loadingCallback: LoadingCallback
    private var pages = 0
    
    /*call this by all means first*/
    fun updateList(setUp: Boolean, callback: LoadingCallback) {
        loadingCallback = callback
        when (listsArgument.listsType) {
            ListType.FEEDS      -> updateFeeds(setUp)
            ListType.FOLLOWER   -> updateFollows(setUp, FollowType.Follower)
            ListType.FOLLOWING  -> updateFollows(setUp, FollowType.Following)
            ListType.GIST       -> updateGists(setUp)
            ListType.STARED     -> updateRepositories(setUp, RepositoryType.STARED)
            ListType.WATCH      -> updateRepositories(setUp, RepositoryType.WATCH)
            ListType.REPOSITORY -> updateRepositories(setUp, RepositoryType.MY_REPOSITORY)
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
        }
    }
    
    private fun updateFollows(setUp: Boolean, followType: FollowType) {
        if (users.value == null || !setUp) {
            loadingCallback(true)
            when (followType) {
                FollowType.Follower  -> githubApiRepository.follower(userName = listsArgument.userName)
                FollowType.Following -> githubApiRepository.following(userName = listsArgument.userName)
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
        }
    }
    
    private fun updateGists(setUp: Boolean) {
        if (gists.value == null || !setUp) {
            loadingCallback(true)
            githubApiRepository.gists(userName = listsArgument.userName)
                    .subscribeOn(schedulers.io())
                    .observeOn(schedulers.ui())
                    .subscribe({
                        gistsPublishProcessor.onNext(it.sortedBy { it.updatedAt })
                    }, {
                        it.printStackTrace()
                    }, {
                        loadingCallback(false)
                    })
        }
    }
    
    private fun updateRepositories(setUp: Boolean, repositoryType: RepositoryType) {
        if (repositories.value == null || !setUp) {
            loadingCallback(true)
            when (repositoryType) {
                RepositoryType.WATCH         -> githubApiRepository.watchingRepo(userName = listsArgument.userName)
                RepositoryType.STARED        -> githubApiRepository.staring(userName = listsArgument.userName)
                RepositoryType.MY_REPOSITORY -> githubApiRepository.repositories(userName = listsArgument.userName)
            }
                    .subscribeOn(schedulers.io())
                    .observeOn(schedulers.ui())
                    .subscribe({
                        repositoriesPublishProcessor.onNext(it.sortedBy { it.updatedAt })
                    }, {
                        it.printStackTrace()
                    }, {
                        loadingCallback(false)
                    })
        }
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
    
    fun updateEvents(): SwipeRefreshLayout.OnRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        updateList(setUp = false, callback = loadingCallback)
    }
    
}
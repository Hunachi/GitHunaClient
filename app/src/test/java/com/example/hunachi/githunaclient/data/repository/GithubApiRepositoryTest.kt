package com.example.hunachi.githunaclient.data.repository

import com.example.hunachi.githunaclient.Callback
import com.example.hunachi.githunaclient.data.api.responce.Repository
import com.example.hunachi.githunaclient.data.api.responce.User
import com.example.hunachi.githunaclient.model.Key
import com.example.hunachi.githunaclient.util.TestSchedulerProvider
import com.example.hunachi.githunaclient.util.rx.SchedulerProvider
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*


/**
 * Created by hunachi on 2018/02/03.
 */
class GithubApiRepositoryTest {
    
    private lateinit var scheduler: SchedulerProvider
    private lateinit var githubApiRepository: GithubApiRepository
    private var user: User = User(userName = "hunachi")
    private val ownerName = "hunachi"
    
    @Before
    fun init() {
        scheduler = TestSchedulerProvider()
        githubApiRepository = GithubApiRepository(Key.token, "hunachi")
    }
    
    @Test
    @Before
    fun userTest() {
        val callback = mock(Callback::class.java)
        user(callback)
        verify(callback, timeout(1000)).onSuccess()
    }
    
    @Test
    fun followerEventTest() {
        val callbackFollowerEvent = mock(Callback::class.java)
        followerEvent(callbackFollowerEvent)
        verify(callbackFollowerEvent, timeout(1000)).onSuccess()
    }
    
    @Test
    fun feedTest() {
        val callbackFeed = mock(Callback::class.java)
        feed(callbackFeed)
        verify(callbackFeed, timeout(1000)).onSuccess()
    }
    
    @Test
    fun repositoryTest() {
        val callbackRepository = mock(Callback::class.java)
        repository(callbackRepository)
        verify(callbackRepository, timeout(1000)).onSuccess()
    }
    
    @Test
    fun followerTest() {
        val callbackFollower = mock(Callback::class.java)
        follower(callbackFollower)
        verify(callbackFollower, timeout(1000)).onSuccess()
    }
    
    @Test
    fun followingTest() {
        val callbackFollowing = mock(Callback::class.java)
        following(callbackFollowing)
        verify(callbackFollowing, timeout(1000)).onSuccess()
    }
    
    @Test
    fun gistTest() {
        val callbackGist = mock(Callback::class.java)
        gists(callbackGist)
        verify(callbackGist, timeout(1000)).onSuccess()
    }
    
    @Test
    fun staredGistTest() {
        val callbackStaredGists = mock(Callback::class.java)
        staredGists(callbackStaredGists)
        verify(callbackStaredGists, timeout(1000)).onSuccess()
    }
    
    @Test
    fun watchingRepoTest() {
        val callbackWatchingRepo = mock(Callback::class.java)
        watchingRepo(callbackWatchingRepo)
        verify(callbackWatchingRepo, timeout(1000)).onSuccess()
    }
    
    @Test
    fun staringTest() {
        val callbackStaring = mock(Callback::class.java)
        staring(callbackStaring)
        verify(callbackStaring, timeout(1000)).onSuccess()
    }
    
    @Test
    fun commitTest() {
        val callbackCommit = mock(Callback::class.java)
        commit(callbackCommit)
        verify(callbackCommit, timeout(1000)).onSuccess()
    }
    
    @Test
    fun contributeCounterTest() {
        val callbackUpdateContributionCount = mock(Callback::class.java)
        updateContributionCount(callbackUpdateContributionCount)
        verify(callbackUpdateContributionCount, timeout(1000)).onSuccess()
    }
    
    private fun user(callback: Callback) =
        githubApiRepository.user("hunachi")
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
            .subscribe({
                user = it
                callback.onSuccess()
            }, {
                callback.onError()
            })
    
    private fun followerEvent(callback: Callback) =
        githubApiRepository.followerEvent(user.userName)
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
            .subscribe({
                callback.onSuccess()
            }, {
                it.printStackTrace()
                callback.onError()
            })
    
    
    private fun feed(callback: Callback) =
        githubApiRepository.feed(user.userName)
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
            .subscribe({
                callback.onSuccess()
            }, {
                it.printStackTrace()
                callback.onError()
            })
    
    private fun repository(callback: Callback) =
        githubApiRepository.repository(user.userName, "GitHunaClient")
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
            .subscribe({
                callback.onSuccess()
            }, {
                callback.onError()
            })
    
    private fun follower(callback: Callback) {
        githubApiRepository.follower(user.userName)
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
            .subscribe({
                callback.onError()
            }, {
                callback.onError()
            })
    }
    
    private fun following(callback: Callback) =
        githubApiRepository.following(user.userName)
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
            .subscribe({
                callback.onSuccess()
            }, {
                callback.onError()
            })
    
    private fun gists(callback: Callback) =
        githubApiRepository.gists(user.userName)
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
            .subscribe({
                callback.onSuccess()
            }, {
                callback.onError()
            })
    
    private fun staredGists(callback: Callback) =
        githubApiRepository.staredGists()
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
            .subscribe({
                callback.onSuccess()
            }, {
                callback.onError()
            })
    
    private fun watchingRepo(callback: Callback) {
        githubApiRepository.watchingRepo(user.userName)
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
            .subscribe({
                callback.onSuccess()
            }, {
                callback.onError()
            })
    }
    
    private fun staring(callback: Callback) {
        githubApiRepository.staring(user.userName)
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
            .subscribe({
                callback.onSuccess()
            }, {
                callback.onError()
            })
    }
    
    private fun commit(callback: Callback) =
        githubApiRepository.repoCommitStatus(user.userName, "GitHunaClient")
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
            .subscribe({
                callback.onSuccess()
            }, {
                callback.onError()
            })
    
    private fun updateContributionCount(callback: Callback) =
        githubApiRepository.repositories(ownerName)
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
            .map { if (it.isNotEmpty()) updateCommits(it) }
            .subscribe({
                callback.onSuccess()
            }, {
                callback.onError()
            })
    
    private fun updateCommits(list: List<Repository>) {
        var newContributionCount = 0
        list.filter { it.updatedAt.substring(0, 10) > "2018-02-10" }
            .forEach {
                githubApiRepository.repoCommitStatus(ownerName, it.name)
                    .subscribeOn(scheduler.io())
                    .observeOn(scheduler.ui())
                    .subscribe({
                        if (it != null)
                            newContributionCount += it.owner?.last() ?: 0
                    })
            }
    }
    
}
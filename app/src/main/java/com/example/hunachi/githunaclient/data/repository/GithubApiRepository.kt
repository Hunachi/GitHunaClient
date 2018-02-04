package com.example.hunachi.githunaclient.data.repository

import com.example.hunachi.githunaclient.data.api.GithubApi
import com.example.hunachi.githunaclient.data.api.responce.User
import com.example.hunachi.githunaclient.data.repository.adapter.GithubApiAdapter
import com.example.hunachi.githunaclient.util.rx.SchedulerProvider
import io.reactivex.Observable
import io.reactivex.Scheduler

/**
 * Created by hunachi on 2018/02/03.
 */
class GithubApiRepository(
        private val scheduler: SchedulerProvider,
        private val token: String
) {
    
    fun user(): Observable<User> = GithubApiAdapter.githubApi
            .user(token)
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
    
    fun follwerEvent(user: String, pages: Int) = GithubApiAdapter.githubApi
            .followerEvents(token = token, user = user, pages = pages)
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
    
}
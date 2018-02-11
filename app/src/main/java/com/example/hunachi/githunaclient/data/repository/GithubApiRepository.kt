package com.example.hunachi.githunaclient.data.repository

import com.example.hunachi.githunaclient.data.api.GithubApi
import com.example.hunachi.githunaclient.data.api.responce.User
import com.example.hunachi.githunaclient.data.repository.adapter.GithubApiAdapter
import com.example.hunachi.githunaclient.util.rx.SchedulerProvider
import io.reactivex.Observable
import io.reactivex.Scheduler
import retrofit2.http.Url

/**
 * Created by hunachi on 2018/02/03.
 */
class GithubApiRepository(
        private val scheduler: SchedulerProvider,
        private val token: String
) {
    
    fun user(): Observable<User> = GithubApiAdapter.githubApi
            .user(token)
    
    fun followerEvent(user: String, pages: Int) = GithubApiAdapter.githubApi
            .followerEvents(token = token, user = user, pages = pages)
    
    fun contribution(@Url url: String) = GithubApiAdapter.githubApi
            .contribute(url)
    
}
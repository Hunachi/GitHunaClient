package com.example.hunachi.githunaclient.data.repository

import com.example.hunachi.githunaclient.data.api.GithubApi
import com.example.hunachi.githunaclient.data.api.responce.User
import com.example.hunachi.githunaclient.data.repository.adapter.GithubApiAdapter
import com.example.hunachi.githunaclient.util.rx.SchedulerProvider
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import retrofit2.http.Url

/**
 * Created by hunachi on 2018/02/03.
 */
class GithubApiRepository(
        private val token: String //TODO
) {
    
    fun ownerUser(): Single<User> = GithubApiAdapter.githubApi
            .ownerUser(token)
    
    fun user(userName: String): Observable<User> = GithubApiAdapter.githubApi
            .user(userName, token)
    
    fun followerEvent(user: String, pages: Int) = GithubApiAdapter.githubApi
            .followerEvents(token = token, user = user, pages = pages)
    
    fun repository(owner: String, repo: String) = GithubApiAdapter.githubApi
            .repository(owner = owner, repo = repo, token = token)
    
    fun contribution(@Url url: String) = GithubApiAdapter.githubApi
            .contribute(url)
    
}
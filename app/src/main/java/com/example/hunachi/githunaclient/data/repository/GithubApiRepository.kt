package com.example.hunachi.githunaclient.data.repository

import com.example.hunachi.githunaclient.data.api.responce.User
import com.example.hunachi.githunaclient.data.repository.adapter.GithubApiAdapter
import com.example.hunachi.githunaclient.presentation.application.MainApplication
import com.example.hunachi.githunaclient.presentation.application.MyApplication
import io.reactivex.Single
import retrofit2.http.Url

/**
 * Created by hunachi on 2018/02/03.
 */
class GithubApiRepository(
        private val application: MyApplication
) {
    
    private val token by lazy { (application as MainApplication).token }
    
    fun ownerUser(): Single<User> = GithubApiAdapter.githubApi
            .ownerUser(token)
    
    fun user(userName: String): Single<User> =
            if (userName.isNotBlank()) GithubApiAdapter.githubApi
                    .user(userName, token)
            else ownerUser()
    
    fun followerEvent(userName: String, pages: Int) = GithubApiAdapter.githubApi
            .followerEvents(token, pages, userName)
    
    fun repository(owner: String, repo: String) = GithubApiAdapter.githubApi
            .repository(owner, repo, token)
    
    fun follower(userName: String) = GithubApiAdapter.githubApi
            .follower(userName, token)
    
    fun following(userName: String) = GithubApiAdapter.githubApi
            .following(userName, token)
    
    fun gists(userName: String) = GithubApiAdapter.githubApi
            .gists(userName, token)
    
    fun starredGist(userName: String) = GithubApiAdapter.githubApi
            .starredGist(token)
    
    fun watchingRepo(userName: String) = GithubApiAdapter.githubApi
            .watchingRepo(userName, token)
    
    fun starring(userName: String) = GithubApiAdapter.githubApi
            .starring(userName, token)
    
    fun contribution(@Url url: String) = GithubApiAdapter.githubApi
            .contribute(url)
    
}
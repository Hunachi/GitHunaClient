package com.example.hunachi.githunaclient.data.repository

import com.example.hunachi.githunaclient.data.api.responce.User
import com.example.hunachi.githunaclient.data.repository.adapter.GithubApiAdapter
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.Url

/**
 * Created by hunachi on 2018/02/03.
 */
class GithubApiRepository(
        private val token: String
) {
    
    fun ownerUser(): Observable<User> = GithubApiAdapter.githubApi
            .ownerUser(token = token)
    
    fun user(userName: String): Observable<User> =
            if (userName.isNotBlank()) GithubApiAdapter.githubApi
                    .user(userName = userName, token = token)
            else ownerUser()
    
    fun followerEvent(userName: String, pages: Int) = GithubApiAdapter.githubApi
            .followerEvents(pages = pages, userName = userName, token = token)
    
    fun repositories(userName: String) = GithubApiAdapter.githubApi
            .repositories(userName = userName, token = token)
    
    fun repository(ownerName: String, repositoryName: String) = GithubApiAdapter.githubApi
            .repository(ownerName = ownerName, repositoryName = repositoryName, token = token)
    
    fun follower(userName: String) = GithubApiAdapter.githubApi
            .follower(userName = userName,token =  token)
    
    fun following(userName: String) = GithubApiAdapter.githubApi
            .following(userName = userName, token = token)
    
    fun gists(userName: String) = GithubApiAdapter.githubApi
            .gists(userName = userName, token = token)
    
    fun staredGists() = GithubApiAdapter.githubApi
            .starredGist(token = token)
    
    fun watchingRepo(userName: String) = GithubApiAdapter.githubApi
            .watchingRepo(userName = userName, token = token)
    
    fun staring(userName: String) = GithubApiAdapter.githubApi
            .staring(userName = userName, token = token)
    
    fun contribution(@Url url: String) = GithubApiAdapter.githubApi
            .contribute(url = url)
    
}
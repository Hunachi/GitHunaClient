package com.example.hunachi.githunaclient.data.api

import android.arch.lifecycle.LifecycleOwner
import com.example.hunachi.githunaclient.data.api.responce.Event
import com.example.hunachi.githunaclient.data.api.responce.Gist
import com.example.hunachi.githunaclient.data.api.responce.Repository
import com.example.hunachi.githunaclient.data.api.responce.User
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.*

/**
 * Created by hunachi on 2018/01/30.
 */
interface GithubApi {
    
    @GET("user")
    fun ownerUser(
            @Query("access_token") token: String
    ): Single<User>
    
    @GET("users/{user_name}")
    fun user(
            @Path("user_name")userName: String,
            @Query("access_token") token: String
    ): Single<User>
    
    @GET("users/{username}/received_events")
    fun followerEvents(
            @Path("username") userName: String,
            @Query("pages") pages: Int,
            @Query("access_token") token: String
    ): Observable<List<Event>>
    
    @GET("repos/{owner}/{repo}")
    fun repository(
            @Path("owner") owner: String,
            @Path("repo") repo: String,
            @Query("access_token") token: String
    ): Observable<Repository>
    
    @GET("users/{username}/followers")
    fun follower(
            @Path("username") userName: String,
            @Query("access_token") token: String
    ): Single<List<User>>
    
    @GET("users/{username}/following")
    fun following(
            @Path("username") userName: String,
            @Query("access_token") token: String
    ): Single<List<User>>
    
    @GET("users/{username}/gists")
    fun gists(
            @Path("username") userName: String,
            @Query("access_token") token: String
    ): Single<List<Gist>>
    
    @GET("gists/starred")
    fun starredGist(
            @Query("access_token") token: String
    ): Single<List<Gist>>
    
    @GET("/users/{username}/subscriptions")
    fun watchingRepo(
            @Path("username") userName: String,
            @Query("access_token") token: String
    ):Single<List<Repository>>
    
    @GET("users/:username/starred")
    fun starring(
            @Path("username") userName: String,
            @Query("access_token") token: String
    ): Single<List<Repository>>
    
    @GET
    fun contribute(@Url url: String): Observable<String>
}
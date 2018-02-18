package com.example.hunachi.githunaclient.data.api

import android.arch.lifecycle.LifecycleOwner
import com.example.hunachi.githunaclient.data.api.responce.Event
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
    ): Observable<User>
    
    
    @GET("users/{user}/received_events")
    fun followerEvents(
            @Path("user") user: String,
            @Query("pages") pages: Int,
            @Query("access_token") token: String
    ): Observable<List<Event>>
    
    @GET("/repos/{owner}/{repo}")
    fun repository(
            @Path("owner") owner: String,
            @Path("repo") repo: String,
            @Query("access_token") token: String
    ): Observable<Repository>
    
    @GET
    fun contribute(@Url url: String): Observable<String>
}
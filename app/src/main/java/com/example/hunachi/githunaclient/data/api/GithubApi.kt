package com.example.hunachi.githunaclient.data.api

import com.example.hunachi.githunaclient.data.api.responce.Event
import com.example.hunachi.githunaclient.data.api.responce.User
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Created by hunachi on 2018/01/30.
 */
interface GithubApi {
    
    @GET("user")
    fun user(
            @Query("access_token") token: String
    ): Observable<User>
    
    
    @GET("users/{user}/received_events")
    fun followerEvents(
            @Path("user") user: String,
            @Query("pages") pages: Int,
            @Query("access_token") token: String
    ): Observable<List<Event>>
    
    @GET
    fun contribute(@Url url: String): Observable<String>
}
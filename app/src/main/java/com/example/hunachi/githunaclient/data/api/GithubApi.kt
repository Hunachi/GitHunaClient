package com.example.hunachi.githunaclient.data.api

import com.example.hunachi.githunaclient.data.api.responce.Event
import com.example.hunachi.githunaclient.data.api.responce.User
import io.reactivex.Observable
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

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
    
}
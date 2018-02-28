package com.example.hunachi.githunaclient.data.api

import android.arch.lifecycle.LifecycleOwner
import com.example.hunachi.githunaclient.data.api.responce.*
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
    ): Observable<User>
    
    @GET("users/{user_name}")
    fun user(
            @Path("user_name") userName: String,
            @Query("access_token") token: String
    ): Observable<User>
    
    @GET("users/{username}/received_events")
    fun followerEvents(
            @Path("username") userName: String,
            @Query("page") page: Int,
            @Query("per_page") perPage: Int,
            @Query("access_token") token: String
    ): Observable<List<Event>>
    
    @GET("users/{username}/events")
    fun feeds(
            @Path("username") userName: String,
            @Query("access_token") token: String
    ): Observable<List<Event>>
    
    @GET("users/{username}/repos")
    fun repositories(
            @Path("username") userName: String,
            @Query("page") page: Int,
            @Query("per_page") perPage: Int,
            @Query("access_token") token: String
    ): Observable<List<Repository>>
    
    @GET("user/repos")
    fun ownerRepositories(
            @Query("page") page: Int,
            @Query("per_page") perPage: Int,
            @Query("access_token") token: String
    ): Observable<List<Repository>>
    
    @GET("repos/{ownername}/{repositoryname}")
    fun repository(
            @Path("ownername") ownerName: String,
            @Path("repositoryname") repositoryName: String,
            @Query("access_token") token: String
    ): Observable<Repository>
    
    @GET("users/{username}/followers")
    fun follower(
            @Path("username") userName: String,
            @Query("page") page: Int,
            @Query("per_page") perPage: Int,
            @Query("access_token") token: String
    ): Observable<List<ChildUser>>
    
    @GET("users/{username}/following")
    fun following(
            @Path("username") userName: String,
            @Query("page") page: Int,
            @Query("per_page") perPage: Int,
            @Query("access_token") token: String
    ): Observable<List<ChildUser>>
    
    @GET("users/{username}/gists")
    fun gists(
            @Path("username") userName: String,
            @Query("access_token") token: String
    ): Observable<List<Gist>>
    
    @GET("gists/starred")
    fun starredGist(
            @Query("access_token") token: String
    ): Observable<List<Gist>>
    
    @GET("/users/{username}/subscriptions")
    fun watchingRepo(
            @Path("username") userName: String,
            @Query("access_token") token: String
    ): Observable<List<Repository>>
    
    @GET("users/{username}/starred")
    fun staring(
            @Path("username") userName: String,
            @Query("access_token") token: String
    ): Observable<List<Repository>>
    
    @GET("repos/{ownername}/{repositoryname}/stats/participation")
    fun repoCommitStatus(
            @Path("ownername") ownerName: String,
            @Path("repositoryname") repositoryName: String,
            @Query("access_token") token: String
    ): Observable<WeeklyCommit>
    
}
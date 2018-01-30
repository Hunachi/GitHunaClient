package com.example.hunachi.githunaclient.data.api

import android.media.session.MediaSession
import com.example.hunachi.githunaclient.data.api.responce.Token
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by hunachi on 2018/01/31.
 */
interface GithubOauth {
    
    @FormUrlEncoded
    @POST("/access_token")
    fun accessToken(
            @Field("client_id") client_id: String,
            @Field("client_secret") client_secret: String,
            @Field("code")code: String,
            @Field("state")state: String
    ): Observable<Token>
    
    
}
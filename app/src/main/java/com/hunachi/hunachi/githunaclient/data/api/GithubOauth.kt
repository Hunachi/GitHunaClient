package com.hunachi.hunachi.githunaclient.data.api

import com.hunachi.hunachi.githunaclient.data.api.responce.Token
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Created by hunachi on 2018/01/31.
 */
interface GithubOauth {
    
    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("access_token")
    fun accessToken(
            @Field("client_id") clientId: String,
            @Field("client_secret") clientSecret: String,
            @Field("code")code: String
    ): Observable<Token>
    
}
package com.example.hunachi.githunaclient.data

import android.net.Uri
import com.example.hunachi.githunaclient.data.api.GithubApi
import com.example.hunachi.githunaclient.data.api.responce.mapper.ApplicationJsonAdapterFactory
import com.squareup.moshi.Moshi
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by hunachi on 2018/01/31.
 */
class GithubApiModule(token: String = ""){
    
    private var token: String = token
    
    val githubApi: GithubApi
    
    init {
        val kotshi by lazy {
            Moshi.Builder()
                    .add(ApplicationJsonAdapterFactory.INSTANCE)
                    .build()
        }
        /*log出力させたい*/
        val logging = HttpLoggingInterceptor()
                .apply {
                    level = HttpLoggingInterceptor.Level.BASIC
                }
        val httpClient = OkHttpClient.Builder()
                .apply {
                    addInterceptor(logging)
                }
        
        val retrofit by lazy {
            Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .addConverterFactory(MoshiConverterFactory.create(kotshi))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(httpClient.build())
                    .build()
        }
        githubApi = retrofit.create(GithubApi::class.java)
    }
}
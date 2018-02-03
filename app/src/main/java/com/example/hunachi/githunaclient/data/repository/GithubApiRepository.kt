package com.example.hunachi.githunaclient.data.repository

import com.example.hunachi.githunaclient.data.api.GithubApi
import com.example.hunachi.githunaclient.data.repository.adapter.GithubApiAdapter
import com.example.hunachi.githunaclient.util.rx.SchedulerProvider
import io.reactivex.Scheduler
import java.util.*

/**
 * Created by hunachi on 2018/02/03.
 */
class GithubApiRepository(private val scheduler: SchedulerProvider) {
    
    fun user(token: String) = GithubApiAdapter.githubApi
            .user(token)
            .subscribeOn(scheduler.io())
    
    
}
package com.example.hunachi.githunaclient.presentation.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.view.MenuItem
import com.example.hunachi.githunaclient.data.api.responce.User
import com.example.hunachi.githunaclient.data.repository.GithubApiRepository
import com.example.hunachi.githunaclient.presentation.base.BaseViewModel
import com.example.hunachi.githunaclient.util.BottomNavigationListener
import com.example.hunachi.githunaclient.presentation.helper.Navigator
import com.example.hunachi.githunaclient.util.rx.SchedulerProvider
import io.reactivex.processors.PublishProcessor

/**
 * Created by hunachi on 2018/01/27.
 */
class MainViewModel(
        private val scheduler: SchedulerProvider,
        private val githubApiRepository: GithubApiRepository
) : BaseViewModel() {
    
    private val navigateProcessor: PublishProcessor<MenuItem> = PublishProcessor.create()
    private val userProcessor: PublishProcessor<User> = PublishProcessor.create()
    val navigateListener: LiveData<MenuItem> = LiveDataReactiveStreams.fromPublisher(navigateProcessor)
    val user: LiveData<User> = LiveDataReactiveStreams.fromPublisher(userProcessor)
    
    fun setupUser(){
        if(user.value == null)githubApiRepository.ownerUser()
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe({
                    userProcessor.onNext(it)
                },{
                    userProcessor.onError(it) //TODO
                })
        else userProcessor.onNext(user.value)
    }
    
    fun onItemSelected(): BottomNavigationListener = BottomNavigationListener { item ->
        navigateProcessor.onNext(item)
        true
    }
}
package com.example.hunachi.githunaclient.presentation.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.support.annotation.CheckResult
import android.view.MenuItem
import com.example.hunachi.githunaclient.data.api.responce.User
import com.example.hunachi.githunaclient.data.repository.GithubApiRepository
import com.example.hunachi.githunaclient.presentation.base.BaseViewModel
import com.example.hunachi.githunaclient.util.BottomNavigationListener
import com.example.hunachi.githunaclient.presentation.helper.Navigator
import com.example.hunachi.githunaclient.util.ErrorCallback
import com.example.hunachi.githunaclient.util.NavigatorCallback
import com.example.hunachi.githunaclient.util.rx.SchedulerProvider
import io.reactivex.processors.PublishProcessor

/**
 * Created by hunachi on 2018/01/27.
 */
class MainViewModel(
        private val scheduler: SchedulerProvider,
        private val githubApiRepository: GithubApiRepository
) : BaseViewModel() {
    
    private val userProcessor: PublishProcessor<User> = PublishProcessor.create()
    private val navigatorProcessor: PublishProcessor<Int> = PublishProcessor.create()
    private val errorProcessor: PublishProcessor<Boolean> = PublishProcessor.create()
    val user: LiveData<User> = LiveDataReactiveStreams.fromPublisher(userProcessor)
    val navigator: LiveData<Int> = LiveDataReactiveStreams.fromPublisher(navigatorProcessor)
    val error: LiveData<Boolean> = LiveDataReactiveStreams.fromPublisher(errorProcessor)
    
    fun setupUser() {
        if (user.value == null) githubApiRepository.ownerUser()
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe({
                    userProcessor.onNext(it)
                }, {
                    onError()
                })
    }
    
    fun onItemSelected(): BottomNavigationListener = BottomNavigationListener { item ->
        navigatorProcessor.onNext(item.itemId)
        true
    }
    
    private fun onError(){
        errorProcessor.run {
            onNext(true)
            onNext(false)
        }
    }
}
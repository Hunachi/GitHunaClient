package com.example.hunachi.githunaclient.presentation.main

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
/*TODO login時にuserの名前情報を取っておけばこれはいらなくない?*/
class MainViewModel(
        private val navigator: Navigator,
        private val scheduler: SchedulerProvider,
        private val githubApiRepository: GithubApiRepository
) : BaseViewModel() {
    
    val navigateProcessor: PublishProcessor<MenuItem> = PublishProcessor.create()
    val userProcessor: PublishProcessor<User> = PublishProcessor.create()
    
    fun setupUser(){
        githubApiRepository.ownerUser()
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe({
                    userProcessor.onNext(it)
                },{
                    userProcessor.onError(it)
                })
    }
    
    fun onItemSelected(): BottomNavigationListener = BottomNavigationListener { item ->
        navigateProcessor.onNext(item)
        true
    }
}
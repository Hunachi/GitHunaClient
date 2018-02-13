package com.example.hunachi.githunaclient.presentation.main.profile

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.databinding.Bindable
import com.example.hunachi.githunaclient.data.api.responce.User
import com.example.hunachi.githunaclient.data.repository.GithubApiRepository
import com.example.hunachi.githunaclient.presentation.MyApplication
import com.example.hunachi.githunaclient.presentation.base.BaseViewModel
import com.example.hunachi.githunaclient.presentation.fragment.event.FollowerEvent
import com.example.hunachi.githunaclient.presentation.helper.Navigator
import com.example.hunachi.githunaclient.util.rx.AppSchedulerProvider
import com.example.hunachi.githunaclient.util.rx.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.processors.PublishProcessor

/**
 * Created by hunachi on 2018/02/11.
 */
class MainProfileViewModel(
        private val navigator: Navigator,
        private val githubApiRepository: GithubApiRepository,
        private val scheduler: SchedulerProvider,
        application: MyApplication
) : BaseViewModel(application) {
    
    val processor: PublishProcessor<User> = PublishProcessor.create()
    
    override fun onCreate() {
        super.onCreate()
        setUpUser()
    }
    
    private fun setUpUser() {
        githubApiRepository.user()
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe({
                    processor.onNext(it)
                },{
                    //TODO make dialog??.
                    processor.onError(it)
                },{
                    processor.onComplete()
                })
    }
    
}
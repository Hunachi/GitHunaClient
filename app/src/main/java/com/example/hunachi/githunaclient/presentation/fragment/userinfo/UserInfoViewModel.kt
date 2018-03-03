package com.example.hunachi.githunaclient.presentation.fragment.userinfo

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import com.example.hunachi.githunaclient.data.api.responce.User
import com.example.hunachi.githunaclient.data.repository.GithubApiRepository
import com.example.hunachi.githunaclient.presentation.base.BaseFragmentViewModel
import com.example.hunachi.githunaclient.util.rx.SchedulerProvider
import io.reactivex.processors.PublishProcessor

/**
 * Created by hunachi on 2018/02/03.
 */
class UserInfoViewModel(
        private val githubApiRepository: GithubApiRepository,
        private val scheduler: SchedulerProvider,
        private val userName: String
) : BaseFragmentViewModel() {
    
    private val userProcessor: PublishProcessor<User> = PublishProcessor.create()
    private val launchWebProcessor: PublishProcessor<String> = PublishProcessor.create()
    private val loadingProcessor: PublishProcessor<Boolean> = PublishProcessor.create()
    private val errorProcessor: PublishProcessor<Boolean> = PublishProcessor.create()
    val user: LiveData<User> = LiveDataReactiveStreams.fromPublisher(userProcessor)
    val launchWeb: LiveData<String> = LiveDataReactiveStreams.fromPublisher(launchWebProcessor)
    val loading: LiveData<Boolean> = LiveDataReactiveStreams.fromPublisher(loadingProcessor)
    val error: LiveData<Boolean> = LiveDataReactiveStreams.fromPublisher(errorProcessor)
    
    
    override fun onStart() {
        if (user.value == null) {
            githubApiRepository.user(userName)
                    .subscribeOn(scheduler.io())
                    .observeOn(scheduler.ui())
                    .subscribe({
                        userProcessor.onNext(it)
                    }, {
                        onError()
                        it.printStackTrace()
                    }, {
                        loadingProcessor.onNext(false)
                    })
        } else loadingProcessor.onNext(false)
    }
    
    fun onClickUserBlog() {
        user.value?.blog?.let { launchWebProcessor.onNext(it) }
    }
    
    fun onClickUserIcon() {
        user.value?.htmlUrl?.let { launchWebProcessor.onNext(it) }
    }
    
    private fun onError() {
        errorProcessor.run {
            onNext(true)
            onNext(false)
        }
    }
}
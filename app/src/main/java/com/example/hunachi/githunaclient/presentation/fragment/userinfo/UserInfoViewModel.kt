package com.example.hunachi.githunaclient.presentation.fragment.userinfo

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import com.example.hunachi.githunaclient.data.api.responce.User
import com.example.hunachi.githunaclient.data.repository.GithubApiRepository
import com.example.hunachi.githunaclient.presentation.base.BaseFragmentViewModel
import com.example.hunachi.githunaclient.presentation.helper.Navigator
import com.example.hunachi.githunaclient.util.ErrorCallback
import com.example.hunachi.githunaclient.util.GoWebCallback
import com.example.hunachi.githunaclient.util.LoadingCallback
import com.example.hunachi.githunaclient.util.rx.SchedulerProvider
import io.reactivex.processors.PublishProcessor

/**
 * Created by hunachi on 2018/02/03.
 */
class UserInfoViewModel(
        val githubApiRepository: GithubApiRepository,
        val scheduler: SchedulerProvider
) : BaseFragmentViewModel() {
    
    private val userProcessor: PublishProcessor<User> = PublishProcessor.create()
    val user: LiveData<User> = LiveDataReactiveStreams.fromPublisher(userProcessor)
    private lateinit var goWebCallback: GoWebCallback
    
    fun setUp(
            userName: String, goWebCallback: GoWebCallback,
            loadingCallback: LoadingCallback, errorCallback: ErrorCallback
    ) {
        this.goWebCallback = goWebCallback
        if (user.value == null) {
            loadingCallback(true)
            githubApiRepository.user(userName)
                    .subscribeOn(scheduler.io())
                    .observeOn(scheduler.ui())
                    .subscribe({
                        userProcessor.onNext(it)
                    }, {
                        it.printStackTrace()
                        errorCallback()
                    }, {
                        loadingCallback(false)
                    })
        }
    }
    
    fun onClickUserBlog() {
        user.value?.blog?.let { goWebCallback(it) }
    }
    
    fun onClickUserIcon() {
        user.value?.htmlUrl?.let { goWebCallback(it) }
    }
}
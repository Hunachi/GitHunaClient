package com.example.hunachi.githunaclient.presentation.fragment.userinfo

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import com.example.hunachi.githunaclient.data.api.responce.User
import com.example.hunachi.githunaclient.data.repository.GithubApiRepository
import com.example.hunachi.githunaclient.presentation.base.BaseFragmentViewModel
import com.example.hunachi.githunaclient.presentation.helper.Navigator
import com.example.hunachi.githunaclient.util.rx.SchedulerProvider
import io.reactivex.processors.PublishProcessor

/**
 * Created by hunachi on 2018/02/03.
 */
class UserInfoViewModel(
        val navigator: Navigator,
        val githubApiRepository: GithubApiRepository,
        val scheduler: SchedulerProvider
) : BaseFragmentViewModel() {
    
    val nameExist = ObservableField<Boolean>(true)
    private val userProcessor: PublishProcessor<User> = PublishProcessor.create()
    val user: LiveData<User> = LiveDataReactiveStreams.fromPublisher(userProcessor)
    private var userName: String? = null
    
    fun setUp(userName: String) {
        if(this.userName.isNullOrBlank())
        githubApiRepository.user(userName)
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe({
                    if (it.userName.isBlank()) nameExist.set(false)
                    userProcessor.onNext(it)
                }, {
                    it.printStackTrace()
                })
        else userProcessor.onNext(user.value)
    }
    
}
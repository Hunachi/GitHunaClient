package com.example.hunachi.githunaclient.presentation.fragment

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.databinding.ObservableField
import android.util.Log
import com.example.hunachi.githunaclient.data.api.responce.User
import com.example.hunachi.githunaclient.data.repository.GithubApiRepository
import com.example.hunachi.githunaclient.presentation.MyApplication
import com.example.hunachi.githunaclient.presentation.base.BaseFragmentViewModel
import com.example.hunachi.githunaclient.presentation.helper.Navigator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.processors.PublishProcessor
import io.reactivex.schedulers.Schedulers

/**
 * Created by hunachi on 2018/02/03.
 */
class UserInfoViewModel(
        val navigator: Navigator,
        val application: MyApplication,
        val githubApiRepository: GithubApiRepository,
        var user: User
) : BaseFragmentViewModel(application) {
    
    private val processor: PublishProcessor<User> = PublishProcessor.create()
    var mUser: LiveData<User> = LiveDataReactiveStreams.fromPublisher(processor)
    var nameExist = ObservableField<Boolean>(true)
    
    override fun onCreateView() {
        super.onCreateView()
        setUp()
    }
    
    private fun setUp() {
        githubApiRepository.user()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.name == null) nameExist.set(false)
                    processor.onNext(it)
                }, {
                    it.printStackTrace()
                })
    }
    
}
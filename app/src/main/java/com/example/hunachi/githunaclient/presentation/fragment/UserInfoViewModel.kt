package com.example.hunachi.githunaclient.presentation.fragment

import android.databinding.ObservableField
import com.example.hunachi.githunaclient.data.api.responce.User
import com.example.hunachi.githunaclient.data.repository.GithubApiRepository
import com.example.hunachi.githunaclient.presentation.MyApplication
import com.example.hunachi.githunaclient.presentation.base.BaseFragmentViewModel
import com.example.hunachi.githunaclient.presentation.helper.Navigator
/**
 * Created by hunachi on 2018/02/03.
 */
class UserInfoViewModel(
        val navigator: Navigator,
        val application: MyApplication,
        val githubApiRepository: GithubApiRepository,
        val user: User
) : BaseFragmentViewModel(application) {
    
    //private val processor: PublishProcessor<User> = PublishProcessor.create()
    //var mUser: LiveData<User> = LiveDataReactiveStreams.fromPublisher(processor)
    val nameExist = ObservableField<Boolean>(true)
    
    override fun onStart() {
        super.onStart()
        setUp()
    }
    
    private fun setUp() {
        if (user.name.isNullOrBlank()) nameExist.set(false)
        /*githubApiRepository.user()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.name == null) nameExist.set(false)
                    processor.onNext(it)
                }, {
                    it.printStackTrace()
                })*/
    }
    
}
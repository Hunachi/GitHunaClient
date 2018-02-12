package com.example.hunachi.githunaclient.presentation.main.profile

import android.databinding.Bindable
import com.example.hunachi.githunaclient.data.api.responce.User
import com.example.hunachi.githunaclient.data.repository.GithubApiRepository
import com.example.hunachi.githunaclient.presentation.MyApplication
import com.example.hunachi.githunaclient.presentation.base.BaseViewModel
import com.example.hunachi.githunaclient.presentation.fragment.event.FollowerEvent
import com.example.hunachi.githunaclient.presentation.helper.Navigator

/**
 * Created by hunachi on 2018/02/11.
 */
class MainProfileViewModel(
        private val navigator: Navigator,
        private val githubApiRepository: GithubApiRepository,
        application: MyApplication,
        private var user: User
) : BaseViewModel(application) {
    
    override fun onCreate() {
        super.onCreate()
    }
    
    fun setUpUser() {
        githubApiRepository.user()
    }
    
}
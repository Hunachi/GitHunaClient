package com.example.hunachi.githunaclient.presentation.fragment.profile

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
        val githubApiRepository: GithubApiRepository,
        val user: User
) : BaseFragmentViewModel() {
    
    val nameExist = ObservableField<Boolean>(true)
    
    override fun onStart() {
        super.onStart()
        setUp()
    }
    
    private fun setUp() {
        if (user.name.isNullOrBlank()) nameExist.set(false)
    }
    
}
package com.example.hunachi.githunaclient.presentation.fragment

import android.arch.lifecycle.LifecycleOwner
import android.databinding.Bindable
import android.databinding.ObservableField
import com.example.hunachi.githunaclient.data.api.responce.User
import com.example.hunachi.githunaclient.presentation.MyApplication
import com.example.hunachi.githunaclient.presentation.base.BaseFragmentViewModel
import com.example.hunachi.githunaclient.presentation.helper.Navigator

/**
 * Created by hunachi on 2018/02/03.
 */
class UserInfoViewModel(
        val navigator: Navigator,
        val application: MyApplication,
        val owner: LifecycleOwner,
        val user: User
) : BaseFragmentViewModel(application) {
    
    var name = ObservableField<String>(user.name)
    var nameExist = ObservableField<Boolean>(true)
    var userName = ObservableField<String>(user.userName)
    var avatar = ObservableField<String>(user.avatarUrl)
    var bio = ObservableField<String>(user.bio)
    
    override fun onCreateView() {
        super.onCreateView()
        if(user == User())setUp()
    }
    
    fun setUp(){
        //TODO
    }
    
    
}
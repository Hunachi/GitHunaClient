package com.example.hunachi.githunaclient.presentation.fragment

import android.arch.lifecycle.LifecycleOwner
import android.databinding.Bindable
import com.example.hunachi.githunaclient.presentation.MyApplication
import com.example.hunachi.githunaclient.presentation.base.BaseFragmentViewModel
import com.example.hunachi.githunaclient.presentation.helper.Navigator

/**
 * Created by hunachi on 2018/02/03.
 */
class UserInfoViewModel(
        val navigator: Navigator,
        val application: MyApplication,
        val owner: LifecycleOwner
) : BaseFragmentViewModel(application) {
    
    
    
    override fun onCreateView() {
        super.onCreateView()
        setUp()
    }
    
    override fun onStart() {
        super.onStart()
    }
    
    fun setUp(){
    }
    
    
}
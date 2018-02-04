package com.example.hunachi.githunaclient.presentation.fragment

import android.arch.lifecycle.LifecycleOwner
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
    
    var userName = ""
    
    override fun onCreateView() {
        super.onCreateView()
        userName = "hoge"
        setUp()
    }
    
    fun setUp(){
    }
    
    
}
package com.example.hunachi.githunaclient.presentation.event

import android.arch.lifecycle.LifecycleOwner
import android.databinding.Bindable
import android.support.v7.app.AppCompatActivity
import com.example.hunachi.githunaclient.presentation.MyApplication
import com.example.hunachi.githunaclient.presentation.base.BaseFragmentViewModel
import com.example.hunachi.githunaclient.presentation.helper.Navigator
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.androidSupportFragmentScope

/**
 * Created by hunachi on 2018/02/03.
 */
class UserInfoViewModel(
        val navigator: Navigator,
        val application: MyApplication,
        val owner: LifecycleOwner
) : BaseFragmentViewModel(application) {
    
    @Bindable
    var userName = ""
    
    override fun onCreateView() {
        super.onCreateView()
        userName = "hoge"
    }
    
    
}

val userInfoViewModelModule = Kodein.Module {
    bind<UserInfoViewModel>() with scopedSingleton(androidSupportFragmentScope) {
        UserInfoViewModel(
            navigator = with(it.activity as AppCompatActivity).instance(),
            application = instance(),
            owner = it as LifecycleOwner /*使う時が来るのか？*/
        )
    }
}
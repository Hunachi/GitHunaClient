package com.example.hunachi.githunaclient.presentation.event

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.arch.lifecycle.Observer
import android.databinding.Bindable
import android.support.v7.app.AppCompatActivity
import android.text.TextWatcher
import com.example.hunachi.githunaclient.kodein.UserInfoViewModelModule
import com.example.hunachi.githunaclient.presentation.base.BaseActivity
import com.example.hunachi.githunaclient.presentation.base.BaseFragmentViewModel
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.androidFragmentScope
import com.github.salomonbrys.kodein.android.androidSupportFragmentScope
import io.reactivex.processors.PublishProcessor

/**
 * Created by hunachi on 2018/02/03.
 */
class UserInfoViewModel(val module: UserInfoViewModelModule): BaseFragmentViewModel(module.application) {
    
    val navigator = module.navigator
    
    @Bindable
    var userName = ""
    
    override fun onCreateView() {
        super.onCreateView()
        userName = "hoge"
    }
    
    
}

val userInfoViewModelModule = Kodein.Module{
    bind<UserInfoViewModel>() with scopedSingleton(androidSupportFragmentScope){
        UserInfoViewModel(
            UserInfoViewModelModule(
                navigator = with(it.activity as AppCompatActivity).instance(),
                application = instance(),
                owner = it as LifecycleOwner /*使う時が来るのか？*/
            )
        )
    }
}
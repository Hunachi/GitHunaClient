package com.example.hunachi.githunaclient.presentation.event

import android.support.v7.app.AppCompatActivity
import com.example.hunachi.githunaclient.kodein.UserInfoViewModelModule
import com.example.hunachi.githunaclient.presentation.base.BaseFragmentViewModel
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.androidFragmentScope

/**
 * Created by hunachi on 2018/02/03.
 */
class UserInfoViewModel(val module: UserInfoViewModelModule): BaseFragmentViewModel(module.application) {

}

val AccessInformationModule = Kodein.Module{
    bind<UserInfoViewModel>() with scopedSingleton(androidFragmentScope){
        UserInfoViewModel(
            UserInfoViewModelModule(
                navigator = with(it.activity as AppCompatActivity).instance(),
                application = instance()
            )
        )
    }
}
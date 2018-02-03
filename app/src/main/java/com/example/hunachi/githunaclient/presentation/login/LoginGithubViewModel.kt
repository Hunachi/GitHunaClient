package com.example.hunachi.githunaclient.presentation.login

import android.support.v7.app.AppCompatActivity
import com.example.hunachi.githunaclient.presentation.base.BaseViewModel
import com.example.hunachi.githunaclient.presentation.MainApplication
import com.example.hunachi.githunaclient.presentation.helper.Navigator
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.androidActivityScope

/**
 * Created by hunachi on 2018/01/30.
 */
class LoginGithubViewModel(
        val navigator: Navigator,
        val application: MainApplication
) : BaseViewModel(application) {
    
    fun onClickOauth() {
        navigator.navigateToOauth()
    }
    
    fun onClickBasic() {
        //todo
    }
}

val loginViewModels = Kodein.Module {
    bind<LoginGithubViewModel>() with scopedSingleton(androidActivityScope) {
        LoginGithubViewModel(
            navigator = with(it as AppCompatActivity).instance(),
            application = instance()
        )
    }
}
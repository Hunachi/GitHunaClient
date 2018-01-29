package com.example.hunachi.githunaclient.presentation.oauth

import com.example.hunachi.githunaclient.presentation.base.BaseViewModel
import com.example.hunachi.githunaclient.util.LoginViewModules
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.android.androidActivityScope
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.scopedSingleton

/**
 * Created by hunachi on 2018/01/30.
 */
class LoginGithubViewModel(val modules: LoginViewModules): BaseViewModel(modules.application) {

}

val loginViewModels =  Kodein.Module{
    bind<LoginGithubViewModel>() with scopedSingleton(androidActivityScope){
        LoginGithubViewModel(LoginViewModules(it as LoginGitHubActivity, instance()))
    }
}
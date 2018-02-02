package com.example.hunachi.githunaclient.presentation.login

import android.support.v7.app.AppCompatActivity
import com.example.hunachi.githunaclient.presentation.base.BaseViewModel
import com.example.hunachi.githunaclient.kodein.LoginViewModels
import com.example.hunachi.githunaclient.util.Scopes
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.androidActivityScope

/**
 * Created by hunachi on 2018/01/30.
 */
class LoginGithubViewModel(private val models: LoginViewModels) : BaseViewModel(models.application) {
    
    private val oauthAdapter = models.oauthAdapter
    private val loadingDialog = models.loadingDialog
    private val navigator = models.navigator
    
    fun onClickOauth() {
        navigator.navigateToOauth()
    }
    
    fun onClickBasic() {
        //todo
    }
}

val loginViewModels = Kodein.Module {
    bind<LoginGithubViewModel>() with scopedSingleton(androidActivityScope) {
        LoginGithubViewModel(LoginViewModels(navigator = with(it as AppCompatActivity).instance(), application = instance(), oauthAdapter = with(instance<Scopes>()).instance(), loadingDialog = instance())
        )
    }
}
package com.example.hunachi.githunaclient.presentation.login

import com.example.hunachi.githunaclient.presentation.base.BaseViewModel
import com.example.hunachi.githunaclient.util.LoginViewModules
import com.example.hunachi.githunaclient.util.Scopes
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.androidActivityScope

/**
 * Created by hunachi on 2018/01/30.
 */
class LoginGithubViewModel(private val modules: LoginViewModules) : BaseViewModel(modules.application) {
    
    private val oauthAdapter = modules.oauthAdapter
    private val loadingDialog = modules.loadingDialog
    
    fun onClickOauth() {
        context.startActivity(oauthAdapter.intent)
    }
    
    fun onClickBasic() {
        //todo
    }
}

val loginViewModels = Kodein.Module {
    bind<LoginGithubViewModel>() with scopedSingleton(androidActivityScope) {
        LoginGithubViewModel(LoginViewModules(
                activity = it as LoginGithubActivity,
                application = instance(),
                oauthAdapter = with(instance<Scopes>()).instance(),
                loadingDialog = instance())
        )
    }
}
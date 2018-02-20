package com.example.hunachi.githunaclient.presentation.login

import android.support.v7.app.AppCompatActivity
import com.example.hunachi.githunaclient.presentation.dialog.LoadingDialogAdapter
import com.example.hunachi.githunaclient.presentation.login.LoginGithubViewModel
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.androidActivityScope

/**
 * Created by hunachi on 2018/02/04.
 */
val loginViewModelModule = Kodein.Module {
    bind<LoginGithubViewModel>() with scopedSingleton(androidActivityScope) {
        LoginGithubViewModel(
            navigator = with(it as AppCompatActivity).instance(),
            application = instance()
        )
    }
}
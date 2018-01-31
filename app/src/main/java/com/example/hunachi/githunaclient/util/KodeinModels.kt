package com.example.hunachi.githunaclient.util

import com.example.hunachi.githunaclient.R
import com.example.hunachi.githunaclient.data.api.modules.GithubLoginModule
import com.example.hunachi.githunaclient.data.api.oauth.OauthAdapter
import com.example.hunachi.githunaclient.presentation.MyApplication
import com.example.hunachi.githunaclient.presentation.dialog.LoadingDialog
import com.example.hunachi.githunaclient.presentation.main.MainActivity
import com.example.hunachi.githunaclient.presentation.login.LoginGithubActivity

/**
 * Created by hunachi on 2018/01/28.
 */
data class MainViewModules(
        /*MainActivityしかあり得ないことを想定*/
        val activity: MainActivity,
        val application: MyApplication,
        val containerId: Int = R.id.container //todo yokunai
)

data class LoginViewModules(
        val activity: LoginGithubActivity,
        val application: MyApplication,
        val oauthAdapter: OauthAdapter,
        val loadingDialog: LoadingDialog
)

data class OauthClientModules(
        val githubLoginModule: GithubLoginModule,
        val appSchedulerProvider: SchedulerProvider,
        val application: MyApplication,
        val callback: OauthAccessCallback,
        val loadingDialog: LoadingDialog
)
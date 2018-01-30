package com.example.hunachi.githunaclient.util

import com.example.hunachi.githunaclient.R
import com.example.hunachi.githunaclient.data.api.modules.GithubLoginModule
import com.example.hunachi.githunaclient.data.api.oauth.OauthAdapter
import com.example.hunachi.githunaclient.presentation.MyApplication
import com.example.hunachi.githunaclient.presentation.main.MainActivity
import com.example.hunachi.githunaclient.presentation.oauth.LoginGithubActivity
import com.example.hunachi.githunaclient.presentation.oauth.LoginGithubViewModel

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
        val oauthAdapter: OauthAdapter
)

data class OauthClientModules(
        val githubLoginModule: GithubLoginModule,
        val appSchedulerProvider: AppSchedulerProvider,
        val application: MyApplication
)
package com.example.hunachi.githunaclient.util


import android.support.v7.app.AppCompatActivity
import com.example.hunachi.githunaclient.data.api.modules.GithubLoginModule
import com.example.hunachi.githunaclient.data.api.oauth.OauthAdapter
import com.example.hunachi.githunaclient.presentation.MyApplication
import com.example.hunachi.githunaclient.presentation.dialog.LoadingDialog
import com.example.hunachi.githunaclient.presentation.helper.Navigator
import com.example.hunachi.githunaclient.presentation.main.MainActivity
import com.example.hunachi.githunaclient.presentation.login.LoginGithubActivity

/**
 * Created by hunachi on 2018/01/28.
 */
data class MainViewModels(
        val navigator: Navigator,
        val application: MyApplication
)

data class LoginViewModels(
        val navigator: Navigator,
        val application: MyApplication,
        val oauthAdapter: OauthAdapter,
        val loadingDialog: LoadingDialog
)

data class OauthClientModels(
        val githubLoginModule: GithubLoginModule,
        val appSchedulerProvider: SchedulerProvider,
        val application: MyApplication,
        val callback: OauthAccessCallback,
        val loadingDialog: LoadingDialog
)

data class NavigatorModels(
        val activity: AppCompatActivity,
        val mainActivity: MainActivity,
        val loginGithubActivity: LoginGithubActivity,
        val oauthAdapter: OauthAdapter
)
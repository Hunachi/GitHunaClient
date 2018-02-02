package com.example.hunachi.githunaclient.kodein

import com.example.hunachi.githunaclient.data.api.modules.GithubLoginClient
import com.example.hunachi.githunaclient.presentation.MyApplication
import com.example.hunachi.githunaclient.presentation.dialog.LoadingDialog
import com.example.hunachi.githunaclient.util.OauthAccessCallback
import com.example.hunachi.githunaclient.util.SchedulerProvider

/**
 * Created by hunachi on 2018/02/02.
 */
data class OauthClientModule(
        val githubLoginClient: GithubLoginClient,
        val appSchedulerProvider: SchedulerProvider,
        val application: MyApplication,
        val callback: OauthAccessCallback,
        val loadingDialog: LoadingDialog
)
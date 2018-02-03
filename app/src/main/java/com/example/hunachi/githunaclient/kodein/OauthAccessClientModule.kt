package com.example.hunachi.githunaclient.kodein

import com.example.hunachi.githunaclient.data.repository.GithubLoginClient
import com.example.hunachi.githunaclient.presentation.MyApplication
import com.example.hunachi.githunaclient.domain.dialog.LoadingDialog
import com.example.hunachi.githunaclient.util.OauthAccessCallback
import com.example.hunachi.githunaclient.util.rx.SchedulerProvider

/**
 * Created by hunachi on 2018/02/02.
 */
data class OauthAccessClientModule(
        val appSchedulerProvider: SchedulerProvider,
        val application: MyApplication,
        val callback: OauthAccessCallback,
        val loadingDialog: LoadingDialog
)
package com.example.hunachi.githunaclient.kodein

import com.example.hunachi.githunaclient.presentation.helper.OauthAdapter
import com.example.hunachi.githunaclient.presentation.MyApplication
import com.example.hunachi.githunaclient.domain.dialog.LoadingDialog
import com.example.hunachi.githunaclient.presentation.helper.Navigator

/**
 * Created by hunachi on 2018/02/02.
 */
data class LoginViewModelModule(
        val navigator: Navigator,
        val application: MyApplication,
        val oauthAdapter: OauthAdapter,
        val loadingDialog: LoadingDialog
)
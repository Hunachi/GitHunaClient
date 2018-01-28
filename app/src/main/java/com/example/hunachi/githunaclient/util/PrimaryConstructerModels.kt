package com.example.hunachi.githunaclient.util

import android.content.Context
import com.example.hunachi.githunaclient.R
import com.example.hunachi.githunaclient.presentation.base.BaseActivity
import com.example.hunachi.githunaclient.presentation.main.MainActivity
import com.example.hunachi.githunaclient.presentation.oauth.OauthAuthorizationFragment

/**
 * Created by hunachi on 2018/01/28.
 */
class MainViewModels(
        val activity: MainActivity,
        val authorizationFragment: OauthAuthorizationFragment,
        val containerId: Int = R.id.container
)
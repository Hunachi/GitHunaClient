package com.example.hunachi.githunaclient.util

import com.example.hunachi.githunaclient.R
import com.example.hunachi.githunaclient.data.OauthAdapter
import com.example.hunachi.githunaclient.presentation.MyApplication
import com.example.hunachi.githunaclient.presentation.main.MainActivity
import com.example.hunachi.githunaclient.presentation.oauth.LoginGithubActivity
import com.example.hunachi.githunaclient.presentation.oauth.OauthFragment

/**
 * Created by hunachi on 2018/01/28.
 */
data class MainViewModules(
        /*MainActivityしかあり得ないことを想定*/
        val activity: MainActivity,
        val application: MyApplication,
        val oauthFragment: OauthFragment,
        val containerId: Int = R.id.container
)

data class OauthFragmentModules(
        val oauthFragment: OauthFragment,
        val application: MyApplication,
        val oauthAdapter: OauthAdapter
)

data class LoginViewModules(
        val activity: LoginGithubActivity,
        val application: MyApplication,
        val oauthAdapter: OauthAdapter
)
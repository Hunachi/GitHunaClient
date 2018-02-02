package com.example.hunachi.githunaclient.kodein

import android.support.v7.app.AppCompatActivity
import com.example.hunachi.githunaclient.data.api.oauth.OauthAdapter
import com.example.hunachi.githunaclient.presentation.login.LoginGithubActivity
import com.example.hunachi.githunaclient.presentation.main.MainActivity

/**
 * Created by hunachi on 2018/02/02.
 */

data class NavigatorModule(
        val activity: AppCompatActivity,
        val mainActivity: MainActivity,
        val loginGithubActivity: LoginGithubActivity,
        val oauthAdapter: OauthAdapter
)
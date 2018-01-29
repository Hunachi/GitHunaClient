package com.example.hunachi.githunaclient.presentation.oauth

import android.content.Intent
import android.databinding.DataBindingUtil
import com.example.hunachi.githunaclient.R
import com.example.hunachi.githunaclient.data.OauthWebAdapter
import com.example.hunachi.githunaclient.databinding.ActivityLoginGitHubBinding
import com.example.hunachi.githunaclient.presentation.base.BaseActivity
import com.github.salomonbrys.kodein.instance

/**
 * Created by hunachi on 2018/01/30.
 */
class LoginGitHubActivity : BaseActivity() {
    
    private val oauthWebAdapter: OauthWebAdapter by instance()
    private val binding: ActivityLoginGitHubBinding by lazy {
        DataBindingUtil.setContentView<ActivityLoginGitHubBinding>(this, R.layout.activity_login_git_hub)
    }
    
    override fun onResume() {
        super.onResume()
        
    }
}
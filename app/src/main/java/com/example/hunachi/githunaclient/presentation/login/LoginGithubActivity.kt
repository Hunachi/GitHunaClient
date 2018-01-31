package com.example.hunachi.githunaclient.presentation.login

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.example.hunachi.githunaclient.R
import com.example.hunachi.githunaclient.data.api.oauth.OauthAccessClient
import com.example.hunachi.githunaclient.databinding.ActivityLoginGitHubBinding
import com.example.hunachi.githunaclient.presentation.base.BaseActivity
import com.example.hunachi.githunaclient.presentation.main.MainActivity
import com.example.hunachi.githunaclient.util.OauthAccessCallback
import com.example.hunachi.githunaclient.util.StatusModule
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.with

/**
 * Created by hunachi on 2018/01/30.
 */
class LoginGithubActivity : BaseActivity() {
    
    private val mainActivity: MainActivity by instance()
    private val viewModel: LoginGithubViewModel by with(this).instance()
    
    private val oauthAccessCallback: OauthAccessCallback = { status ->
        when (status) {
            StatusModule.SUCCESS -> startMainActivity()
            StatusModule.ERROR   -> {/*todo set ...dialog????*/}
        }
    }
    private val oauthAccessClient: OauthAccessClient by with(oauthAccessCallback).instance()
    
    private val binding: ActivityLoginGitHubBinding by lazy {
        DataBindingUtil.setContentView<ActivityLoginGitHubBinding>(this, R.layout.activity_login_git_hub)
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*if already have token this process skip.*/
        if (application.user.token.isNotBlank()) startMainActivity()
        binding.apply {
            viewModel = this@LoginGithubActivity.viewModel
            setLifecycleOwner(this@LoginGithubActivity)
        }
        setViewModel(viewModel)
    }
    
    private fun startMainActivity(){
        startActivity(Intent(this, mainActivity::class.java))
        finish()
    }
    
    override fun onResume() {
        super.onResume()
        oauthAccessClient.callbackToken(intent)
    }
    
    override fun onDestroy() {
        super.onDestroy()
    }
}
package com.example.hunachi.githunaclient.presentation.login

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.example.hunachi.githunaclient.R
import com.example.hunachi.githunaclient.data.repository.OauthAccessRepository
import com.example.hunachi.githunaclient.databinding.ActivityLoginGitHubBinding
import com.example.hunachi.githunaclient.presentation.base.BaseActivity
import com.example.hunachi.githunaclient.presentation.dialog.LoadingDialog
import com.example.hunachi.githunaclient.presentation.main.MainActivity
import com.example.hunachi.githunaclient.util.OauthAccessCallback
import com.example.hunachi.githunaclient.model.StatusModule
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.with

/**
 * Created by hunachi on 2018/01/30.
 */
class LoginGithubActivity : BaseActivity() {
    
    private val viewModel: LoginGithubViewModel by with(this).instance()
    private val loadingDialog: LoadingDialog by instance()
    
    private val oauthAccessCallback: OauthAccessCallback = {
        when (it) {
            StatusModule.SUCCESS -> finish()
            StatusModule.ERROR   -> {/*todo set ...dialog????*/
            }
        }
    }
    private val oauthAccessRepository: OauthAccessRepository by with(oauthAccessCallback).instance()
    
    private val binding: ActivityLoginGitHubBinding by lazy {
        DataBindingUtil.setContentView<ActivityLoginGitHubBinding>(this, R.layout.activity_login_git_hub)
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*if already have user this process skip.*/
        binding.viewModel = this@LoginGithubActivity.viewModel
        binding.setLifecycleOwner(this@LoginGithubActivity)
        setViewModel(viewModel)
    }
    
    override fun onResume() {
        super.onResume()
        oauthAccessRepository.callbackToken(intent)
    }
    
    override fun onDestroy() {
        super.onDestroy()
    }
}
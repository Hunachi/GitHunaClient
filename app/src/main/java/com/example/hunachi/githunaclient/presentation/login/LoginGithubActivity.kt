package com.example.hunachi.githunaclient.presentation.login

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.example.hunachi.githunaclient.R
import com.example.hunachi.githunaclient.data.repository.GithubTokenRepository
import com.example.hunachi.githunaclient.databinding.ActivityLoginGitHubBinding
import com.example.hunachi.githunaclient.presentation.base.BaseActivity
import com.example.hunachi.githunaclient.presentation.dialog.LoadingDialog
import com.example.hunachi.githunaclient.model.StatusModule
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.with

/**
 * Created by hunachi on 2018/01/30.
 */
class LoginGithubActivity : BaseActivity(), GithubTokenRepository.Callback {
    
    private val viewModel: LoginGithubViewModel by with(this).instance()
    private val loadingDialog: LoadingDialog by instance()
    private val githubTokenRepository: GithubTokenRepository
            by with(this as GithubTokenRepository.Callback).instance()
    
    private val binding: ActivityLoginGitHubBinding by lazy {
        DataBindingUtil.setContentView<ActivityLoginGitHubBinding>(
            this,
            R.layout.activity_login_git_hub
        )
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
        githubTokenRepository.callbackToken(intent)
    }
    
    override fun onDestroy() {
        super.onDestroy()
    }
    
    override fun tokenStatusCallback(status: StatusModule) {
        when (status) {
            StatusModule.SUCCESS -> finish()
            StatusModule.ERROR   -> {/*todo set ...dialog????*/ }
        }
    }
    
}
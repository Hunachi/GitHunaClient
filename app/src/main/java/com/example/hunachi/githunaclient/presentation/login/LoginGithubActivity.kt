package com.example.hunachi.githunaclient.presentation.login

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.example.hunachi.githunaclient.R
import com.example.hunachi.githunaclient.databinding.ActivityLoginGitHubBinding
import com.example.hunachi.githunaclient.kodein.loginViewModelModule
import com.example.hunachi.githunaclient.presentation.base.BaseActivity
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.android.appKodein
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.lazy
import com.github.salomonbrys.kodein.with

/**
 * Created by hunachi on 2018/01/30.
 */
class LoginGithubActivity : BaseActivity() {
    
    private val kodein = Kodein.lazy{
        extend(appKodein.invoke())
        import(loginViewModelModule)
    }
    private val viewModel: LoginGithubViewModel by kodein.with(this).instance()
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
}
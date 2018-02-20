package com.example.hunachi.githunaclient.presentation.login

import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AlertDialog
import com.example.hunachi.githunaclient.R
import com.example.hunachi.githunaclient.databinding.ActivityLoginGitHubBinding
import com.example.hunachi.githunaclient.presentation.base.BaseActivity
import com.example.hunachi.githunaclient.presentation.dialog.LoadingDialogAdapter
import com.example.hunachi.githunaclient.presentation.helper.Navigator
import com.example.hunachi.githunaclient.util.StatusSignal
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.with

/**
 * Created by hunachi on 2018/01/30.
 */
class LoginGithubActivity : BaseActivity() {
    
    private val loadingDialogAdapter: LoadingDialogAdapter by with(this as Context).instance()
    private lateinit var dialog: AlertDialog
    private val navigator: Navigator by with(this).instance()
    private val viewModel: LoginGithubViewModel by with(this).instance()
    private val binding: ActivityLoginGitHubBinding by lazy {
        DataBindingUtil.setContentView<ActivityLoginGitHubBinding>(this, R.layout.activity_login_git_hub)
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpViewModel()
        dialog = loadingDialogAdapter.onCreateDialog()
        binding.setLifecycleOwner(this@LoginGithubActivity)
    }
    
    private fun setUpViewModel() {
        binding.viewModel = this@LoginGithubActivity.viewModel
        viewModel.apply {
            codeProcessor.subscribe { status ->
                when (status) {
                    StatusSignal.SUCCESS -> dialog.show()
                    StatusSignal.ERROR   -> {}//TODO
                }
            }
            tokenProcessor.subscribe { status ->
                when (status) {
                    StatusSignal.SUCCESS -> {
                        if (dialog.isShowing) dialog.dismiss()
                        navigator.navigateToMain()
                    }
                    StatusSignal.ERROR   -> {}//TODO
                }
            }
        }
        setViewModel(viewModel)
    }
}
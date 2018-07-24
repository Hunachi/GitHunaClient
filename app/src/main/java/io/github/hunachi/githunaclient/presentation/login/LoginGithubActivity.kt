package io.github.hunachi.githunaclient.presentation.login

import android.arch.lifecycle.Observer
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AlertDialog
import io.github.hunachi.githunaclient.R
import io.github.hunachi.githunaclient.databinding.ActivityLoginGitHubBinding
import io.github.hunachi.githunaclient.presentation.base.BaseActivity
import io.github.hunachi.githunaclient.presentation.dialog.LoadingDialogAdapter
import io.github.hunachi.githunaclient.presentation.helper.Navigator
import io.github.hunachi.githunaclient.util.ErrorCallback
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
            code.observe(this@LoginGithubActivity, Observer { status ->
                when (status) {
                    SignalStatus.SUCCESS -> dialog.show()
                    SignalStatus.ERROR   -> onError()
                }
            })
            token.observe(this@LoginGithubActivity, Observer { status ->
                when (status) {
                    SignalStatus.SUCCESS -> {
                        if (dialog.isShowing) dialog.dismiss()
                        navigator.navigateToMain()
                    }
                    SignalStatus.ERROR   -> onError()
                }
            })
        }
        setViewModel(viewModel)
    }
    
    fun onError() {
        errorToast("failed to login")
    }
}

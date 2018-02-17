package com.example.hunachi.githunaclient.presentation.login

import android.support.v7.app.AlertDialog
import android.widget.Toast
import com.example.hunachi.githunaclient.data.repository.GithubTokenRepository
import com.example.hunachi.githunaclient.util.StatusModule
import com.example.hunachi.githunaclient.presentation.base.BaseViewModel
import com.example.hunachi.githunaclient.presentation.MyApplication
import com.example.hunachi.githunaclient.presentation.dialog.LoadingDialogAdapter
import com.example.hunachi.githunaclient.presentation.helper.Navigator
import com.example.hunachi.githunaclient.util.rx.AppSchedulerProvider
import com.github.salomonbrys.kodein.*

/**
 * Created by hunachi on 2018/01/30.
 */
class LoginGithubViewModel(
        private val navigator: Navigator,
        private val application: MyApplication,
        private val loadingDialogAdapter: LoadingDialogAdapter
) : BaseViewModel(), GithubTokenRepository.Callback {
    
    private val kodein = Kodein.lazy {
        bind<GithubTokenRepository>() with singleton {
            GithubTokenRepository(
                scheduler = AppSchedulerProvider(),
                application = application,
                callback = this@LoginGithubViewModel
            )
        }
    }
    
    private lateinit var dialog: AlertDialog //TODO move to view
    lateinit var githubTokenRepository: GithubTokenRepository
    
    override fun onCreate() {
        super.onCreate()
        githubTokenRepository = kodein.instance<GithubTokenRepository>().value
        dialog = loadingDialogAdapter.onCreateDialog()
    }
    
    fun onClickOauth() {
        navigator.navigateToOauth()
    }
    
    override fun onResume() {
        super.onResume()
        githubTokenRepository.callbackToken(navigator.activity.intent)
    }
    
    override fun codeStatusCallback(statusModule: StatusModule) {
        when(statusModule){
            StatusModule.SUCCESS -> dialog.show()
            StatusModule.ERROR   -> {
                Toast.makeText(application, "認証に失敗しました．/nもう一度試して見て下さい．", Toast.LENGTH_SHORT)
                        .show()
            }
        }
    }
    
    override fun tokenStatusCallback(statusModule: StatusModule) {
        when (statusModule) {
            StatusModule.SUCCESS -> {
                if(dialog.isShowing) dialog.dismiss()
                navigator.activityFinish()
            }
            StatusModule.ERROR   -> {/*todo show ...errorDialog????*/ }
        }
    }
}


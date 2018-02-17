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
import io.reactivex.processors.PublishProcessor

/**
 * Created by hunachi on 2018/01/30.
 */
class LoginGithubViewModel(
        private val navigator: Navigator,
        private val application: MyApplication
) : BaseViewModel(), GithubTokenRepository.Callback {
    
    val tokenProcessor: PublishProcessor<StatusModule> = PublishProcessor.create()
    val codeProcessor: PublishProcessor<StatusModule> = PublishProcessor.create()
    
    private val kodein = Kodein.lazy {
        bind<GithubTokenRepository>() with singleton {
            GithubTokenRepository(
                scheduler = AppSchedulerProvider(),
                application = application,
                callback = this@LoginGithubViewModel
            )
        }
    }
    
    lateinit var githubTokenRepository: GithubTokenRepository
    
    override fun onCreate() {
        super.onCreate()
        githubTokenRepository = kodein.instance<GithubTokenRepository>().value
    }
    
    fun onClickOauth() {
        navigator.navigateToOauth()
    }
    
    override fun onResume() {
        super.onResume()
        githubTokenRepository.callbackToken(navigator.activity.intent)
    }
    
    override fun codeStatusCallback(statusModule: StatusModule) {
        codeProcessor.onNext(statusModule)
    }
    
    override fun tokenStatusCallback(statusModule: StatusModule) {
        tokenProcessor.onNext(statusModule)
    }
}


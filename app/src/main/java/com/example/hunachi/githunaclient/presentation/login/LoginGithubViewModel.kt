package com.example.hunachi.githunaclient.presentation.login

import com.example.hunachi.githunaclient.data.repository.GithubTokenRepository
import com.example.hunachi.githunaclient.util.StatusSignal
import com.example.hunachi.githunaclient.presentation.base.BaseViewModel
import com.example.hunachi.githunaclient.presentation.MyApplication
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
    
    val tokenProcessor: PublishProcessor<StatusSignal> = PublishProcessor.create()
    val codeProcessor: PublishProcessor<StatusSignal> = PublishProcessor.create()
    
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
    
    override fun codeStatusCallback(statusSignal: StatusSignal) {
        codeProcessor.onNext(statusSignal)
    }
    
    override fun tokenStatusCallback(statusSignal: StatusSignal) {
        tokenProcessor.onNext(statusSignal)
    }
}


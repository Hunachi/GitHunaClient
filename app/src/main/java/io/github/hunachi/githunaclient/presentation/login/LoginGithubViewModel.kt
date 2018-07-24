package io.github.hunachi.githunaclient.presentation.login

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import io.github.hunachi.githunaclient.data.repository.GithubTokenRepository
import io.github.hunachi.githunaclient.presentation.base.BaseViewModel
import io.github.hunachi.githunaclient.presentation.MyApplication
import io.github.hunachi.githunaclient.presentation.helper.Navigator
import io.github.hunachi.githunaclient.util.rx.AppSchedulerProvider
import com.github.salomonbrys.kodein.*
import io.reactivex.processors.PublishProcessor

/**
 * Created by hunachi on 2018/01/30.
 */
class LoginGithubViewModel(
        private val navigator: Navigator,
        private val application: MyApplication
) : BaseViewModel(), GithubTokenRepository.Callback {
    
    private val tokenProcessor: PublishProcessor<SignalStatus> = PublishProcessor.create()
    private val codeProcessor: PublishProcessor<SignalStatus> = PublishProcessor.create()
    val token: LiveData<SignalStatus> = LiveDataReactiveStreams.fromPublisher(tokenProcessor)
    val code: LiveData<SignalStatus> = LiveDataReactiveStreams.fromPublisher(codeProcessor)
    
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
    
    override fun codeStatusCallback(signalStatus: SignalStatus) {
        codeProcessor.onNext(signalStatus)
    }
    
    override fun tokenStatusCallback(signalStatus: SignalStatus) {
        tokenProcessor.onNext(signalStatus)
    }
}


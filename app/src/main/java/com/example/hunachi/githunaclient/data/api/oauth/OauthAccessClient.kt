package com.example.hunachi.githunaclient.data.api.oauth

import android.content.Intent
import android.util.Log
import com.example.hunachi.githunaclient.data.api.modules.GithubLoginModule
import com.example.hunachi.githunaclient.util.*
import com.github.salomonbrys.kodein.*

/**
 * Created by hunachi on 2018/01/29.
 */
class OauthAccessClient(private val module: OauthClientModules) {
    
    private val loginClient = module.githubLoginModule
    private val scheduler = module.appSchedulerProvider
    private val application = module.application
    private val callback = module.callback
    
    fun callbackToken(intent: Intent) {
        if (intent.action == Intent.ACTION_VIEW) {
            val uri = intent.data
            uri?.apply {
                val code = getQueryParameter("code")
                val state = getQueryParameter("state")
                if (state == Key.state) accessToken(code)
                else throw Exception("Callbackのstateが異なりました．")
            }
        }
    }
    
    private fun accessToken(code: String) {
        //Log.d("コードを受け取ったよ！！", code) //OK
        loginClient.register(code).subscribeOn(scheduler.io()).observeOn(scheduler.ui()).subscribe({
            //Log.d("Tokenを取得した", it.toString())
            application.setUserToken(it.token)
            callback(StatusModule.SUCCESS)
        }, {
            it.printStackTrace()
            callback(StatusModule.ERROR)
        })
    }
}

val oauthAccessClientModule = Kodein.Module {
    bind<OauthAccessClient>() with factory { callback: OauthAccessCallback ->
        OauthAccessClient(OauthClientModules(
                githubLoginModule = instance(),
                appSchedulerProvider = instance(),
                application = instance(),
                callback = callback,
                loadingDialog = instance())
        )
    }
}
package com.example.hunachi.githunaclient.data.api.oauth

import android.content.Intent
import android.util.Log
import com.example.hunachi.githunaclient.data.api.modules.GithubLoginModule
import com.example.hunachi.githunaclient.util.AppSchedulerProvider
import com.example.hunachi.githunaclient.util.Key
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton

/**
 * Created by hunachi on 2018/01/29.
 */
class OauthAccessClient(githubLoginModule: GithubLoginModule, schedulerProvider: AppSchedulerProvider) {
    
    private val loginClient = githubLoginModule
    private val scheduler = schedulerProvider
    
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
    
    //todo add callback fot activity
    
    private fun accessToken(code: String) {
        Log.d("コードを受け取ったよ！！", code) //OK
        loginClient.register(code)
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe({
                    Log.d("Tokenを取得した", it.toString())
                },{
                    it.printStackTrace()
                })
    }
}

val oauthAccessClientModule = Kodein.Module {
    bind<OauthAccessClient>() with singleton { OauthAccessClient(instance(), instance()) }
}
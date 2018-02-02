package com.example.hunachi.githunaclient.presentation.helper

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import com.example.hunachi.githunaclient.kodein.NavigatorModule
import com.example.hunachi.githunaclient.util.Scopes
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.androidActivityScope

/**
 * Created by hunachi on 2018/02/01.
 */
class Navigator(private val module: NavigatorModule){
    
    private val activity = module.activity
    private val loginGithubActivity = module.loginGithubActivity
    private val mainActivity = module.mainActivity
    private val oauthAdapter = module.oauthAdapter
    
    fun navigateToLogin(){
        activity.startActivity(Intent(activity, loginGithubActivity::class.java))
    }
    
    fun navigateToMain(){
        activity.startActivity(Intent(activity, mainActivity::class.java))
    }
    
    fun navigateToOauth(){
        activity.startActivity(oauthAdapter.intent)
    }
    
    fun activityFinish(){
        activity.finish()
    }
}

val navigatorModule = Kodein.Module{
    bind<Navigator>() with scopedSingleton(androidActivityScope){
        Navigator(NavigatorModule(activity = it as AppCompatActivity, mainActivity = instance(), loginGithubActivity = instance(), oauthAdapter = with(instance() as Scopes).instance()))
    }
}
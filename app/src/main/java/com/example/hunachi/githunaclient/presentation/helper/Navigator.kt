package com.example.hunachi.githunaclient.presentation.helper

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import com.example.hunachi.githunaclient.util.NavigatorModules
import com.example.hunachi.githunaclient.util.Scopes
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.androidActivityScope

/**
 * Created by hunachi on 2018/02/01.
 */
class Navigator(private val modules: NavigatorModules){
    
    private val activity = modules.activity
    private val loginGithubActivity = modules.loginGithubActivity
    private val mainActivity = modules.mainActivity
    private val oauthAdapter = modules.oauthAdapter
    
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
        Navigator(NavigatorModules(
                activity = it as AppCompatActivity,
                mainActivity = instance(),
                loginGithubActivity = instance(),
                oauthAdapter = with(instance() as Scopes).instance()
        ))
    }
}
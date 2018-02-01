package com.example.hunachi.githunaclient.presentation.helper

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import com.example.hunachi.githunaclient.util.models.NavigatorModels
import com.example.hunachi.githunaclient.util.Scopes
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.androidActivityScope

/**
 * Created by hunachi on 2018/02/01.
 */
class Navigator(private val models: NavigatorModels){
    
    private val activity = models.activity
    private val loginGithubActivity = models.loginGithubActivity
    private val mainActivity = models.mainActivity
    private val oauthAdapter = models.oauthAdapter
    
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
        Navigator(NavigatorModels(activity = it as AppCompatActivity, mainActivity = instance(), loginGithubActivity = instance(), oauthAdapter = with(instance() as Scopes).instance()))
    }
}
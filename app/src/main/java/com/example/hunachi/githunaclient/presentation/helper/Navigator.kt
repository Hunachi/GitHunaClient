package com.example.hunachi.githunaclient.presentation.helper

import android.content.Intent
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import com.example.hunachi.githunaclient.R
import com.example.hunachi.githunaclient.presentation.base.BaseActivity
import com.example.hunachi.githunaclient.presentation.base.BaseFragment
import com.example.hunachi.githunaclient.presentation.login.LoginGithubActivity
import com.example.hunachi.githunaclient.presentation.main.MainActivity
import com.example.hunachi.githunaclient.util.Scopes
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.androidActivityScope

/**
 * Created by hunachi on 2018/02/01.
 */
class Navigator(
        val activity: BaseActivity,
        val mainActivity: MainActivity,
        val loginGithubActivity: LoginGithubActivity,
        val oauthAdapter: OauthAdapter
) {
    
    fun navigateToLogin() {
        activity.startActivity(Intent(activity, loginGithubActivity::class.java))
    }
    
    fun navigateToMain() {
        activity.startActivity(Intent(activity, mainActivity::class.java))
    }
    
    fun navigateToOauth() {
        activity.startActivity(oauthAdapter.intent)
    }
    
    fun replaceFragment(fragment: BaseFragment,@IdRes @LayoutRes resourceId: Int = R.id.container){
        activity.replaceFragment(fragment, resourceId)
    }
    
    fun activityFinish() {
        activity.finish()
    }
}

val navigatorModule = Kodein.Module {
    bind<Navigator>() with scopedSingleton(androidActivityScope) {
        Navigator(
            activity = it as BaseActivity,
            mainActivity = instance(),
            loginGithubActivity = instance(),
            oauthAdapter = with(instance() as Scopes).instance()
        )
    }
}
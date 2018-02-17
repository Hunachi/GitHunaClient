package com.example.hunachi.githunaclient.presentation.helper

import android.app.ActivityManager
import android.content.Intent
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import com.example.hunachi.githunaclient.R
import com.example.hunachi.githunaclient.data.repository.adapter.OauthAdapter
import com.example.hunachi.githunaclient.presentation.base.BaseActivity
import com.example.hunachi.githunaclient.presentation.base.BaseFragment
import com.example.hunachi.githunaclient.presentation.fragment.feeds.Feed
import com.example.hunachi.githunaclient.presentation.login.LoginGithubActivity
import com.example.hunachi.githunaclient.presentation.main.MainActivity
import com.example.hunachi.githunaclient.presentation.main.profile.MainProfileActivity

/**
 * Created by hunachi on 2018/02/01.
 */
class Navigator(
        val activity: BaseActivity,
        private val mainActivity: MainActivity,
        private val loginGithubActivity: LoginGithubActivity,
        private val mainProfileActivity: MainProfileActivity
) {
    
    fun navigateToLogin() {
        activity.startActivity(Intent(activity, loginGithubActivity::class.java))
        activity.finish()
    }
    
    fun navigateToMain() {
        activity.startActivity(Intent(activity, mainActivity::class.java))
        activityFinish()
    }
    
    fun navigateToOauth() {
        activity.startActivity(OauthAdapter.intent)
    }
    
    fun navigateToMainProfile(userName: String) {
        activity.startActivity(
            Intent(activity, mainProfileActivity::class.java)
                    .apply { putExtra("userName", userName) }
        )
        if (activity is MainProfileActivity) activityFinish()
    }
    
    fun replaceFragment(fragment: BaseFragment, @IdRes @LayoutRes resourceId: Int = R.id.container) {
        activity.replaceFragment(resourceId, fragment)
    }
    
    fun activityFinish() {
        activity.finish()
    }
}
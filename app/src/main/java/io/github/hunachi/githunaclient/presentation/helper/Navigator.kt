package io.github.hunachi.githunaclient.presentation.helper

import android.content.Intent
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import io.github.hunachi.githunaclient.R
import io.github.hunachi.githunaclient.data.repository.adapter.OauthAdapter
import io.github.hunachi.githunaclient.presentation.base.BaseActivity
import io.github.hunachi.githunaclient.presentation.base.BaseFragment
import io.github.hunachi.githunaclient.presentation.login.LoginGithubActivity
import io.github.hunachi.githunaclient.presentation.main.FRAGMENT_FRAG_NAME
import io.github.hunachi.githunaclient.presentation.main.FragmentFrag
import io.github.hunachi.githunaclient.presentation.main.MainActivity
import io.github.hunachi.githunaclient.presentation.main.profile.MainProfileActivity

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
    
    fun navigateToMain(flag: FragmentFrag) {
        activity.startActivity(Intent(activity, mainActivity::class.java).apply {
            putExtra(FRAGMENT_FRAG_NAME, flag)
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        })
        activityFinish()
    }
    
    fun navigateToMain() {
        activity.startActivity(Intent(activity, mainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        })
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
       // if (activity is MainProfileActivity) activityFinish()
    }
    
    fun replaceFragment(@IdRes @LayoutRes resourceId: Int, fragment: BaseFragment) {
        activity.supportFragmentManager
                .beginTransaction()
                .replace(resourceId, fragment)
                .commit()
    }
    
    fun activityFinish() {
        activity.finish()
    }
}

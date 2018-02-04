package com.example.hunachi.githunaclient.presentation

import android.content.Context
import com.example.hunachi.githunaclient.data.repository.githubTokenModule
import com.example.hunachi.githunaclient.presentation.dialog.LoadingDialog
import com.example.hunachi.githunaclient.presentation.event.UserInfoFragment
import com.example.hunachi.githunaclient.presentation.event.userInfoViewModelModule
import com.example.hunachi.githunaclient.presentation.helper.navigatorModule
import com.example.hunachi.githunaclient.presentation.login.LoginGithubActivity
import com.example.hunachi.githunaclient.presentation.main.MainActivity
import com.example.hunachi.githunaclient.presentation.main.mainViewModelModule
import com.example.hunachi.githunaclient.presentation.login.loginViewModels
import com.example.hunachi.githunaclient.util.rx.AppSchedulerProvider
import com.example.hunachi.githunaclient.util.rx.SchedulerProvider
import com.github.salomonbrys.kodein.*

/**
 * Created by hunachi on 2018/01/27.
 */
class MainApplication : MyApplication(), KodeinAware {
    
    override val kodein by Kodein.lazy {
        bind<MyApplication>() with singleton { this@MainApplication as MyApplication }
        bind<LoadingDialog>() with singleton { LoadingDialog(this@MainApplication) }
        import(mainViewModelModule)
        import(loginViewModels)
        import(githubTokenModule)
        import(navigatorModule)
        import(userInfoViewModelModule)
        bind<MainActivity>() with singleton { MainActivity() }
        bind<LoginGithubActivity>() with singleton { LoginGithubActivity() }
        bind<SchedulerProvider>() with singleton { AppSchedulerProvider() }
        bind<UserInfoFragment>() with singleton { UserInfoFragment() }
    }
    
    companion object {
        const val userToken = "user_token"
    }
    
    private val preferences by lazy {
        getSharedPreferences("$packageName.txt", Context.MODE_PRIVATE)
    }
    
    override fun onCreate() {
        super.onCreate()
        token = preferences.getString(userToken, "")
    }
    
    override fun setUserToken(token: String) {
        super.setUserToken(token)
        preferences.edit().putString(userToken, token).commit()
    }
    
    override fun deleteUserToken() {
        super.deleteUserToken()
        preferences.edit().remove(userToken).commit()
    }
}

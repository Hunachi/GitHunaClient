package com.example.hunachi.githunaclient.presentation

import android.app.Application
import android.content.Context
import com.example.hunachi.githunaclient.data.repository.GithubLoginClient
import com.example.hunachi.githunaclient.presentation.helper.OauthAdapter
import com.example.hunachi.githunaclient.domain.User
import com.example.hunachi.githunaclient.presentation.login.oauthAccessClientModule
import com.example.hunachi.githunaclient.domain.dialog.LoadingDialog
import com.example.hunachi.githunaclient.presentation.event.UserInfoFragment
import com.example.hunachi.githunaclient.presentation.event.userInfoViewModelModule
import com.example.hunachi.githunaclient.presentation.helper.navigatorModule
import com.example.hunachi.githunaclient.presentation.login.LoginGithubActivity
import com.example.hunachi.githunaclient.presentation.main.MainActivity
import com.example.hunachi.githunaclient.presentation.main.mainViewModelModule
import com.example.hunachi.githunaclient.presentation.login.loginViewModels
import com.example.hunachi.githunaclient.util.rx.AppSchedulerProvider
import com.example.hunachi.githunaclient.util.rx.SchedulerProvider
import com.example.hunachi.githunaclient.util.Scopes
import com.github.salomonbrys.kodein.*
import java.io.Serializable

/**
 * Created by hunachi on 2018/01/27.
 */
class MyApplication : Application(), KodeinAware {
    
    override val kodein by Kodein.lazy {
        bind<MyApplication>() with singleton { this@MyApplication }
        /*if you want to change scope, please change item at below mutableList.*/
        bind<Scopes>() with instance(mutableListOf("repo"))
        bind<LoadingDialog>() with singleton { LoadingDialog(this@MyApplication) }
        import(mainViewModelModule)
        import(loginViewModels)
        import(oauthAccessClientModule)
        import(navigatorModule)
        import(userInfoViewModelModule)
        bind<User>() with singleton { User() }
        bind<MainActivity>() with singleton { MainActivity() }
        bind<LoginGithubActivity>() with singleton { LoginGithubActivity() }
        bind<OauthAdapter>() with factory { scopes: Scopes -> OauthAdapter(scopes = scopes) }
        //bind<GithubLoginClient>() with singleton { GithubLoginClient }
        bind<SchedulerProvider>() with singleton { AppSchedulerProvider() }
        bind<UserInfoFragment>() with singleton { UserInfoFragment() }
    }
    
    companion object {
        const val userToken = "user_token"
    }
    
    var user: User = kodein.instance()
        private set
    
    private val preferences by lazy {
        getSharedPreferences("$packageName.txt", Context.MODE_PRIVATE)
    }
    
    override fun onCreate() {
        super.onCreate()
        user = User(token = preferences.getString(userToken, ""))
    }
    
    fun setUserToken(token: String) {
        user.token = token
        preferences.edit().putString(userToken, token).commit()
    }
    
    fun deleteUserToken() {
        user = User()
        preferences.edit().remove(userToken).commit()
    }
}

data class User(
        var token: String = "",
        var userName: String = ""
): Serializable

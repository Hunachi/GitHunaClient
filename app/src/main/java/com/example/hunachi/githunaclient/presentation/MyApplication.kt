package com.example.hunachi.githunaclient.presentation

import android.app.Application
import android.content.Context
import com.example.hunachi.githunaclient.data.OauthAdapter
import com.example.hunachi.githunaclient.presentation.main.MainActivity
import com.example.hunachi.githunaclient.presentation.main.mainViewModelModule
import com.example.hunachi.githunaclient.presentation.oauth.loginViewModels
import com.example.hunachi.githunaclient.util.Scopes
import com.example.hunachi.githunaclient.util.User
import com.github.salomonbrys.kodein.*

/**
 * Created by hunachi on 2018/01/27.
 */
class MyApplication : Application(), KodeinAware {
    
    override val kodein by Kodein.lazy {
        bind<MyApplication>() with singleton { this@MyApplication }
        bind<Scopes>() with instance(mutableListOf("repo"))
        import(mainViewModelModule)
        import(loginViewModels)
        bind<MainActivity>() with singleton { MainActivity() }
        bind<OauthAdapter>() with factory { scopes: Scopes -> OauthAdapter(scopes = scopes) }
    }
    
    companion object {
        const val userToken = "user_token"
    }
    
    var user: User = User()
    private val preferences by lazy {
        getSharedPreferences("$packageName.txt", Context.MODE_PRIVATE)
    }
    
    override fun onCreate() {
        super.onCreate()
        user = User(token = preferences.getString(userToken, ""))
    }
    
    fun deleteUserToken(){
        user = User()
        preferences.edit().remove(userToken).commit()
    }
}
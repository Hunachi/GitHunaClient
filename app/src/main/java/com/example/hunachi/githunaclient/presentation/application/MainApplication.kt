package com.example.hunachi.githunaclient.presentation.application

import android.content.Context
import androidx.content.edit
import com.github.salomonbrys.kodein.*

/**
 * Created by hunachi on 2018/01/27.
 */
class MainApplication : MyApplication(), KodeinAware {
    
    override val kodein: Kodein by Kodein.lazy {
        bind<MyApplication>() with singleton {this@MainApplication as MyApplication }
        extend(kodeinModules)
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
        preferences.edit {
            putString(userToken, token)
        }
    }
    
    override fun deleteUserToken() {
        super.deleteUserToken()
        preferences.edit {
            remove(userToken)
        }
    }
}
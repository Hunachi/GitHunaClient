package com.example.hunachi.githunaclient.presentation

import android.content.Context
import androidx.content.edit
import com.example.hunachi.githunaclient.kodein.kodeinModules
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
        const val KEY_TOKEN = "user_token"
        const val KEY_USERNAME = "user_name"
    }
    
    private val preferences by lazy {
        getSharedPreferences("$packageName.txt", Context.MODE_PRIVATE)
    }
    
    override fun onCreate() {
        super.onCreate()
        token = preferences.getString(KEY_TOKEN, "")
        userName = preferences.getString(KEY_USERNAME, "")
    }
    
    override fun updateToken(token: String) {
        super.updateToken(token)
        preferences.edit {
            putString(KEY_TOKEN, token)
        }
    }
    
    override fun updateUserName(userName: String) {
        super.updateUserName(userName)
        preferences.edit{
            putString(KEY_USERNAME, userName)
        }
    }
    
    override fun deleteUserToken() {
        super.deleteUserToken()
        preferences.edit {
            remove(KEY_TOKEN)
            remove(KEY_USERNAME)
        }
    }
}
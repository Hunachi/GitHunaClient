package com.example.hunachi.githunaclient.presentation

import android.app.Application

/**
 * Created by hunachi on 2018/02/03.
 */
open class MyApplication: Application() {
    
    var token = ""
    protected set
    
    open fun setUserToken(token: String) {
        this.token = token
    }
    
    open fun deleteUserToken() {
        token = ""
    }
    
}
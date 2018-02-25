package com.example.hunachi.githunaclient.presentation

import android.app.Application

/**
 * Created by hunachi on 2018/02/03.
 */
open class MyApplication: Application() {
    
    var token = ""
    protected set
    
    var userName = ""
    protected set
    
    open fun updateToken(token: String) {
        this.token = token
    }
    
    open fun updateUserName(userName: String){
        this.userName = userName
    }
    
    open fun deleteUserToken() {
        token = ""
    }
    
}
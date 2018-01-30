package com.example.hunachi.githunaclient.data

import android.content.Intent
import android.util.Log
import com.example.hunachi.githunaclient.util.Key
import java.io.IOException

/**
 * Created by hunachi on 2018/01/29.
 */
class OauthAccessClient {
    
    fun callbackToken(intent: Intent){
        if(intent.action == Intent.ACTION_VIEW){
            val uri = intent.data
            uri?.apply {
                val code = getQueryParameter("code")
                val state = getQueryParameter("state")
                if(state == Key.state)accessToken(code)
                else throw Exception("Callbackのstateが異なりました．")
            }
        }
    }
    
    private fun accessToken(code: String){
        Log.d("コードを受け取ったよ！！",code) //OK
        //todo
    }
}
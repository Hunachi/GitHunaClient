package com.example.hunachi.githunaclient.data

import android.content.Intent
import com.example.hunachi.githunaclient.util.Key
import java.io.IOException

/**
 * Created by hunachi on 2018/01/29.
 */
class OauthAccesClient {
    
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
        //todo
    }
}
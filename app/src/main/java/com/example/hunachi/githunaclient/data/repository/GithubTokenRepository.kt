package com.example.hunachi.githunaclient.data.repository

import android.content.Intent
import android.telecom.Call
import android.util.Log
import com.example.hunachi.githunaclient.data.repository.adapter.GithubLoginAdapter
import com.example.hunachi.githunaclient.model.Key
import com.example.hunachi.githunaclient.model.StatusModule
import com.example.hunachi.githunaclient.presentation.MainApplication
import com.example.hunachi.githunaclient.util.*
import com.example.hunachi.githunaclient.presentation.MyApplication
import com.example.hunachi.githunaclient.util.rx.SchedulerProvider
import com.github.salomonbrys.kodein.*

/**
 * Created by hunachi on 2018/01/29.
 */
class GithubTokenRepository(
        val scheduler: SchedulerProvider,
        val application: MyApplication,
        val callback: Callback
) {
    
    fun callbackToken(intent: Intent) {
        if (intent.action == Intent.ACTION_VIEW) {
            val uri = intent.data
            uri?.apply {
                val code = getQueryParameter("code")
                val state = getQueryParameter("state")
                if (state == Key.state && code.isNotBlank()){
                    reciveToken(code)
                    callback.codeStatusCallback(StatusModule.SUCCESS)
                }
                else throw Exception("Callbackのstateが異なりました．")
            }
        }
    }
    
    private fun reciveToken(code: String){
        //Log.d("コードを受け取ったよ！！", code) //OK
        GithubLoginAdapter.register(code)
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe({
                    //Log.d("Tokenを取得した", it.toString()) //OK
                    application.setUserToken(it.token)
                    callback.tokenStatusCallback(StatusModule.SUCCESS)
                }, {
                    it.printStackTrace()
                    callback.tokenStatusCallback(StatusModule.ERROR)
                })
    }
    
    interface Callback{
        
        fun codeStatusCallback(statusModule: StatusModule)
        
        fun tokenStatusCallback(statusModule: StatusModule)
        
    }
    
}
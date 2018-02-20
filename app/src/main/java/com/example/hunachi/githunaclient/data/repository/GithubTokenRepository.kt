package com.example.hunachi.githunaclient.data.repository

import android.content.Intent
import com.example.hunachi.githunaclient.data.repository.adapter.GithubLoginAdapter
import com.example.hunachi.githunaclient.util.StatusSignal
import com.example.hunachi.githunaclient.presentation.application.MyApplication
import com.example.hunachi.githunaclient.util.Key
import com.example.hunachi.githunaclient.util.rx.SchedulerProvider

/**
 * Created by hunachi on 2018/01/29.
 */
/*TODO これuseCaseでは？*/
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
                    callback.codeStatusCallback(StatusSignal.SUCCESS)
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
                    application.setUserToken(it.token)
                    callback.tokenStatusCallback(StatusSignal.SUCCESS)
                }, {
                    it.printStackTrace()
                    callback.tokenStatusCallback(StatusSignal.ERROR)
                })
    }
    
    interface Callback{
        
        fun codeStatusCallback(statusSignal: StatusSignal)
        
        fun tokenStatusCallback(statusSignal: StatusSignal)
        
    }
    
}
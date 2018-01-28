package com.example.hunachi.githunaclient.presentation.base

import android.databinding.BaseObservable
import com.example.hunachi.githunaclient.presentation.MyApplication
import com.github.salomonbrys.kodein.instance

/**
 * Created by hunachi on 2018/01/27.
 */
abstract class BaseViewModel : BaseObservable(){
    
    protected lateinit var context: BaseActivity
    /*if you call them(下のインスタンスs) when context is uninitialized,you're exploding.*/
    
    //todo change kodein
    fun create(context: BaseActivity) {
        this.context = context
        onCreate()
    }
    
    open fun onCreate(){}
    
    open fun onStart() {}
    
    open fun onDestroy() {}
}
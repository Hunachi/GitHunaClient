package com.example.hunachi.githunaclient.presentation.base

import android.content.Context
import android.databinding.BaseObservable
import com.example.hunachi.githunaclient.presentation.MyApplication
import com.github.salomonbrys.kodein.instance

/**
 * Created by hunachi on 2018/01/27.
 */
abstract class BaseViewModel(protected val context: Context) : BaseObservable(){
    
    //protected lateinit var context: BaseActivity
    /*if you call them(下のインスタンスs) when context is uninitialized,you're exploding.*/
    /*
    //todo change kodein
    fun create(context: BaseActivity) {
        onCreate()
    }*/
    
    open fun onCreate(){}
    
    open fun onStart() {}
    
    open fun onDestroy() {}
}
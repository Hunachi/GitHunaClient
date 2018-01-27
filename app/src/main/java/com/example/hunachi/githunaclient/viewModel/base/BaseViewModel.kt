package com.example.hunachi.githunaclient.viewModel.base

import android.databinding.BaseObservable
import com.example.hunachi.githunaclient.MyApplication
import com.example.hunachi.githunaclient.view.base.BaseActivity
import com.github.salomonbrys.kodein.KodeinInjected
import com.github.salomonbrys.kodein.KodeinInjector
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.kodein

/**
 * Created by hunachi on 2018/01/27.
 */
abstract class BaseViewModel : BaseObservable(){
    
    private lateinit var context: BaseActivity
    /*if you call them(下のインスタンスs) when context is uninitialized,you're exploding.*/
    private val application: MyApplication by context.instance()
    
    fun create(context: BaseActivity) {
        this.context = context
        onCreate()
    }
    
    open fun onCreate(){}
    
    open fun onStart() {}
    
    open fun onDestroy() {}
}
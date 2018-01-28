package com.example.hunachi.githunaclient.presentation.base

import android.content.Context
import android.databinding.BaseObservable
import com.example.hunachi.githunaclient.presentation.MyApplication
import com.github.salomonbrys.kodein.instance

/**
 * Created by hunachi on 2018/01/27.
 */
abstract class BaseViewModel(protected val context: Context) : BaseObservable(){
    
    /*多分役立つのでActivity LifeCycleに同期させた🍣*/
    open fun onCreate(){}
    
    open fun onRestart(){}
    
    open fun onStart() {}
    
    open fun onResume() {}
    
    open fun onPause() {}
    
    open fun onStop(){}
    
    open fun onDestroy() {}
    
}
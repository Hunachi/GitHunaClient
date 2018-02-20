package com.example.hunachi.githunaclient.presentation.base

import android.databinding.BaseObservable

/**
 * Created by hunachi on 2018/01/27.
 */
abstract class BaseViewModel: BaseObservable() { //TODO
    
    /*多分役立つのでActivity LifeCycleに同期させた🍣他にいい方法があるのかもしれない*/
    open fun onCreate(){}
    
    open fun onRestart(){}
    
    open fun onStart() {}
    
    open fun onResume() {}
    
    open fun onPause() {}
    
    open fun onStop(){}
    
    open fun onDestroy() {}
    
}
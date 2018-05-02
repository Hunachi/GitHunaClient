package com.hunachi.hunachi.githunaclient.presentation.base

/**
 * Created by hunachi on 2018/01/27.
 */
abstract class BaseViewModel {
    /*多分役立つのでActivity LifeCycleに同期させた🍣*/
    open fun onCreate(){}
    
    open fun onRestart(){}
    
    open fun onStart() {}
    
    open fun onResume() {}
    
    open fun onPause() {}
    
    open fun onStop(){}
    
    open fun onDestroy() {}
    
}
package com.example.hunachi.githunaclient.presentation.base

import com.example.hunachi.githunaclient.presentation.MyApplication

/**
 * Created by hunachi on 2018/01/29.
 */
abstract class BaseFragmentViewModel(application: MyApplication) : BaseViewModel(application) {
    
    open fun onCreateView() {}
    
    open fun onActivityCreated() {}
    
    open fun onDetach() {}

}
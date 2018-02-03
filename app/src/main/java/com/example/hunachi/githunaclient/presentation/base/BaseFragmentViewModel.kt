package com.example.hunachi.githunaclient.presentation.base

import android.content.Context

/**
 * Created by hunachi on 2018/01/29.
 */
abstract class BaseFragmentViewModel(protected val fContext: Context) : BaseViewModel(fContext) {
    
    open fun onCreateView() {}
    
    open fun onActivityCreated() {}
    
    open fun onDetach() {}

}
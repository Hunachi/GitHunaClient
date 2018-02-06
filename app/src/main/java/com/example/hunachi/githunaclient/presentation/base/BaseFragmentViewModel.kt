package com.example.hunachi.githunaclient.presentation.base

import android.content.Context
import com.example.hunachi.githunaclient.presentation.MyApplication

/**
 * Created by hunachi on 2018/01/29.
 */
abstract class BaseFragmentViewModel(protected val fContext: MyApplication) : BaseViewModel(fContext) {
    
    open fun onCreateView() {}
    
    open fun onActivityCreated() {}
    
    open fun onDetach() {}

}
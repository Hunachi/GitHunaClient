package com.hunachi.hunachi.githunaclient.presentation.base

/**
 * Created by hunachi on 2018/01/29.
 */
abstract class BaseFragmentViewModel: BaseViewModel() {
    
    open fun onCreateView() {}
    
    open fun onActivityCreated() {}
    
    open fun onDetach() {}

}
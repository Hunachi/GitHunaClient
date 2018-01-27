package com.example.hunachi.githunaclient.view.base

import android.content.Intent
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import com.example.hunachi.githunaclient.MyApplication
import com.example.hunachi.githunaclient.viewModel.base.BaseViewModel
import com.github.salomonbrys.kodein.KodeinInjector
import com.github.salomonbrys.kodein.android.AppCompatActivityInjector
import com.github.salomonbrys.kodein.instance

/**
 * Created by hunachi on 2018/01/27.
 */
abstract class BaseActivity: AppCompatActivity(), AppCompatActivityInjector{
    
    final override val injector: KodeinInjector = KodeinInjector()
    
    private lateinit var viewModel: BaseViewModel
    
    open fun setViewModel(viewModel: BaseViewModel, context: BaseActivity){
        this.viewModel = viewModel
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeInjector()
    }
    
    override fun onStart() {
        super.onStart()
        viewModel.onStart()
    }
    
    override fun onDestroy() {
        super.onDestroy()
        //TODO destroy other provider too.
        viewModel.onDestroy()
        destroyInjector()
    }
}
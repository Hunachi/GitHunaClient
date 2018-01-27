package com.example.hunachi.githunaclient.presentation.base

import android.annotation.SuppressLint
import android.os.Bundle
import com.example.hunachi.githunaclient.presentation.base.BaseViewModel
import com.github.salomonbrys.kodein.android.KodeinAppCompatActivity

/**
 * Created by hunachi on 2018/01/27.
 */
abstract class BaseActivity: KodeinAppCompatActivity(){
    
    private lateinit var viewModel: BaseViewModel
    
    open fun setViewModel(viewModel: BaseViewModel){
        this.viewModel = viewModel
        viewModel.create(this)
    }
    
    @SuppressLint("MissingSuperCall")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    
    override fun onStart() {
        super.onStart()
        viewModel.onStart()
    }
    
    @SuppressLint("MissingSuperCall")
    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }
    
    fun replaceFragment(){
    }
}
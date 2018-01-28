package com.example.hunachi.githunaclient.presentation.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import com.github.salomonbrys.kodein.android.KodeinAppCompatActivity

/**
 * Created by hunachi on 2018/01/27.
 */
abstract class BaseActivity : KodeinAppCompatActivity() {
    
    private lateinit var viewModel: BaseViewModel
    
    open fun setViewModel(viewModel: BaseViewModel) {
        this.viewModel = viewModel
        viewModel.onCreate()
    }
    
    @SuppressLint("MissingSuperCall")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    
    override fun onRestart() {
        super.onRestart()
    }
    
    override fun onStart() {
        super.onStart()
        viewModel.onStart()
    }
    
    override fun onResume() {
        super.onResume()
    }
    
    override fun onPause() {
        super.onPause()
    }
    
    override fun onStop() {
        super.onStop()
    }
    
    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }
    
    protected fun replaceFragment(fragment: BaseFragment, @LayoutRes @IdRes resourceId: Int) {
        supportFragmentManager
                .beginTransaction()
                .replace(resourceId, fragment)
                .commit()
    }
}
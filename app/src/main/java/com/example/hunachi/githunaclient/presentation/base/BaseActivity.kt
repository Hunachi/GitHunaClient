package com.example.hunachi.githunaclient.presentation.base

import android.annotation.SuppressLint
import android.content.Intent
import android.hardware.Camera
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.util.Log
import android.widget.Toast
import com.example.hunachi.githunaclient.presentation.helper.Navigator
import com.example.hunachi.githunaclient.util.ErrorCallback
import com.github.salomonbrys.kodein.android.KodeinAppCompatActivity
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.with

/**
 * Created by hunachi on 2018/01/27.
 */
abstract class BaseActivity : KodeinAppCompatActivity() {
    
    private var viewModel: BaseViewModel? = null
    
    /*VMとActivityを同期させるなら必須*/
    protected open fun setViewModel(viewModel: BaseViewModel) {
        this.viewModel = viewModel
        viewModel.onCreate()
    }
    
    @SuppressLint("MissingSuperCall")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("create" ,"寿司")
    }
    
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
    }
    
    override fun onRestart() {
        super.onRestart()
        viewModel?.onRestart()
    }
    
    override fun onStart() {
        super.onStart()
        viewModel?.onStart()
    }
    
    override fun onResume() {
        super.onResume()
        viewModel?.onResume()
    }
    
    override fun onPause() {
        super.onPause()
        Log.d("pause" ,"寿司")
        viewModel?.onPause()
    }
    
    override fun onStop() {
        super.onStop()
        viewModel?.onStop()
        Log.d("stop" ,"寿司")
    }
    
    @SuppressLint("MissingSuperCall")
    override fun onDestroy() {
        super.onDestroy()
        viewModel?.onDestroy()
        Log.d("destroy" ,"寿司")
    }
    
    fun errorToast(){
        errorToast("failed to receive the data")
    }
    
    fun errorToast(text: String){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}
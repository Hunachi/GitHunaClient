package com.example.hunachi.githunaclient.view

import android.os.Bundle
import com.example.hunachi.githunaclient.R
import com.example.hunachi.githunaclient.view.base.BaseActivity
import com.example.hunachi.githunaclient.viewModel.MainViewModel
import com.github.salomonbrys.kodein.android.appKodein
import com.github.salomonbrys.kodein.instance

class MainActivity : BaseActivity() {
    
    private val viewModel: MainViewModel by instance()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setViewModel(viewModel)
    }
}

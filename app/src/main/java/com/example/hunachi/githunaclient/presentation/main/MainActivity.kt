package com.example.hunachi.githunaclient.presentation.main

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.example.hunachi.githunaclient.R
import com.example.hunachi.githunaclient.databinding.ActivityMainBinding
import com.example.hunachi.githunaclient.presentation.base.BaseActivity
import com.example.hunachi.githunaclient.presentation.base.BaseFragment
import com.example.hunachi.githunaclient.presentation.oauth.OauthAuthorizationFragment
import com.github.salomonbrys.kodein.instance

class MainActivity : BaseActivity() {
    
    private val viewModel: MainViewModel by instance()
    private val oauthAuthorizationFragment: OauthAuthorizationFragment by instance()
    private lateinit var binding: ActivityMainBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
        setViewModel(viewModel)
        
        replaceFragment(oauthAuthorizationFragment, R.id.container)
    }
}

package com.example.hunachi.githunaclient.presentation.main

import android.os.Bundle
import com.example.hunachi.githunaclient.R
import com.example.hunachi.githunaclient.presentation.base.BaseActivity
import com.example.hunachi.githunaclient.presentation.base.BaseFragment
import com.example.hunachi.githunaclient.presentation.oauth.OauthAuthorizationFragment
import com.github.salomonbrys.kodein.instance

class MainActivity : BaseActivity() {
    
    private val viewModel: MainViewModel by instance()
    private val oauthAuthorizationFragment: OauthAuthorizationFragment by instance()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setViewModel(viewModel)
        replaceFragment(oauthAuthorizationFragment, R.id.container)
    }
}

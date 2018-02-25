package com.example.hunachi.githunaclient.presentation.main

import android.arch.lifecycle.Observer
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.example.hunachi.githunaclient.R
import com.example.hunachi.githunaclient.databinding.ActivityMainBinding
import com.example.hunachi.githunaclient.presentation.MyApplication
import com.example.hunachi.githunaclient.presentation.base.BaseActivity
import com.example.hunachi.githunaclient.presentation.fragment.viewpager.ViewPagerFragment
import com.example.hunachi.githunaclient.presentation.fragment.userinfo.UserInfoFragment
import com.example.hunachi.githunaclient.presentation.helper.Navigator
import com.example.hunachi.githunaclient.util.Key
import com.example.hunachi.githunaclient.util.NavigatorCallback
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.with

class MainActivity : BaseActivity() {
    
    private val viewModel: MainViewModel by with(this).instance()
    private val navigator: Navigator by with(this).instance()
    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }
    private val myApplication: MyApplication by lazy { application as MyApplication }
    private lateinit var ownerInfoFragment: UserInfoFragment
    private lateinit var viewPagerFragment: ViewPagerFragment
    private var userName: String? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userName = myApplication.userName
        /*if token don't have, let's go to login page.*/
        if (checkToken()) setupViewModel() else navigator.navigateToLogin()
    }
    
    private fun checkToken(): Boolean = myApplication.token.isNotBlank()
    
    private fun setupViewModel() {
        binding.viewModel = viewModel.apply {
            user.observe(this@MainActivity, Observer { user ->
                user?.userName?.let {
                    userName = it
                    myApplication.updateUserName(it)
                    setupFragmentManager()
                }
            })
        }
        binding.setLifecycleOwner(this)
        setViewModel(viewModel)
        /*if userName is uninitialized, let's get user info.*/
        if (userName.isNullOrBlank()) viewModel.setupUser() else setupFragmentManager()
    }
    
    private fun setupFragmentManager() {
        viewPagerFragment = with(userName).instance<ViewPagerFragment>().value
        ownerInfoFragment = with(userName).instance<UserInfoFragment>().value
        binding.navigation.selectedItemId = R.id.action_lists
        navigator.replaceFragment(R.id.container, viewPagerFragment)
        viewModel.initCallback(callback)
    }
    
    private val callback: NavigatorCallback = { item ->
        when (item.itemId) {
            R.id.action_profile -> navigator.replaceFragment(R.id.container, ownerInfoFragment)
            R.id.action_lists   -> navigator.replaceFragment(R.id.container, viewPagerFragment)
        }
    }
}
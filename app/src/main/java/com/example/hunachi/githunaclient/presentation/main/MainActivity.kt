package com.example.hunachi.githunaclient.presentation.main

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.example.hunachi.githunaclient.R
import com.example.hunachi.githunaclient.databinding.ActivityMainBinding
import com.example.hunachi.githunaclient.presentation.application.MyApplication
import com.example.hunachi.githunaclient.presentation.base.BaseActivity
import com.example.hunachi.githunaclient.presentation.fragment.viewpager.ViewPagerFragment
import com.example.hunachi.githunaclient.presentation.fragment.userinfo.UserInfoFragment
import com.example.hunachi.githunaclient.presentation.helper.Navigator
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.with

class MainActivity : BaseActivity() {
    
    private val viewModel: MainViewModel by with(this).instance()
    private val navigator: Navigator by with(this).instance()
    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }
    private val myApplication: MyApplication by lazy { application as MyApplication }
    private lateinit var userInfoFragment: UserInfoFragment
    private lateinit var viewPagerFragment: ViewPagerFragment
    private var userName: String = ""
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userName = myApplication.userName
        if (checkToken()) setupViewModel()
        else navigator.navigateToLogin()
    }
    
    private fun checkToken(): Boolean = myApplication.token.isNotBlank()
    
    private fun setupViewModel() {
        binding.viewModel = viewModel.apply {
            navigateProcessor.subscribe { item ->
                when (item.itemId) {
                    R.id.action_profile -> navigator.replaceFragment(R.id.container, userInfoFragment)
                    R.id.action_lists   -> navigator.replaceFragment(R.id.container, viewPagerFragment)
                }
            }
            userProcessor.subscribe({
                it.userName.let {
                    userName = it
                    myApplication.updateUserName(it)
                    setupFragmentManager()
                }
            }, {
                //TODO make dialog??.
                it.printStackTrace()
            })
        }
        binding.setLifecycleOwner(this)
        setViewModel(viewModel)
        if (userName.isBlank()) viewModel.setupUser()
        else setupFragmentManager()
    }
    
    private fun setupFragmentManager() {
        viewPagerFragment = with(userName).instance<ViewPagerFragment>().value
        userInfoFragment = with(userName).instance<UserInfoFragment>().value
        binding.navigation.selectedItemId = R.id.action_lists
        navigator.replaceFragment(R.id.container, viewPagerFragment)
    }
}
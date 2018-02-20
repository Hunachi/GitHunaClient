package com.example.hunachi.githunaclient.presentation.main

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.example.hunachi.githunaclient.R
import com.example.hunachi.githunaclient.data.api.responce.User
import com.example.hunachi.githunaclient.databinding.ActivityMainBinding
import com.example.hunachi.githunaclient.presentation.application.MyApplication
import com.example.hunachi.githunaclient.presentation.base.BaseActivity
import com.example.hunachi.githunaclient.presentation.fragment.viewpager.ViewPagerFragment
import com.example.hunachi.githunaclient.presentation.fragment.userinfo.UserInfoFragment
import com.example.hunachi.githunaclient.presentation.helper.Navigator
import com.example.hunachi.githunaclient.util.FragmentTag
import com.example.hunachi.githunaclient.util.extension.show
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.with

class MainActivity : BaseActivity() {
    
    private val viewModel: MainViewModel by with(this).instance()
    private val navigator: Navigator by with(this).instance()
    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }
    private lateinit var user: User
    private val manager = supportFragmentManager
    private lateinit var userInfoFragment: UserInfoFragment
    private lateinit var viewPagerFragment: ViewPagerFragment
    private val fragmentTags = FragmentTag.values().map { it.name }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(checkToken()) setupViewModel()
    }
    
    private fun checkToken(): Boolean =
            if ((application as MyApplication).token.isBlank()) {
                navigator.navigateToLogin()
                false
            } else true
    
    private fun setupViewModel() {
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
        setViewModel(viewModel)
        binding.navigation.selectedItemId = R.id.action_lists
        viewModel.apply {
            navigateProcessor.subscribe { item ->
                when (item.itemId) {
                    R.id.action_profile -> manager.show(FragmentTag.USER_INFO.name, fragmentTags)
                    R.id.action_lists   -> manager.show(FragmentTag.FEED.name, fragmentTags)
                }
            }
            userProcessor.subscribe ({
                user = it
                if(checkToken()) setupFragmentManager()
            },{
                it.printStackTrace()
            })
        }
    }
    
    private fun setupFragmentManager() {
        viewPagerFragment = with(user.userName).instance<ViewPagerFragment>().value
        userInfoFragment = with(user).instance<UserInfoFragment>().value
        manager.beginTransaction().apply {
            add(R.id.container, userInfoFragment, FragmentTag.USER_INFO.name)
            add(R.id.container, viewPagerFragment, FragmentTag.FEED.name)
        }.commit()
    }
}
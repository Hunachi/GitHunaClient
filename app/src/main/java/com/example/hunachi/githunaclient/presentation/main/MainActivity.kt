package com.example.hunachi.githunaclient.presentation.main

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.widget.Toast
import com.example.hunachi.githunaclient.R
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
    private val myApplication: MyApplication by lazy { application as MyApplication }
    private val manager = supportFragmentManager
    private lateinit var userInfoFragment: UserInfoFragment
    private lateinit var viewPagerFragment: ViewPagerFragment
    private val fragmentTags = FragmentTag.values().map { it.name }
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
                    R.id.action_profile -> manager.show(FragmentTag.USER_INFO.name, fragmentTags)
                    R.id.action_lists   -> manager.show(FragmentTag.FEED.name, fragmentTags)
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
        binding.navigation.selectedItemId = R.id.action_lists
        if (userName.isBlank()) viewModel.setupUser()
        else setupFragmentManager()
    }
    
    private fun setupFragmentManager() {
        viewPagerFragment = with(userName).instance<ViewPagerFragment>().value
        userInfoFragment = with(userName).instance<UserInfoFragment>().value
        manager.beginTransaction().apply {
            add(R.id.container, userInfoFragment, FragmentTag.USER_INFO.name)
            add(R.id.container, viewPagerFragment, FragmentTag.FEED.name)
        }.commit()
    }
}
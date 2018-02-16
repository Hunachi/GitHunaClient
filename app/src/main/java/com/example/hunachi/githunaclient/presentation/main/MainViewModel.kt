package com.example.hunachi.githunaclient.presentation.main

import android.util.Log
import android.widget.Toast
import com.example.hunachi.githunaclient.R
import com.example.hunachi.githunaclient.presentation.MainApplication
import com.example.hunachi.githunaclient.presentation.base.BaseViewModel
import com.example.hunachi.githunaclient.util.BottomNavigationListener
import com.example.hunachi.githunaclient.presentation.MyApplication
import com.example.hunachi.githunaclient.presentation.fragment.UserInfoFragment
import com.example.hunachi.githunaclient.presentation.fragment.feeds.FeedsFragment
import com.example.hunachi.githunaclient.presentation.helper.Navigator
import com.example.hunachi.githunaclient.util.FragmentTag
import com.example.hunachi.githunaclient.util.extension.show

/**
 * Created by hunachi on 2018/01/27.
 */

class MainViewModel(
        private val navigator: Navigator,
        private val application: MyApplication,
        private val userInfoFragment: UserInfoFragment,
        private val feedsFragment: FeedsFragment
) : BaseViewModel(application) {
    
    private val manager = navigator.activity.supportFragmentManager
    private val fragmentTags = FragmentTag.values().map { it.name }
    
    override fun onCreate() {
        super.onCreate()
        manager.beginTransaction().apply {
            add(R.id.container, feedsFragment, FragmentTag.FEED.name)
            add(R.id.container, userInfoFragment, FragmentTag.USER_INFO.name)
        }.commit()
    }
    
    override fun onStart() {
        super.onStart()
        if (application.token.isBlank()) {
            navigator.navigateToLogin()
         }
    }
    
    //Listener of BottomNavigation(what I made hard.)
    fun onItemSelected(): BottomNavigationListener = BottomNavigationListener { item ->
        manager.run {
            when (item.itemId) {
                R.id.action_search   -> show(FragmentTag.USER_INFO.name, fragmentTags)
                R.id.action_settings -> show(FragmentTag.FEED.name, fragmentTags)
            }
        }
        true
    }
}
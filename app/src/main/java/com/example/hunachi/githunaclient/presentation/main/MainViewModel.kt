package com.example.hunachi.githunaclient.presentation.main

import android.app.Application
import android.widget.Toast
import com.example.hunachi.githunaclient.R
import com.example.hunachi.githunaclient.presentation.base.BaseViewModel
import com.example.hunachi.githunaclient.util.BottomNavigationListener
import com.example.hunachi.githunaclient.presentation.MyApplication
import com.example.hunachi.githunaclient.presentation.fragment.UserInfoFragment
import com.example.hunachi.githunaclient.presentation.fragment.event.FollowerEventFragment
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
        private val followerEventFragment: FollowerEventFragment
) : BaseViewModel(application) {
    
    private val manager = navigator.activity.supportFragmentManager
    private val fragmentTags = FragmentTag.values().map { it.name }
    
    override fun onCreate() {
        super.onCreate()
        manager.beginTransaction().apply {
            add(R.id.container, followerEventFragment, FragmentTag.FOLLOWER_EVENT.name)
            add(R.id.container, userInfoFragment, FragmentTag.USER_INFO.name)
        }.commit()
    }
    
    override fun onStart() {
        super.onStart()
        if (application.token.isBlank()) {
            navigator.navigateToLogin()
            Toast.makeText(application, "Github accountと未連携", Toast.LENGTH_SHORT).show()
        } else Toast.makeText(application, "今日も一日がんばるぞい!{name}さん!", Toast.LENGTH_SHORT).show()
    }
    
    //Listener of BottomNavigation(what I made hard.)
    fun onItemSelected(): BottomNavigationListener = BottomNavigationListener { item ->
        manager.run {
            when (item.itemId) {
                R.id.action_search   -> show(FragmentTag.USER_INFO.name, fragmentTags)
                R.id.action_settings -> show(FragmentTag.FOLLOWER_EVENT.name, fragmentTags)
            }
        }
        true
    }
}
package com.example.hunachi.githunaclient.presentation.main

import android.widget.Toast
import com.example.hunachi.githunaclient.R
import com.example.hunachi.githunaclient.presentation.base.BaseViewModel
import com.example.hunachi.githunaclient.util.BottomNavigationListner
import com.example.hunachi.githunaclient.presentation.MyApplication
import com.example.hunachi.githunaclient.presentation.fragment.UserInfoFragment
import com.example.hunachi.githunaclient.presentation.fragment.event.FollowerEventFragment
import com.example.hunachi.githunaclient.presentation.helper.Navigator
import com.example.hunachi.githunaclient.util.extention.show

/**
 * Created by hunachi on 2018/01/27.
 */

class MainViewModel(
        val navigator: Navigator,
        val application: MyApplication,
        val userInfoFragment: UserInfoFragment,
        val followerEventFragment: FollowerEventFragment
) : BaseViewModel(application) {
    
    /*private val textProcessor: PublishProcessor<String> = PublishProcessor.create()
    var text: LiveData<String> = LiveDataReactiveStreams.fromPublisher(textProcessor)*/
    private val manager = navigator.activity.supportFragmentManager
    
    companion object {
        val fragmentTags = mutableListOf("follow", "user")
    }
    
    override fun onCreate() {
        super.onCreate()
        manager.beginTransaction().apply {
            add(R.id.container, followerEventFragment, "follow")
            add(R.id.container, userInfoFragment, "user")
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
    fun onItemSelected(): BottomNavigationListner = BottomNavigationListner { item ->
        when (item.itemId) {
            R.id.action_search   -> manager.show("user", fragmentTags)
            R.id.action_settings -> manager.show("follow", fragmentTags)
        }
        true
    }
}

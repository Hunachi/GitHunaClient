package com.example.hunachi.githunaclient.presentation.main

import android.widget.Toast
import com.example.hunachi.githunaclient.R
import com.example.hunachi.githunaclient.presentation.base.BaseViewModel
import com.example.hunachi.githunaclient.util.BottomNavigationListner
import com.example.hunachi.githunaclient.presentation.MyApplication
import com.example.hunachi.githunaclient.presentation.fragment.UserInfoFragment
import com.example.hunachi.githunaclient.presentation.fragment.event.FollowerEventFragment
import com.example.hunachi.githunaclient.presentation.helper.Navigator

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
    
    override fun onCreate() {
        super.onCreate()
    }
    
    override fun onStart() {
        super.onStart()
        if (application.token.isBlank()) {
            navigator.navigateToLogin()
            Toast.makeText(context, "Github accountと未連携", Toast.LENGTH_SHORT).show()
        } else Toast.makeText(context, "今日も一日がんばるぞい!{name}さん!", Toast.LENGTH_SHORT).show()
    }
    
    //Listener of BottomNavigation(what I made hard.)
    fun onItemSelected(): BottomNavigationListner = BottomNavigationListner { item ->
        Toast.makeText(context, "${item.itemId}", Toast.LENGTH_SHORT).show()
        when (item.itemId) {
            R.id.action_search -> navigator.replaceFragment(userInfoFragment)
            R.id.action_settings -> navigator.replaceFragment(followerEventFragment)
        }
        true
    }
}
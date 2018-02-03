package com.example.hunachi.githunaclient.kodein


import com.example.hunachi.githunaclient.presentation.MyApplication
import com.example.hunachi.githunaclient.presentation.event.UserInfoFragment
import com.example.hunachi.githunaclient.presentation.helper.Navigator

/**
 * Created by hunachi on 2018/01/28.
 */
data class MainViewModelModule(
        val navigator: Navigator,
        val application: MyApplication,
        val userInfoFragment: UserInfoFragment
)
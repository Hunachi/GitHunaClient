package com.example.hunachi.githunaclient.kodein

import com.example.hunachi.githunaclient.presentation.MyApplication
import com.example.hunachi.githunaclient.presentation.helper.Navigator

/**
 * Created by hunachi on 2018/02/03.
 */
data class UserInfoViewModelModule(
        val navigator: Navigator,
        val application: MyApplication
)
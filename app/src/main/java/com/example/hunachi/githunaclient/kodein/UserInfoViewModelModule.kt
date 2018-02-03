package com.example.hunachi.githunaclient.kodein

import android.arch.lifecycle.LifecycleOwner
import com.example.hunachi.githunaclient.presentation.MyApplication
import com.example.hunachi.githunaclient.presentation.helper.Navigator

/**
 * Created by hunachi on 2018/02/03.
 */
data class UserInfoViewModelModule(
        val navigator: Navigator,
        val application: MyApplication,
        val owner: LifecycleOwner
)
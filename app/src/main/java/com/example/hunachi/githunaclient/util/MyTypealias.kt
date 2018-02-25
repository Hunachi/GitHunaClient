package com.example.hunachi.githunaclient.util

import android.support.design.widget.BottomNavigationView
import com.example.hunachi.githunaclient.presentation.fragment.list.BaseItem
import com.example.hunachi.githunaclient.presentation.login.SignalStatus
        
        /**
 * Created by hunachi on 2018/01/30.
 */
typealias Scopes = MutableList<String>

typealias OauthAccessCallback = (SignalStatus) -> Unit

typealias ItemCallback = (BaseItem) -> Unit

typealias GoWebCallback = (String) -> Unit

typealias LoadingCallback = (Boolean) -> Unit

typealias BottomNavigationListener = BottomNavigationView.OnNavigationItemSelectedListener
package io.github.hunachi.githunaclient.util

import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import io.github.hunachi.githunaclient.presentation.fragment.list.BaseItem
import io.github.hunachi.githunaclient.presentation.login.SignalStatus
        
        /**
         * Created by hunachi on 2018/01/30.
         */
typealias Scopes = MutableList<String>

typealias OauthAccessCallback = (SignalStatus) -> Unit

typealias ItemCallback = (BaseItem) -> Unit

typealias GoWebCallback = (String) -> Unit

typealias LoadingCallback = (Boolean) -> Unit

typealias NavigatorCallback = (MenuItem) -> Unit

typealias ErrorCallback = () -> Unit

typealias BottomNavigationListener = BottomNavigationView.OnNavigationItemSelectedListener

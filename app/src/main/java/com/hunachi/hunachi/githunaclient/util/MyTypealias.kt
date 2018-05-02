package com.hunachi.hunachi.githunaclient.util

import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import com.hunachi.hunachi.githunaclient.presentation.fragment.list.BaseItem
import com.hunachi.hunachi.githunaclient.presentation.login.SignalStatus

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
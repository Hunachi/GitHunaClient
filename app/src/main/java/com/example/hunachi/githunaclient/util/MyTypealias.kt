package com.example.hunachi.githunaclient.util

import android.support.design.widget.BottomNavigationView
        
        /**
 * Created by hunachi on 2018/01/30.
 */
typealias Scopes = MutableList<String>

typealias OauthAccessCallback = (StatusModule) -> Unit

typealias BottomNavigationListner = BottomNavigationView.OnNavigationItemSelectedListener
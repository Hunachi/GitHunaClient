package com.example.hunachi.githunaclient.util

import android.support.design.widget.BottomNavigationView
import com.example.hunachi.githunaclient.presentation.fragment.feeds.Feed
        
        /**
 * Created by hunachi on 2018/01/30.
 */
typealias Scopes = MutableList<String>

typealias OauthAccessCallback = (StatusModule) -> Unit

typealias FeedItemCallback = (Feed) -> Unit

typealias BottomNavigationListener = BottomNavigationView.OnNavigationItemSelectedListener
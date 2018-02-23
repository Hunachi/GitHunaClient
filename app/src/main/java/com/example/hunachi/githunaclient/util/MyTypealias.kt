package com.example.hunachi.githunaclient.util

import android.support.design.widget.BottomNavigationView
import com.example.hunachi.githunaclient.data.api.responce.Repository
import com.example.hunachi.githunaclient.presentation.fragment.list.feed.Feed
        
        /**
 * Created by hunachi on 2018/01/30.
 */
typealias Scopes = MutableList<String>

typealias OauthAccessCallback = (StatusSignal) -> Unit

typealias FeedItemCallback = (Feed) -> Unit

typealias GoWebCallback = (String) -> Unit

typealias LoadingCallback = (Boolean) -> Unit

typealias BottomNavigationListener = BottomNavigationView.OnNavigationItemSelectedListener
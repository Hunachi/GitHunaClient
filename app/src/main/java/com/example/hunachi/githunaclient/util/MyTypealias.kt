package com.example.hunachi.githunaclient.util

import android.support.design.widget.BottomNavigationView
import com.example.hunachi.githunaclient.data.api.responce.Repo
import com.example.hunachi.githunaclient.data.api.responce.Repository
import com.example.hunachi.githunaclient.presentation.fragment.feeds.Feed
        
        /**
 * Created by hunachi on 2018/01/30.
 */
typealias Scopes = MutableList<String>

typealias OauthAccessCallback = (StatusSignal) -> Unit

typealias FeedItemCallback = (Feed) -> Unit

typealias GoWebCallback = (Repository) -> Unit

typealias LoadingCallback = (Boolean) -> Unit

typealias BottomNavigationListener = BottomNavigationView.OnNavigationItemSelectedListener
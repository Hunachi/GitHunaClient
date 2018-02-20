package com.example.hunachi.githunaclient.util

import com.example.hunachi.githunaclient.BuildConfig

/**
 * Created by hunachi on 2018/02/20.
 */
class Key{
    companion object {
        const val clientId: String = BuildConfig.CLIENT_ID
        const val state: String = "git-huna-oauth"
        const val clientSecret: String = BuildConfig.CLIENT_SECRET
    }
}
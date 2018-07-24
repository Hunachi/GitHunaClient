package io.github.hunachi.githunaclient.util

import io.github.hunachi.githunaclient.BuildConfig

/**
 * Created by hunachi on 2018/02/20.
 */
class Key{
    companion object {
        const val CLIENT_ID: String = BuildConfig.CLIENT_ID
        const val state: String = "git-huna-oauth"
        const val CLIENT_SECRET: String = BuildConfig.CLIENT_SECRET
    }
}

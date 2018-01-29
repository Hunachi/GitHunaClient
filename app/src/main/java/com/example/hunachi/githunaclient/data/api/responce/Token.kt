package com.example.hunachi.githunaclient.data.api.responce

import android.support.annotation.RestrictTo

/**
 * Created by hunachi on 2018/01/30.
 */
data class Token(
        //todo make kotshi.
        val access_token: String,
        val scope: String,
        val token_type: String
)
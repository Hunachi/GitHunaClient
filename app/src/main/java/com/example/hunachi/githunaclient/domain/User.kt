package com.example.hunachi.githunaclient.domain

import java.io.Serializable

/**
 * Created by hunachi on 2018/01/29.
 */
public data class User(
        var token: String = "",
        var userName: String = ""
): Serializable


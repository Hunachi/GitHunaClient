package com.example.hunachi.githunaclient.data.api.responce

import android.support.annotation.RestrictTo
import com.example.hunachi.githunaclient.util.Scopes
import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

/**
 * Created by hunachi on 2018/01/30.
 */
@JsonSerializable
data class Token(
        //todo make kotshi.
        @Json(name = "access_token")
        val token: String,
        val scope: String?,
        @Json(name = "token_type")
        val tokenType: String
)
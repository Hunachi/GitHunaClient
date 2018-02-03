package com.example.hunachi.githunaclient.data.api.responce

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

/**
 * Created by hunachi on 2018/01/30.
 */
@JsonSerializable
data class Token(
        @Json(name = "access_token")
        val token: String,
        val scope: String?,
        @Json(name = "token_type")
        val tokenType: String
)
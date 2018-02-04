package com.example.hunachi.githunaclient.data.api.responce

import com.squareup.moshi.Json
import io.reactivex.annotations.Nullable
import se.ansman.kotshi.JsonSerializable

/**
 * Created by hunachi on 2018/02/04.
 */
@JsonSerializable
data class Actor(
        @Json(name = "login")
        var userName: String,
        @Nullable
        @Json(name = "display_login")
        var name: String?,
        var url: String,
        @Json(name = "avatar_url")
        var avatarUrl: String?
)
package com.example.hunachi.githunaclient.data.api.responce

import com.squareup.moshi.Json
import io.reactivex.annotations.Nullable
import se.ansman.kotshi.JsonSerializable

/**
 * Created by hunachi on 2018/02/04.
 */
@JsonSerializable
data class Organization(
        @Json(name = "login")
        val name: String,
        @Json(name = "html_url")
        val url: String,
        @Nullable
        @Json(name = "avatar_url")
        val avatarUrl: String
)
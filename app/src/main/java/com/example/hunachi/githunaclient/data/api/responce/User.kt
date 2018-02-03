package com.example.hunachi.githunaclient.data.api.responce

import com.squareup.moshi.Json
import io.reactivex.annotations.Nullable
import se.ansman.kotshi.JsonSerializable

/**
 * Created by hunachi on 2018/02/03.
 */
@JsonSerializable
data class User(
        @Json(name = "login")
        var userName: String,
        @Json(name = "avatar_url")
        var avatarUrl: String?,
        @Json(name = "html_url")
        var url: String,
        @Nullable
        var name: String?,
        @Nullable
        var company: String?,
        var bio: String?,
        @Json(name = "public_repos")
        var publicRepoCount: Int,
        @Json(name = "public_gists")
        var gistCount: Int,
        var followers: Int,
        var following: Int
)
package com.example.hunachi.githunaclient.data.api.responce

import com.example.hunachi.githunaclient.presentation.fragment.list.BaseItem
import com.squareup.moshi.Json
import io.reactivex.annotations.Nullable
import se.ansman.kotshi.JsonSerializable
import java.io.Serializable

/**
 * Created by hunachi on 2018/02/03.
 */
@JsonSerializable
data class User(
        @Json(name = "login")
        var userName: String = "",
        @Json(name = "avatar_url")
        var avatarUrl: String? = null,
        @Json(name = "html_url")
        var htmlUrl: String = "",
        @Nullable
        var name: String? = null,
        @Nullable
        var company: String? = null,
        var bio: String? = null,
        @Nullable
        var blog: String? = null,
        @Json(name = "public_repos")
        var publicRepoCount: Int = 0,
        @Json(name = "public_gists")
        var gistCount: Int = 0,
        var followers: Int = 0,
        var following: Int = 0
): Serializable
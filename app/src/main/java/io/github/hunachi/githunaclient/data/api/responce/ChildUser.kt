package io.github.hunachi.githunaclient.data.api.responce

import io.github.hunachi.githunaclient.presentation.fragment.list.BaseItem
import com.squareup.moshi.Json
import io.reactivex.annotations.Nullable
import se.ansman.kotshi.JsonSerializable

/**
 * Created by hunachi on 2018/02/23.
 */
@JsonSerializable
data class ChildUser(
        @Json(name = "login")
        var userName: String,
        @Nullable
        @Json(name = "display_login")
        var name: String?,
        var url: String,
        @Json(name = "avatar_url")
        var avatarUrl: String
): BaseItem

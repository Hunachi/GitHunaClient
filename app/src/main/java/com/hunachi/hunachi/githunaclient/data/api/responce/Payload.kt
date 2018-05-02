package com.hunachi.hunachi.githunaclient.data.api.responce

import com.squareup.moshi.Json
import io.reactivex.annotations.Nullable
import se.ansman.kotshi.JsonSerializable


/**
 * Created by hunachi on 2018/02/04.
 */
@JsonSerializable
data class Payload(
        @Nullable
        val action: String?,
        @Nullable
        @Json(name = "repository")
        val repo: Repo?,
        @Nullable
        val issue: Issue?,
        @Nullable
        val organization: Organization?,
        @Nullable
        val forkee: Forkee?
    /*全部書くときりがないとって*/
)
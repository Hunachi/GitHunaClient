package com.hunachi.hunachi.githunaclient.data.api.responce

import com.squareup.moshi.Json
import io.reactivex.annotations.Nullable
import se.ansman.kotshi.JsonSerializable

/**
 * Created by hunachi on 2018/02/04.
 */
@JsonSerializable
data class Repo (
        val id: Int,
        val name: String,
        @Nullable
        @Json(name = "full_name")
        val fullName: String?,
        val url: String
)
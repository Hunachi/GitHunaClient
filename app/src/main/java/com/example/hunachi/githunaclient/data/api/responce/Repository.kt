package com.example.hunachi.githunaclient.data.api.responce

import com.example.hunachi.githunaclient.presentation.fragment.list.BaseItem
import com.squareup.moshi.Json
import io.reactivex.annotations.Nullable
import se.ansman.kotshi.JsonSerializable

/**
 * Created by hunachi on 2018/02/18.
 */
@JsonSerializable
data class Repository(
        val id: Int,
        val name: String,
        @Json(name = "full_name")
        val fullName: String,
        val url: String,
        @Json(name = "html_url")
        val htmlUrl: String,
        @Json(name = "created_at")
        val createdAt: String
): BaseItem
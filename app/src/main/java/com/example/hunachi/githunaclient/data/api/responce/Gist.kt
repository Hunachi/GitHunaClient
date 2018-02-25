package com.example.hunachi.githunaclient.data.api.responce

import com.example.hunachi.githunaclient.presentation.fragment.list.BaseItem
import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

/**
 * Created by hunachi on 2018/02/22.
 */
@JsonSerializable
data class Gist (
        val id: String,
        val owner: Actor,
        var description: String,
        val html_url: String,
        @Json(name = "created_at")
        val createdAt: String,
        @Json(name = "updated_at")
        val updatedAt: String
): BaseItem
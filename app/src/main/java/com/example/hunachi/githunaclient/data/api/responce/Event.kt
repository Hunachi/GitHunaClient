package com.example.hunachi.githunaclient.data.api.responce

import com.squareup.moshi.Json
import io.reactivex.annotations.Nullable
import se.ansman.kotshi.JsonSerializable

/**
 * Created by hunachi on 2018/02/04.
 */
@JsonSerializable
data class Event(
        val id: String,
        val type: String,
        val actor: Actor,
        @Nullable
        val repo: Repo?,
        val payload: Payload,
        @Json(name = "created_at")
        val createdAt: String
)
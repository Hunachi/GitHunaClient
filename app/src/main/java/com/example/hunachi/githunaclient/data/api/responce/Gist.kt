package com.example.hunachi.githunaclient.data.api.responce

import se.ansman.kotshi.JsonSerializable

/**
 * Created by hunachi on 2018/02/22.
 */
@JsonSerializable
data class Gist (
        val id: String,
        val owner: Actor,
        val description: String,
        val html_url: String
)
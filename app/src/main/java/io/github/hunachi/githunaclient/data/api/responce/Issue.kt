package io.github.hunachi.githunaclient.data.api.responce

import com.squareup.moshi.Json


/**
 * Created by hunachi on 2018/02/04.
 */
data class Issue(
        val title: String,
        @Json(name = "html_url")
        val url: String
)

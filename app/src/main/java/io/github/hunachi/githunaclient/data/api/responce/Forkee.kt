package io.github.hunachi.githunaclient.data.api.responce

import com.squareup.moshi.Json
import io.reactivex.annotations.Nullable
import se.ansman.kotshi.JsonSerializable

/**
 * Created by hunachi on 2018/02/04.
 */
@JsonSerializable
data class Forkee(
        @Json(name = "html_url")
        val url: String?
)

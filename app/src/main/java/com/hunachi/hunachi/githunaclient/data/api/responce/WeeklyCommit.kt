package com.hunachi.hunachi.githunaclient.data.api.responce

import io.reactivex.annotations.Nullable
import se.ansman.kotshi.JsonSerializable

/**
 * Created by hunachi on 2018/02/28.
 */
@JsonSerializable
data class WeeklyCommit(
        @Nullable
        val all: List<Int>?,
        @Nullable
        val owner: List<Int>?
)
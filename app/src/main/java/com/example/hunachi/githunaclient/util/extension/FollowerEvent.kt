package com.example.hunachi.githunaclient.util.extension

import com.example.hunachi.githunaclient.data.api.responce.Event
import com.example.hunachi.githunaclient.presentation.fragment.event.FollowerEvent

/**
 * Created by hunachi on 2018/02/07.
 */
fun Event.convertToFollowerEvent() =
        FollowerEvent().apply {
            id = this@convertToFollowerEvent.id.toLong()
            actor = this@convertToFollowerEvent.actor.userName
            avatarUrl = this@convertToFollowerEvent.actor.avatarUrl
            action = type.substring(0, type.lastIndex - 4)
            repositoryName = when {
                repo?.fullName != null -> repo.fullName
                repo?.name != null     -> repo.name
                else                   -> "NULL"
            }
        }
package com.example.hunachi.githunaclient.util.extension

import com.example.hunachi.githunaclient.data.api.responce.Event
import com.example.hunachi.githunaclient.presentation.fragment.feeds.Feed

/**
 * Created by hunachi on 2018/02/07.
 */
fun Event.convertToFollowerEvent() =
        Feed().apply {
            id = this@convertToFollowerEvent.id.toLong()
            actor = this@convertToFollowerEvent.actor.userName
            avatarUrl = this@convertToFollowerEvent.actor.avatarUrl
            action = type.convertToActionText(payload.action)?: type.convertToLowerText()
            repositoryName = when {
                repo?.fullName != null -> repo.fullName
                repo?.name != null     -> repo.name
                else                   -> "unKnown"
            }
        }
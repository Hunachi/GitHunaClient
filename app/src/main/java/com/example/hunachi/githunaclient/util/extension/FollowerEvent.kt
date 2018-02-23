package com.example.hunachi.githunaclient.util.extension

import com.example.hunachi.githunaclient.data.api.responce.Event
import com.example.hunachi.githunaclient.presentation.fragment.list.feed.Feed

/**
 * Created by hunachi on 2018/02/07.
 */
fun Event.convertToFollowerEvent() =
        Feed().apply {
            id = this@convertToFollowerEvent.id.toLong()
            actor = this@convertToFollowerEvent.actor.userName
            avatarUrl = this@convertToFollowerEvent.actor.avatarUrl
            repositoryUrl = this@convertToFollowerEvent.repo.url
            action = type.convertToActionText(payload.action)?: type.convertToLowerText()
            repositoryName = when {
                repo.fullName != null -> repo.fullName
                else                   -> repo.name
            }
        }
package com.example.hunachi.githunaclient.util.extension

import android.content.Intent
import android.support.annotation.CheckResult
import android.support.customtabs.CustomTabsIntent
import android.support.v4.content.ContextCompat
import com.example.hunachi.githunaclient.R
import com.example.hunachi.githunaclient.presentation.base.BaseActivity

/**
 * Created by hunachi on 2018/02/18.
 */
@CheckResult
fun BaseActivity.customTabsIntent(): CustomTabsIntent{
    val intent = CustomTabsIntent.Builder()
            .setShowTitle(true)
            .setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimary))
            .build()
    intent.intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
    intent.intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    return intent
}
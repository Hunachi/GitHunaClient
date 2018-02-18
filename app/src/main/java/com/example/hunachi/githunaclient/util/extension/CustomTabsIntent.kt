package com.example.hunachi.githunaclient.util.extension

import android.support.annotation.CheckResult
import android.support.customtabs.CustomTabsIntent
import android.support.v4.content.ContextCompat
import com.example.hunachi.githunaclient.R
import com.example.hunachi.githunaclient.presentation.base.BaseActivity

/**
 * Created by hunachi on 2018/02/18.
 */
@CheckResult
fun BaseActivity.customTabsIntent() = CustomTabsIntent.Builder()
        .setShowTitle(true)
        .setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimary))
        .build()
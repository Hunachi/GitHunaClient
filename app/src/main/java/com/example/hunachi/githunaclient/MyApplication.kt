package com.example.hunachi.githunaclient

import android.app.Application
import com.github.salomonbrys.kodein.*

/**
 * Created by hunachi on 2018/01/27.
 */
class MyApplication : Application(), KodeinAware {
    override val kodein by Kodein.lazy {
        bind<MyApplication>() with singleton { this@MyApplication }
        bind<MainActivity>() with instance(MainActivity())
    }
}
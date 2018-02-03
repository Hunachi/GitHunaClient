package com.example.hunachi.githunaclient.util.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by hunachi on 2018/01/31.
 */
class AppSchedulerProvider: SchedulerProvider {
    
    override fun ui(): Scheduler = AndroidSchedulers.mainThread()
    
    override fun io(): Scheduler = Schedulers.io()
    
}
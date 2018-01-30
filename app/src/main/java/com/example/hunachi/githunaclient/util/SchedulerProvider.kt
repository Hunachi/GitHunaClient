package com.example.hunachi.githunaclient.util

import io.reactivex.Scheduler

/**
 * Created by hunachi on 2018/01/31.
 */
interface SchedulerProvider{
    
    fun ui(): Scheduler
    
    fun io(): Scheduler
    
}
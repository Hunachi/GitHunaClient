package com.hunachi.hunachi.githunaclient.util.rx

import io.reactivex.Scheduler

/**
 * Created by hunachi on 2018/01/31.
 */
/*Test用とMain用があるため(予定)*/
interface SchedulerProvider{
    
    fun ui(): Scheduler
    
    fun io(): Scheduler
    
}
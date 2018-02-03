package com.example.hunachi.githunaclient.util

import com.example.hunachi.githunaclient.util.rx.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

/**
 * Created by hunachi on 2018/02/03.
 */
class TestSchedulerProvider : SchedulerProvider {
    override fun ui(): Scheduler = Schedulers.trampoline()
    
    override fun io(): Scheduler = Schedulers.trampoline()
}
package com.hunachi.hunachi.githunaclient.util.extension

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer

/**
 * Created by hunachi on 2018/03/03.
 */
fun <T> LiveData<T>.observerOnChanged(owner: LifecycleOwner, observer: Observer<T>): Unit{
    var prev : T? = null
    this.observe(owner, Observer<T>{
        if(prev?.equals(it) != true)
            observer.onChanged(it)
        prev = it
    })
}
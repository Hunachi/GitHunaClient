package com.example.hunachi.githunaclient.util.extention

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.databinding.BindingAdapter
import android.support.design.widget.BottomNavigationView
import com.example.hunachi.githunaclient.util.BottomNavigationListner

/**
 * Created by hunachi on 2018/02/01.
 */
@BindingAdapter("app:navigationClick")
fun BottomNavigationView.selectedListener(listener: BottomNavigationListner)
        = setOnNavigationItemSelectedListener(listener)

fun<T> LiveData<T>.observeOnChanged(owner: LifecycleOwner, observer: Observer<T>){
    this.observe(owner, Observer<T>{
        
    })
}
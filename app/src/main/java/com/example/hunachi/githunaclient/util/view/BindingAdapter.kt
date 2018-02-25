package com.example.hunachi.githunaclient.util.view

import android.databinding.BindingAdapter
import android.support.design.widget.BottomNavigationView
import android.support.v4.widget.SwipeRefreshLayout
import android.widget.ImageButton
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.hunachi.githunaclient.util.BottomNavigationListener
import com.example.hunachi.githunaclient.util.extension.setBackgroundTranspot

/**
 * Created by hunachi on 2018/02/01.
 */
@BindingAdapter("app:navigationClick")
fun BottomNavigationView.selectedListener(listener: BottomNavigationListener) = setOnNavigationItemSelectedListener(listener)

@BindingAdapter("app:setIcon")
fun ImageButton.setIcon(url: String?) {
    this.setBackgroundTranspot()
    Glide.with(this).load(url).apply(RequestOptions.circleCropTransform()).into(this)
}

@BindingAdapter("app:listRefresh")
fun SwipeRefreshLayout.refreshListner(listener: SwipeRefreshLayout.OnRefreshListener) = setOnRefreshListener(listener)

/*
fun<T> LiveData<T>.observeOnChanged(owner: LifecycleOwner, observer: Observer<T>){
    this.observe(owner, Observer<T>{
        
    })
}*/

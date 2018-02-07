package com.example.hunachi.githunaclient.util.extension

import android.databinding.BindingAdapter
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.design.widget.BottomNavigationView
import android.widget.ImageButton
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.hunachi.githunaclient.util.BottomNavigationListener

/**
 * Created by hunachi on 2018/02/01.
 */
@BindingAdapter("app:navigationClick")
fun BottomNavigationView.selectedListener(listener: BottomNavigationListener)
        = setOnNavigationItemSelectedListener(listener)

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
@BindingAdapter("app:setIcon")
fun ImageButton.setIcon(url: String) {
    this.setBackgroundTranspot()
    Glide.with(this).load(url).apply(RequestOptions.circleCropTransform()).into(this)
}

/*
fun<T> LiveData<T>.observeOnChanged(owner: LifecycleOwner, observer: Observer<T>){
    this.observe(owner, Observer<T>{
        
    })
}*/

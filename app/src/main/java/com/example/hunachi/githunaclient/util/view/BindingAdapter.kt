package com.example.hunachi.githunaclient.util.view

import android.annotation.SuppressLint
import android.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.design.widget.BottomNavigationView
import android.support.v4.widget.SwipeRefreshLayout
import android.widget.ImageButton
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.hunachi.githunaclient.R
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

@BindingAdapter("app:setCommitCountImage")
fun ImageView.setImage(url: String) {
    Glide.with(this).load(url).into(this)
}

@SuppressLint("ResourceType")
@BindingAdapter("app:setCommitCountImage")
fun ImageView.setCommitCountImage(@IdRes resourceId: Int) {
    val resourceId_ = if (resourceId > 0) resourceId else R.drawable.contribute_loading_image
    Glide.with(this).load(resourceId_).into(this)
}

@BindingAdapter("app:listRefresh")
fun SwipeRefreshLayout.refreshListner(listener: SwipeRefreshLayout.OnRefreshListener) = setOnRefreshListener(listener)

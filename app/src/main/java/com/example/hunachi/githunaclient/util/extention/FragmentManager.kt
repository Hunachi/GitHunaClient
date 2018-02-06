package com.example.hunachi.githunaclient.util.extention

import android.support.v4.app.FragmentManager

/**
 * Created by hunachi on 2018/02/06.
 */
/*fragmentを再利用する最適解が分からない（思いついたら書き直す！）*/
fun FragmentManager.show(tag: String, deleteTags: MutableList<String>) {
    beginTransaction().apply {
        show(findFragmentByTag(tag))
        deleteTags.forEach { if (it != tag) hide(it) }
    }.commit()
}

fun FragmentManager.hide(tag: String) {
    beginTransaction().apply {
        hide(findFragmentByTag(tag))
    }.commit()
}
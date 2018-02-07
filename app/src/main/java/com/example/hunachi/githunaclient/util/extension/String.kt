package com.example.hunachi.githunaclient.util.extension

import android.util.Log

/**
 * Created by hunachi on 2018/02/08.
 */
/*Let's study competition programming!!*/
fun String.convertNiceText(): String {
    var index = 0
    var string = this.substring(0, this.length - 5)
    while (true) {
        if (string[index].isUpperCase()) {
            string = (if (index > 0) string.substring(0, index) + " " else "") +
                    string[index].toLowerCase() +
                    (if (index + 1 < string.length) string.substring(index + 1, string.length) else "")
        }
        index++
        if (string.all { it.isLowerCase() || it == ' '} || index >= string.length) break
    }
    return string
}
package com.example.hunachi.githunaclient.util.extension

/**
 * Created by hunachi on 2018/02/26.
 */
/*contain使うとO(N*M)になる->O(M).But it's unusable in List where new information is updated any place other than both ends.*/
fun <T> MutableList<T>.addListItem(addList: List<T>, isTopAddPosition: Boolean): Int {
    var addListSize = 0
    if (addList.isEmpty()) return addListSize
    if (this.isEmpty()) {
        this.addAll(0, addList)
        return addList.size
    }
    /*add items from first*/
    if (isTopAddPosition) {
        val insertToPosition = addList.indexOfFirst { this.first() == it }
        if (insertToPosition >= 0) {
            addListSize = insertToPosition
            this.addAll(0, addList.subList(0, insertToPosition))
        }
    } else
    /*add items from last*/ {
        val insertFromPosition = addList.indexOfLast { this.last() == it } + 1
        if (insertFromPosition >= 0) {
            addListSize = addList.size - insertFromPosition
            this.addAll(this.size, addList.subList(insertFromPosition, addList.size))
        }
    }
    return addListSize
}
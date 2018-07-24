package io.github.hunachi.githunaclient.util.extension

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/**
 * Created by hunachi on 2018/02/26.
 */
class MutableListKtTest {
    
    private val list = mutableListOf<Int>()
    private val addListFirst = mutableListOf<Int>()
    private val testListFirst = mutableListOf<Int>()
    private val addListSecond = mutableListOf<Int>()
    private val testListSecond = mutableListOf<Int>()
    
    @Before
    fun init() {
        (1..10).forEach { list.add(it) }
        (-2..2).forEach { addListFirst.add(it) }
        (-2..10).forEach { testListFirst.add(it) }
        (8..12).forEach { addListSecond.add(it) }
        (-2..12).forEach { testListSecond.add(it) }
    }
    
    @Test
    fun addListItem() {
        assertEquals(list.addListItem(addListFirst, isTopAddPosition = true), 3)
        assertEquals(list, testListFirst)
        assertEquals(list.addListItem(addListSecond, isTopAddPosition = false), 2)
        assertEquals(list, testListSecond)
    }
}

package com.example.hunachi.githunaclient.util.extension

import org.junit.Assert.*
import org.junit.Test

/**
 * Created by hunachi on 2018/02/08.
 */
class StringKtTest{
    
    @Test
    fun convertor(){
        assertEquals("HogeHogeHogeHogeHoge".convertNiceText(),"hoge hoge hoge hoge hoge")
    }
    
}